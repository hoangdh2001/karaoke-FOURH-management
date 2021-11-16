package gui.component;

import gui.GD_Chinh;
import gui.swing.graphics.ShadowType;
import gui.swing.event.EventShowPopupMenu;
import gui.swing.panel.PanelShadow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Header extends PanelShadow {
    private final SimpleDateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat dfTime = new SimpleDateFormat("hh:MM:ss");
    private Thread thread;
    private boolean start = true;
    
    public void addEvent(ActionListener event) {
        btnOpenMenu.addActionListener(event);
    }
    
    public void addEvent2(EventShowPopupMenu event) {
        btnDropMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.showPopup(btnDropMenu);
            }
        });
    }
    
    public Header() {
        initComponents();
        setShadowOpacity(0.3f);
        setShadowSize(2);
        setShadowType(ShadowType.BOT);
        btnDropMenu.setTextName(GD_Chinh.NHAN_VIEN.getTenNhanVien());
        btnDropMenu.setTextRoll(GD_Chinh.NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV());
        initTime();
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

        btnDropMenu.setBorderRadius(10);
        btnDropMenu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnDropMenu.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar.png"))); // NOI18N
        btnDropMenu.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/expand_arrow_15px.png"))); // NOI18N
        btnDropMenu.setTextName("Đỗ Huy Hoàng");
        btnDropMenu.setTextRoll("Quản lý");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOpenMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 639, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTime)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDropMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnOpenMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTime))
                    .addComponent(jSeparator1))
                .addGap(7, 7, 7))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(btnDropMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.ButtonInfo btnDropMenu;
    private gui.swing.button.Button btnOpenMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
