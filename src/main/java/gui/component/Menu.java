package gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.event.EventMenu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.model.ModelMenu;
import gui.swing.button.Button;
import gui.swing.menu.MenuAnimation;
import gui.swing.menu.MenuItem;
import gui.swing.scrollbar.ScrollBarCustom;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel pane;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    private JButton cmdMenu;
    private MigLayout layout;
    private JButton cmdLogout;
    private PaneExit paneExit;
    private PaneTitle paneTitle;
    
    public Menu() {
        buidMenu();
    }
    
    private void buidMenu() {
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "5[]0[]push[60]0"));
        setBackground(new Color(35, 35, 35));
        setOpaque(false);
        add(createCmdMenu(), "pos 1al 0al 100% 50");
        add(createCmdLogout(), "pos 1al 1al 100% 100, height 60!");
        add(createPaneName());
        add(createPane(), "h 700!");
        add(createPaneExit());
        
    }
    
    private PaneExit createPaneExit() {
        paneExit = new PaneExit();
        paneExit.setAlpha(1f);
        return paneExit;
    }
    
    private PaneTitle createPaneName() {
        paneTitle = new PaneTitle();
        paneTitle.setAlpha(1f);
        return paneTitle;
    }
    
    private JButton createCmdMenu() {
        Image image = new ImageIcon(getClass().getResource("/icon/menu.png")).getImage();
        cmdMenu = new Button();
        cmdMenu.setBackground(Color.decode("#56CCF2"));
        cmdMenu.setBorder(new EmptyBorder(5, 12, 5, 12));
        cmdMenu.setFocusable(false);
        cmdMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMenu.setIcon(new ImageIcon(image));
        return cmdMenu;
    }
    
    private JScrollPane createPane() {
        pane = new JPanel();
        pane.setOpaque(false);
        JScrollPane sp = new JScrollPane(pane);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setViewportBorder(null);
        sp.setBorder(null);
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        pane.setLayout(layout);
        return sp;
    }
    
    private JButton createCmdLogout() {
        cmdLogout = new JButton() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(65, 152, 216));
                g2.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20);
                super.paintComponent(grphcs);
            }
        };
        cmdLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdLogout.setContentAreaFilled(false);
        cmdLogout.setFocusable(false);
        cmdLogout.setIcon(new ImageIcon(getClass().getResource("/icon/exit.png")));
        return cmdLogout;
    }
    
    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/singing_20px.png")), "Quản lý phòng hát", "Sơ đồ phòng hát", "Danh sách phòng hát"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/deposit_20px.png")), "Quản lý đặt phòng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/customer_20px.png")), "Quản lý khách hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/bill_20px.png")), "Quản lý hóa đơn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/5.png")), "Quản lý nhân viên"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/combo_chart_20px.png")), "Báo cáo thống kê", "Thống kê doanh thu", "Thống kê hàng hóa"));
    }
    
    private void addMenu(ModelMenu menu) {
        MenuItem menuItem = new MenuItem(menu, getEventMenu(), event, pane.getComponentCount(), 50, new Font("Segoe ui", Font.PLAIN, 15));
        menuItem.setBackground(new Color(50, 50, 50));
        menuItem.setForeground(new Color(255, 255, 255));
        pane.add(menuItem, "h 50!");
    }
    
    private EventMenu getEventMenu() {
        return new EventMenu() {
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    System.out.println(enableMenu);
                    if (isShowMenu()) {
                        System.out.println(isShowMenu());
                        if (open) {
                            System.out.println(open);
                            new MenuAnimation(layout, com, 50).openMenu();
                        } else {
                            new MenuAnimation(layout, com, 50).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }
    
    public void addMenuEvent(ActionListener evt) {
        cmdMenu.addActionListener(evt);
    }
    
    public void hideallMenu() {
        for (Component com : pane.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                System.out.println("Done");
                new MenuAnimation(layout, com, 500, 50).closeMenu();
                item.setOpen(false);
            }
        }
    }
    
    public boolean isShowMenu() {
        return showMenu;
    }
    
    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }
    
    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }
    
    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }
    
    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }
    
    public void setAlpha(float alpha) {
        paneTitle.setAlpha(alpha);
        paneExit.setAlpha(alpha);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
//        GradientPaint gra = new GradientPaint(0, 0, new Color(51, 51, 51), getWidth(), 0, new Color(90, 84, 84));
//        GradientPaint gra = new GradientPaint(0, 0, new Color(0, 135, 255), getWidth(), 0, new Color(255, 255, 199));
//        GradientPaint gra = new GradientPaint(0, 0, new Color(242, 153, 74), getWidth(), 0, new Color(242, 201, 76));
        GradientPaint gra = new GradientPaint(0, 0, Color.decode("#56CCF2"), 0, getHeight(), Color.decode("#2F80ED"));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
}
