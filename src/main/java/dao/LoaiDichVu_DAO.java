package dao;

import entity.LoaiDichVu;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoaiDichVuService;
import util.HibernateUtil;

public class LoaiDichVu_DAO implements LoaiDichVuService {
    private SessionFactory sessionFactory;

    public LoaiDichVu_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public LoaiDichVu getLoaiDichVu(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            LoaiDichVu loaiDichVu = session.find(LoaiDichVu.class, id);
            tr.commit();
            return loaiDichVu;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<LoaiDichVu> getDsLoaiDichVu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<LoaiDichVu> rs = session
                    .createNamedQuery("getDsLoaiDichVu", LoaiDichVu.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<String> getDsTenLoaiDichVu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<String> rs = session.
                    createNamedQuery("getDsTenLoaiDichVu")
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public LoaiDichVu getLoaiDichVuByMa(String ma) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                LoaiDichVu ldv = session.find(LoaiDichVu.class, ma);
            tr.commit();
            return ldv;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
}

