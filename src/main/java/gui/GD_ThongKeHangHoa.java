package gui;

import gui.swing.model.ModelChart;
import java.awt.Color;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class GD_ThongKeHangHoa extends javax.swing.JPanel {

    public GD_ThongKeHangHoa() {
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        createChart();
    }
    
    private void createChart() {
        barChart.addLegend("Income", new Color(0, 108, 247));
        barChart.addLegend("Expense", new Color(104, 49, 200));
        barChart.addLegend("Profit", new Color(95, 209, 69));
        barChart.addLegend("Cost", new Color(241, 100, 120));
    }
    
    private void loadDataChart() {
        barChart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        barChart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        barChart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        barChart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        barChart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        barChart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        barChart.start();
    }
    
    private void hiddenLoading() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                barChart.setVisible(true);
            }

            @Override
            public void timingEvent(float fraction) {
                pnlLoading.setAlpha(1f - fraction);
                repaint();
            }

            @Override
            public void end() {
                pnlLoading.setVisible(false);
            }
        };
        Animator animator2 = new Animator(200, target);
        animator2.setResolution(0);
        animator2.setAcceleration(0.5f);
        animator2.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        barChart = new gui.swing.chart.Chart();
        pnlLoading = new gui.component.PanelLoading();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 5));

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(68, 68, 68));
        jLabel1.setText("Thống kê hàng hóa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 923, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
        );

        add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlCenter.setLayout(new java.awt.CardLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1055, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Bảng thống kê", jPanel2);

        jPanel3.setOpaque(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5" }));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.CardLayout());
        jPanel4.add(barChart, "card2");

        pnlLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Ellipsis-1s-58px.gif"))); // NOI18N
        jPanel4.add(pnlLoading, "card3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel3);

        pnlCenter.add(jTabbedPane1, "card2");

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1) {
            barChart.clear();
//            pnlLoading.setVisible(true);
//            barChart.setVisible(false);
            loadDataChart();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.chart.Chart barChart;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.component.PanelLoading pnlLoading;
    private gui.swing.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
