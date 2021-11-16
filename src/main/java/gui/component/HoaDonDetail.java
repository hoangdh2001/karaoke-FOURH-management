/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import entity.HoaDon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Hao
 */
public class HoaDonDetail extends javax.swing.JPanel {
    private HoaDon hoaDon;
    private PanelThongTinHoaDon pnlThongTinHoaDon;
    
    /**
     * Creates new form HoaDonDetail
     */
    public HoaDonDetail(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        pnlThongTinHoaDon = new PanelThongTinHoaDon(hoaDon);
        createTabButton();
    }
    
    private void createTabButton() {
        pnlBottom.add(pnlThongTinHoaDon);
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        btnIn = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));

        btnIn.setBackground(new java.awt.Color(255, 255, 255));
        btnIn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print_24.png"))); // NOI18N
        btnIn.setText("IN");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        add(pnlBottom, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        printRecord(pnlBottom);

    }//GEN-LAST:event_btnInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIn;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
    
    //In
    private void printRecord(JPanel panel){
            //Create printerJob Here
            PrinterJob printerJob = PrinterJob.getPrinterJob();

            printerJob.setJobName("Print Record");
            printerJob.setPrintable(new Printable() {
                @Override
                public int print(Graphics arg0, PageFormat arg1, int arg2) throws PrinterException {
                    if(arg2>0){
                        return Printable.NO_SUCH_PAGE;
                    }
                    Graphics2D graphics2D = (Graphics2D) arg0;
                    graphics2D.translate(arg1.getImageableX(), arg1.getImageableY());
                    graphics2D.scale(0.73, 0.8);
                    panel.print(graphics2D);
                    return Printable.PAGE_EXISTS;
                }
            });
            boolean returningResult = printerJob.printDialog();
           
            if(returningResult){
                try{
                    
                    printerJob.print();
                }catch(PrinterException p){
                    JOptionPane.showMessageDialog(this, "Print Error: "+p.getMessage());
                }
            }
    }

}
