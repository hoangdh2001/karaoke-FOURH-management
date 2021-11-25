package gui.dialog;

import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.swing.graphics.GraphicsUtilities;
import gui.swing.model.AutoID;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import service.LoaiPhongService;
import service.PhongService;

public class DL_TaoPhong extends javax.swing.JDialog {

    private String filePath = "D:\\";
    private PhongService phongService;
    private LoaiPhongService loaiPhongService;
    private BufferedImage bImage;
    private File file;

    public DL_TaoPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.phongService = new Phong_DAO();
        this.loaiPhongService = new LoaiPhong_DAO();
        initComponents();
        panelLoading1.setIcon(new ImageIcon(getClass().getResource("/icon/Ellipsis-1s-58px.gif")));
        panelLoading1.setVisible(false);
        loadDataForm();
    }

    private void loadDataForm() {
        List<LoaiPhong> dsLoaiPhong = loaiPhongService.getDsLoaiPhong();
        if (dsLoaiPhong != null) {
            ((DefaultComboBoxModel) cmbLoaiPhong.getModel()).addAll(dsLoaiPhong);
        }
        ((SpinnerNumberModel) spnTang.getModel()).setMaximum(phongService.getTang());
    }

    public void setImage(Icon icon) {
        pictureBox1.setImage(icon);
        pictureBox1.repaint();
        pictureBox1.revalidate();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DL_TaoPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spnTang = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        cmbLoaiPhong = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pictureBox1 = new gui.swing.image.PictureBox();
        panelLoading1 = new gui.component.PanelLoading();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tạo phòng");

        jLabel1.setText("Tên phòng");

        jLabel2.setText("Tầng");

        spnTang.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel3.setText("Loại phòng");

        jButton1.setText("Thêm ảnh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new java.awt.CardLayout());
        jPanel1.add(pictureBox1, "card3");
        jPanel1.add(panelLoading1, "card3");

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa ảnh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(spnTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif", "jpeg", "png");
        JFileChooser fs = new JFileChooser(new File(filePath));
        fs.setLocation(100, 100);
        int number = fs.showOpenDialog(null);
        fs.addChoosableFileFilter(filter);
        fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fs.setAcceptAllFileFilterUsed(false);
        if (number == JFileChooser.APPROVE_OPTION) {
//            filePath = 
            filePath = fs.getCurrentDirectory().getAbsolutePath();
            file = fs.getSelectedFile();
            Thread loader = new Thread(new Runnable() {

                @Override
                public void run() {
                    if (file != null) {
                        try {
                            pictureBox1.setVisible(false);
                            panelLoading1.setVisible(true);
                            sleep();
                            bImage = GraphicsUtilities.loadCompatibleImage(file.toURI().toURL());
                            ImageIcon image = new ImageIcon(file.getAbsolutePath());
                            if ((image.getImageLoadStatus() == MediaTracker.COMPLETE)) {
                                setImage(image);
                                panelLoading1.setVisible(false);
                                pictureBox1.setVisible(true);
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "File không hợp lệ!");
                            panelLoading1.setVisible(false);
                            pictureBox1.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "File không hợp lệ!");
                    }
                }
            });
            loader.start();

        } else {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setImage(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String maPhong = AutoID.generateId(phongService.getMaxId(), "PH");
        String tenPhong = txtTenPhong.getText().trim();
        int tang = Integer.parseInt(spnTang.getValue() + "");
        LoaiPhong loaiPhong = (LoaiPhong) cmbLoaiPhong.getSelectedItem();
        byte[] anhPhong = null;
        try {
            anhPhong = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            
        }
        Phong phong = new Phong(maPhong, tenPhong, TrangThaiPhong.TRONG, loaiPhong, tang, anhPhong);
        if (phongService.addPhong(phong)) {
            JOptionPane.showMessageDialog(null, "Tạo phòng thành công!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Tạo phòng thất bại!");
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbLoaiPhong;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private gui.component.PanelLoading panelLoading1;
    private gui.swing.image.PictureBox pictureBox1;
    private javax.swing.JSpinner spnTang;
    private javax.swing.JTextField txtTenPhong;
    // End of variables declaration//GEN-END:variables
}
