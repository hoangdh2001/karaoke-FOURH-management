package gui.component;

import gui.GD_Chinh;
import gui.swing.button.ButtonMenu;
import gui.swing.button.PopupMenu;
import gui.swing.event.EventMenuSelected;
import gui.swing.graphics.ShadowType;
import gui.swing.panel.PanelShadow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

public class Header extends PanelShadow {
    private final SimpleDateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat dfTime = new SimpleDateFormat("hh:mm:ss");
    private Thread thread;
    private boolean start = true;
    private EventMenuSelected event;
    private PopupMenu popupMenu;
    
    public void addEvent(ActionListener event) {
        btnOpenMenu.addActionListener(event);
    }
    
    public void addEventMenu(EventMenuSelected event) {
        this.event = event;
    }
    
    public Header() {
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        setShadowOpacity(0.3f);
        setShadowSize(2);
        setShadowType(ShadowType.BOT);
        createBtnInfo();
        createPopupMenu();
    }
    
    private void createBtnInfo() {
        btnDropMenu.setText(GD_Chinh.NHAN_VIEN.getTenNhanVien());
        if(GD_Chinh.NHAN_VIEN.isGioiTinh()) {
            btnDropMenu.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/avatar_female.png")));
        } else {
            btnDropMenu.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/avatar_male.png")));
        }
        initTime();
    }
    
    private void createPopupMenu() {
        popupMenu = new PopupMenu();
        popupMenu.setBackground(Color.WHITE);
        addItem("Hồ sơ", 0);
        addItem("Đổi mật khẩu", 1);
        addItem("Hướng dẫn", 2);
        popupMenu.addSeperator();
        addItem("Đăng xuất", 3);
    }
    
    private void addItem(String text, int index) {
        ButtonMenu item = new ButtonMenu();
        item.setText(text);
        item.setBackground(Color.WHITE);
        item.setOverColor(new Color(230, 230, 230));
        item.setBorderRadius(5);
        item.setHorizontalAlignment(ButtonMenu.LEADING);
        item.addActionListener((ActionEvent arg0) -> {
            popupMenu.setVisible(false);
            item.setBackground(item.getBackgroundColor());
            event.menuSelected(0, index);
        });
        popupMenu.addItem(item);
    }

    private void initTime() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (start) {
                    time();
                    repaint();
                    sleep();
                }
            }
        });
        thread.start();
    }

    private void time() {
        lblDate.setText(dfDate.format(new Date()));
        lblTime.setText(dfTime.format(new Date()));
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        btnOpenMenu = new gui.swing.button.Button();
        btnDropMenu = new gui.swing.button.ButtonInfo();

        setBackground(new java.awt.Color(255, 255, 255));
        setShadowOpacity(0.3F);
        setShadowSize(2);
        setShadowType(gui.swing.graphics.ShadowType.BOT);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblDate.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDate.setForeground(new java.awt.Color(127, 127, 127));
        lblDate.setText("26-10-2021");

        lblTime.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTime.setForeground(new java.awt.Color(127, 127, 127));
        lblTime.setText("08:30:40");

        btnOpenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N

        btnDropMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/expand_arrow_15px.png"))); // NOI18N
        btnDropMenu.setText("Đỗ Huy Hoàng");
        btnDropMenu.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar_male.png"))); // NOI18N
        btnDropMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOpenMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 574, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTime)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDropMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnOpenMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDropMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTime))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(7, 7, 7))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDropMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropMenuActionPerformed
        popupMenu.setVisible(true);
        popupMenu.show(btnDropMenu, 0, btnDropMenu.getHeight() + 3);
        popupMenu.setPopupSize(200, (int) popupMenu.getPreferredSize().getHeight());
    }//GEN-LAST:event_btnDropMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.ButtonInfo btnDropMenu;
    private gui.swing.button.Button btnOpenMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
