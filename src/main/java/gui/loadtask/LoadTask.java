package gui.loadtask;

import entity.Phong;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.hibernate.query.Query;

public class LoadTask extends SwingWorker<Long, Long> {

    private Query<Phong> q;
    private List<Phong> list;

    public List<Phong> getList() {
        return list;
    }
    
    public LoadTask(Query<Phong> q) {
        this.q = q;
        list = new ArrayList<Phong>();
    }

    @Override
    protected Long doInBackground() throws Exception {
        long lines = q.getResultStream().count();

        long n = lines / 100;

        int i = 0;
        int p = 0;

        for (Phong phong : q.getResultList()) {
            i++;
            list.add(phong);
            if (i > 100 && i % n == 0) {
                p++;
                setProgress(Math.min(100, p));
            }
        }

        return lines;
    }

    @Override
    protected void done() {
        try {
            long total = get();
            JOptionPane.showMessageDialog(null, "Finished! Lines total: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
