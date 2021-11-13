package gui.loadtask;

import entity.Phong;
import gui.swing.progressbar.ProgressBarCustom;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class Phong_DAO_Test implements PhongServiceTest {

    private SessionFactory sessionFactory;
    private Query query;

    public Phong_DAO_Test() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public List<Phong> getDsPhong(ProgressBarCustom progressBar) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            query = session
                    .createNamedQuery("getDsPhong", Phong.class);
            LoadTask loadTask = new LoadTask(query);
            loadTask.execute();
            loadTask.addPropertyChangeListener((evt) -> {
                if ("progress".equals(evt.getPropertyName())) {
                    progressBar.setValue((int) evt.getNewValue());
                }
            });
            List<Phong> dsPhong = loadTask.getList();
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
}
