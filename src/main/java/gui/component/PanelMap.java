package gui.component;

import gui.event.EventTabSelected;
import gui.swing.panel.PanelShadow;
import gui.swing.panel.TabButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelMap extends PanelShadow {

    private JPanel pane;
    private TabButton tabPane;
    private JPanel roomMap;
    private JPanel pnlRoomMap1;
    private JPanel pnlRoomMap2;
    private JPanel pnlRoomMap3;

    public PanelMap() {
        buildMap();
    }

    private void buildMap() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 2, 0, 2));
        add(createPane(), BorderLayout.CENTER);
        add(createTabPane(), BorderLayout.NORTH);
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
                tabPane.check();
                if (index == 0) {
                    showTabPane(createRoomMap());
                    return true;
                } else if (index == 1) {
                    showTabPane(createPnlRoomMap1());
                    return true;
                }
                else if(index == 2) {
                    showTabPane(createPnlRoomMap2());
                    return true;
                }
                else if(index == 3) {
                    showTabPane(createPnlRoomMap3());
                    return true;
                }
                return false;
            }
        });
        tabPane.setBackground(Color.WHITE);
        tabPane.addTabButtonItem("Tất cả");
        tabPane.addTabButtonItem("Tầng 1");
        tabPane.addTabButtonItem("Tầng 2");
        tabPane.addTabButtonItem("Tầng 3");
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
        initRoom();
        return roomMap;
    }

    public void addRoom(Room room) {
        room.setPreferredSize(new Dimension(200, 250));
        roomMap.add(room);
    }

    public void initRoom() {
        addRoom(new Room());
        addRoom(new Room());
        addRoom(new Room());
        addRoom(new Room());
        addRoom(new Room());
        addRoom(new Room());
    }
    
    private JPanel createPnlRoomMap1() {
        pnlRoomMap1 = new JPanel();
        pnlRoomMap1.setBackground(Color.WHITE);
        return pnlRoomMap1;
    }
    
    private JPanel createPnlRoomMap2() {
        pnlRoomMap2 = new JPanel();
        pnlRoomMap2.setBackground(Color.WHITE);
        return pnlRoomMap2;
    }
    
    private JPanel createPnlRoomMap3() {
        pnlRoomMap3 = new JPanel();
        pnlRoomMap3.setBackground(Color.WHITE);
        return pnlRoomMap3;
    }
}
