package test;

import javax.swing.*;

public class ProgressBarDemo extends JPanel {

    // 1. Create a instance variable of JProgressBar
    JProgressBar progressBar;

    // 2. Constructor call to ProgressBarDemo will 
    // create a object of JProgressBar and set 
    // minimum and maximum value of progress bar 
    // to 0 and 100
    public ProgressBarDemo() {

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        // 3. Add Progress bar to Panel 
        add(progressBar);

    }

    // 4. progress method will update the value of 
    // progress bar every 100 ms after executing 
    // sleep method
    public void progress(int value) {

        progressBar.setValue(value);

    }

    public static void main(String args[]) {

        // 5. Create a object of customized JPanel
        final ProgressBarDemo progressBarDemo = new ProgressBarDemo();

        // 6. Create a Object of JFrame and setting title as 
        // "Progress Bar Demo"
        JFrame frame = new JFrame("Progress Bar Demo");

        //	Give frame a size in pixels as say 300,300 
        frame.setSize(200, 100);

        // 7. set a operation when a user close the frame, here it 
        // closes the frame and exits the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 8. Setting the Content Pane as ProgressBarDemo
        frame.setContentPane(progressBarDemo);

        // 9. Packing all the components together
        frame.pack();

        // 10. after setting contentPane, we make it 
        // visible on the frame by calling the method as 
        // setVisible and passing value as true.
        frame.setVisible(true);

        // 11. Having for loop which will loop from 0 to 100
        // it will invoke a dedicated thread to Swing Component
        // "JProgressBar" and on each loop will update the value
        // of JProgressBar by 1. It will accomplish this by calling 
        // progressBarDemo.progress method and pass the updated or
        // completed status to it.
        for (int i = 0; i <= 100; i++) {
            final int updateCompleted = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBarDemo.progress(updateCompleted);
                    }
                });
                // 12. Giving each loop a pause of 100 ms and 
                // updating JProgressBar meter by 1.
                Thread.sleep(100);
            } catch (InterruptedException e) {;
            }
        }
    }
}
