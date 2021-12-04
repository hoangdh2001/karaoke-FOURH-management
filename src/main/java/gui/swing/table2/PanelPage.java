package gui.swing.table2;

import gui.swing.panel.slideshow.EventPagination;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class PanelPage extends javax.swing.JPanel {

    private MigLayout layout;
    private JPanel pnlNum;
    private JComboBox<Integer> cmbNumPage;
    private int currentIndex = 0;
    private EventPagination event;
    private int index = 1;
    private final List<Item> listBtn = new ArrayList<>();
    private JLabel lblNext;
    private JLabel lblPre;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void addEventPagination(EventPagination event) {
        this.event = event;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public PanelPage() {
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        layout = new MigLayout("insets 0", "5[center]5[center]5", "push[center]push");
        setLayout(layout);

        JLabel lblShow = new JLabel("Show");
        add(lblShow);

        cmbNumPage = new JComboBox<>();
        cmbNumPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (cmbNumPage.getSelectedIndex() != currentIndex) {
                    event.onClick(cmbNumPage.getSelectedIndex());
                    currentIndex = cmbNumPage.getSelectedIndex();
                }
            }
        });
        add(cmbNumPage);

        JButton btnPre = new JButton();
        btnPre.setIcon(new ImageIcon(getClass().getResource("/icon/double_left.png")));
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (index > 1) {
                    for (int i = 0; i < 4; i++) {
                        listBtn.get(i).setIndex(listBtn.get(i).getIndex() - 1);
                    }
                    index -= 1;
                }
                if (index == 1) {
                    lblPre.setText("");
                }
                if (cmbNumPage.getItemCount() > 4) {
                    lblNext.setText("...");
                }
            }
        });
        add(btnPre);

        lblPre = new JLabel("");
        add(lblPre);

        pnlNum = new JPanel();
        pnlNum.setLayout(new MigLayout("fill, insets 0", "", "[center]"));
        pnlNum.setOpaque(false);
        add(pnlNum);

        lblNext = new JLabel("");
        add(lblNext);

        JButton btnNext = new JButton();
        btnNext.setIcon(new ImageIcon(getClass().getResource("/icon/double_right.png")));
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if ((index + 4) <= cmbNumPage.getItemCount()) {
                    for (int i = 0; i < 4; i++) {
                        listBtn.get(i).setIndex(listBtn.get(i).getIndex() + 1);
                    }
                    index += 1;
                }
                if (cmbNumPage.getItemCount() > 4) {
                    lblPre.setText("...");
                }
                if ((index + 3) == cmbNumPage.getItemCount()) {
                    lblNext.setText("");
                }
            }
        });
        add(btnNext);

    }

    public void init(int num) {
        pnlNum.removeAll();
        cmbNumPage.removeAllItems();
        for (int i = 0; i < num; i++) {
            if (i < 4) {
                Item item = new Item(i, event);
                pnlNum.add(item);
                listBtn.add(item);

            } else {
                lblNext.setText("...");
            }
            cmbNumPage.addItem(i + 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private class Item extends JButton {

        private int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
            setText(index + 1 + "");
            repaint();
        }

        public Item(int index, EventPagination event) {
            super(index + 1 + "");
            this.index = index;
            addActionListener((ActionEvent ae) -> {
                if (this.index != currentIndex) {
                    event.onClick(this.index);
                    currentIndex = this.index;
                    cmbNumPage.setSelectedIndex(this.index);
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
