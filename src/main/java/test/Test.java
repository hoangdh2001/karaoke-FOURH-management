/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static class TestPane extends JPanel {

        public static final Border PANEL_BORDER = new LineBorder(Color.red, 12);

        private boolean mouseInTheHouse = false;
        private boolean timedOut = false;

        private JButton react;
        private JButton trigger;

        public TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.ipadx = 200;
            gbc.ipady = 60;
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            react = new JButton("React");
            trigger = new JButton("Trigger");

            add(react, gbc);
            add(trigger, gbc);

            trigger.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseInTheHouse = true;
                    stateChanged();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseInTheHouse = false;
                }

            });
            Timer timer = new Timer(4000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timedOut = true;
                    System.out.println("!!");
                    stateChanged();
                }
            });
            timer.start();
        }

        protected void stateChanged() {
            if (mouseInTheHouse && timedOut) {
                react.setBorder(PANEL_BORDER);
            }
        }

    }

}
