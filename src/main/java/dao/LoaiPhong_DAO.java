package dao;

import entity.LoaiPhong;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoaiPhongService;
import util.HibernateUtil;

public class LoaiPhong_DAO implements LoaiPhongService {
    
    private SessionFactory sessionFactory;

    public LoaiPhong_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    
    @Override
    public boolean addLoaiPhong(LoaiPhong loaiPhong) {
        Session sesion = sessionFactory.getCurrentSession();
        Transaction tr = sesion.getTransaction();
        
        try {
            tr.begin();
            sesion.save(loaiPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateLoaiPhong(LoaiPhong loaiPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.update(loaiPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deleteLoaiPhong(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.delete(session.find(LoaiPhong.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public LoaiPhong getLoaiPhong(String id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            LoaiPhong loaiPhong = session.find(LoaiPhong.class, id);
            tr.commit();
            return loaiPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<LoaiPhong> getDsLoaiPhong() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<LoaiPhong> rs = session
                    .createNamedQuery("getDsLoaiPhong", LoaiPhong.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    
}
