package gui.component;

import dao.Phong_DAO;
import entity.Phong;
import gui.event.EventTabSelected;
import gui.swing.panel.PanelShadow;
import gui.swing.panel.TabButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelMap extends PanelShadow {

    private JPanel pane;
    private TabButton tabPane;
    private JPanel roomMap;
    private List<JPanel> panels = new ArrayList<>();
    private Phong_DAO phong_DAO;

    public PanelMap() {
        phong_DAO = new Phong_DAO();
        buildMap();
    }

    private void buildMap() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 2, 0, 2));
        add(createTabPane(), BorderLayout.NORTH);
        add(createPane(), BorderLayout.CENTER);
    }

    private JPanel createPane() {
        pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(createRoomMap());
        return pane;
    }

    private JPanel createTabPane() {
        tabPane = new TabButton();
        tabPane.setEvent(new EventTabSelected() {
            @Override
            public boolean selected(int index, boolean selectedTab) {
                showTabPane(panels.get(index));
                tabPane.check();
                return true;
            }
        });
        tabPane.setBackground(Color.WHITE);
        
        return tabPane;
    }

    private void showTabPane(Component component) {
        pane.removeAll();
        pane.add(component);
        pane.repaint();
        pane.revalidate();
    }

    private JPanel createRoomMap() {
        roomMap = new JPanel();
        roomMap.setBackground(Color.WHITE);
        roomMap.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 20));
        tabPane.addTabButtonItem("Tất cả");
        panels.add(roomMap);
        initRoom();
        return roomMap;
    }

    public void addRoom(JPanel panel, Room room) {
        room.setPreferredSize(new Dimension(200, 250));
        panel.add(room);
    }

    public void initRoom() {
        int i = 0;
        List<Phong> dsPhong = phong_DAO.getDsPhong();

        for (int j = 0; j < dsPhong.size(); j++) {
           
            Phong phong = dsPhong.get(j);
            if(i != phong.getTang()) {
                JPanel tabFloor = createTabFloor(phong.getTang());
                panels.add(tabFloor);
            }
            addRoom(panels.get(phong.getTang()), new Room(phong));
            addRoom(roomMap, new Room(phong));
            i = phong.getTang();
            
        }
    }
    private JPanel createTabFloor(int tang) {
        JPanel tabFloor = new JPanel();
        tabFloor.setBackground(Color.WHITE);
        tabFloor.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 20));
        tabPane.addTabButtonItem("Tầng" + tang);
        return tabFloor;
    }
}
