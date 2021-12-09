package gui.component;

import dao.HoaDon_DAO;
import entity.HoaDon;
import entity.Phong;
import entity.TrangThaiHoaDon;
import entity.TrangThaiPhong;
import gui.swing.event.EventRoom;
import gui.swing.event.EventTabSelected;
import gui.swing.layout.WrapLayout;
import gui.swing.panel.PanelShadow;
import gui.swing.panel.TabButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import gui.swing.event.EventShowInfoOver;
import gui.swing.panel.PanelEmpty;

public class PanelMap extends PanelShadow {

    private JPanel pane;
    private TabButton tabPane;
    private PanelEmpty roomMap;
    private List<PanelEmpty> panels = new ArrayList<>();
    private JScrollPane sp;
    private int indexShowing;
    private EventShowInfoOver event;
    private EventRoom eventRoom;

    public int getIndexShowing() {
        return indexShowing;
    }

    public void setIndexShowing(int indexShowing) {
        this.indexShowing = indexShowing;
    }

    public void addEvent(EventShowInfoOver event) {
        this.event = event;
    }

    public void addEventSp(MouseWheelListener event) {
        sp.addMouseWheelListener(event);
    }

    public void addEventRoom(EventRoom event) {
        this.eventRoom = event;
    }

    public void addEventTabSelected(EventTabSelected event) {
        tabPane.setEvent(event);
    }

    public void checkTab() {
        tabPane.check();
    }

    public TabButton getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabButton tabPane) {
        this.tabPane = tabPane;
    }
    
    public PanelMap() {
        buildMap();
    }

    private void buildMap() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 2, 0, 2));
        add(createTabPane(), BorderLayout.NORTH);
        add(createPane(), BorderLayout.CENTER);
    }

    private JScrollPane createPane() {
        sp = new JScrollPane();
        pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.WHITE);
        pane.add(createRoomMap());
        sp.setViewportView(pane);
        sp.getVerticalScrollBar().setUnitIncrement(50);
        sp.setBorder(null);
        return sp;
    }

    private JPanel createTabPane() {
        tabPane = new TabButton();
        tabPane.setBackground(Color.WHITE);
        return tabPane;
    }

    public void showTabPane(int index) {
        pane.removeAll();
        pane.add(panels.get(index));
        pane.repaint();
        pane.revalidate();
    }

    private PanelEmpty createRoomMap() {
        roomMap = new PanelEmpty();
        roomMap.setOpaque(false);
        roomMap.setLayout(new WrapLayout(WrapLayout.LEADING, 50, 20));

        panels.add(roomMap);
        return roomMap;
    }

    public void addRoom(PanelEmpty panel, Room room) {
        room.setPreferredSize(new Dimension(200, 250));

        room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    event.showInfoOver(room, e);
                }
            }
        });
        room.addEvent(eventRoom);
        panel.add(room);
    }

    public void initSearchRoom(List<Phong> dsPhong) {
        if (dsPhong != null) {
            JPanel pnl = panels.get(indexShowing);
            pnl.removeAll();
            pnl.repaint();
            pnl.revalidate();
            dsPhong.forEach(phong -> {
                addRoom(panels.get(indexShowing), new Room(phong));
            });
        }
    }

    public void loadMap(List<Phong> dsPhong, int tang) {
        panels.get(tang).removeAll();
        for (Phong phong : dsPhong) {
            if(phong.getTrangThai() == TrangThaiPhong.DANG_HAT) {
                HoaDon hoaDon = new HoaDon_DAO().getHoaDonByIdPhong(phong.getMaPhong(), TrangThaiHoaDon.DANG_XU_LY);
                addRoom(panels.get(tang), new Room(phong, hoaDon));
            } else {
                addRoom(panels.get(tang), new Room(phong));
            }
            panels.get(tang).repaint();
            panels.get(tang).revalidate();
        }
    }

    public void createTabFloor(int tang) {
        tabPane.removeAll();
        tabPane.addTabButtonItem("Tất cả");
        for (int i = 0; i < tang; i++) {
            PanelEmpty tabFloor = new PanelEmpty();
//            tabFloor.setOpaque(false);
            tabFloor.setLayout(new WrapLayout(WrapLayout.LEADING, 50, 20));
            tabPane.addTabButtonItem("Tầng " + tabPane.getComponentCount());
            panels.add(tabFloor);
        }
    }
}
