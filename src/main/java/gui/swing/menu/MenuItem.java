package gui.swing.menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gui.swing.event.EventMenu;
import gui.swing.event.EventMenuSelected;
import gui.swing.model.ModelMenu;
import net.miginfocom.swing.MigLayout;

public class MenuItem extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private float alpha;
    private ModelMenu menu;
    private boolean open;
    private EventMenuSelected eventSelected;
    private int index;
    private int height;
    private Color foreground = Color.WHITE;

    public ModelMenu getMenu() {
        return menu;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public MenuItem(ModelMenu menu, EventMenu event, EventMenuSelected eventSelected, int index, int height, Font font) {
        buidMenuItem();
        this.menu = menu;
        this.eventSelected = eventSelected;
        this.index = index;
        this.height = height;
        setOpaque(false);
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, " + height + "!]0[fill, 35!]"));
        MenuButton firstItem = new MenuButton(menu.getIcon(), menu.getMenuName(), foreground);
        firstItem.setIconTextGap(20);
        firstItem.setFont(font);
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (menu.getSubMenu().length > 0) {
                    if (event.menuPressed(MenuItem.this, !open)) {
                        open = !open;
                    }
                }
                eventSelected.menuSelected(index, -1);
            }
        });
        add(firstItem);
        int subMenuIndex = -1;
        for (String st : menu.getSubMenu()) {
            MenuButton item = new MenuButton(st, foreground);
            item.setFont(font);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    eventSelected.menuSelected(index, item.getIndex());
                }
            });
            add(item);
        }
    }

    public MenuItem(ModelMenu menu, EventMenu event, EventMenuSelected eventSelected, int index, int height) {
        buidMenuItem();
        this.menu = menu;
        this.eventSelected = eventSelected;
        this.index = index;
        this.height = height;
        setOpaque(false);
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, " + height + "!]0[fill, 35!]"));
        MenuButton firstItem = new MenuButton(menu.getIcon(), " " + menu.getMenuName(), foreground);
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (menu.getSubMenu().length > 0) {
                    if (event.menuPressed(MenuItem.this, !open)) {
                        open = !open;
                    }
                }
                eventSelected.menuSelected(index, -1);
            }
        });
        add(firstItem);
        int subMenuIndex = -1;
        for (String st : menu.getSubMenu()) {
            MenuButton item = new MenuButton(st, foreground);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    eventSelected.menuSelected(index, item.getIndex());
                }
            });
            add(item);
        }
    }

    private void buidMenuItem() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getPreferredSize().height;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(49, 54, 74));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0, 2, width, this.height);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillRect(0, this.height, width, height - this.height);
        g2.setColor(new Color(100, 100, 100));
        g2.drawLine(30, this.height, 30, height - 17);
        for (int i = 0; i < menu.getSubMenu().length; i++) {
            int y = ((i + 1) * 35 + this.height) - 17;
            g2.drawLine(30, y, 38, y);
        }
        if (menu.getSubMenu().length > 0) {
            createArrowButton(g2);
        }
        super.paintComponent(grphcs);
    }

    private void createArrowButton(Graphics2D g2) {
        int size = 4;
        int y = (height / 2) - 2;
        int x = getWidth() - 20;
        g2.setColor(new Color(230, 230, 230));
        float ay = alpha * size;
        float ay1 = (1f - alpha) * size;
        g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
        g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
    }
}
