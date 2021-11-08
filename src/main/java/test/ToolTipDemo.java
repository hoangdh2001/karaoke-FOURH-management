/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

public final class ToolTipDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                ToolTipManager.sharedInstance().setInitialDelay(2000);
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI(){
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(new JToolTipButton());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static final class JToolTipButton extends JButton{
        private static final long serialVersionUID = -5193366265809801639L;

        protected JToolTipButton(){
            super("I can haz tooltip?");
            setToolTipText("Hey man, get off me!");
        }
    }

}
