package dao;

import entity.MatHang;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.MatHangService;
import util.HibernateUtil;

public class MatHang_DAO implements MatHangService {

    private SessionFactory sessionFactory;

    public MatHang_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    
    @Override
    public boolean addMatHang(MatHang matHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.save(matHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateMatHang(MatHang matHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.update(matHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deleteMatHang(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.delete(session.find(MatHang.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public MatHang getMatHang(String id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            MatHang matHang = session.find(MatHang.class, id);
            tr.commit();
            return matHang;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<MatHang> getDsMatHang() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<MatHang> rs = session
                    .createNamedQuery("getDsMatHang", MatHang.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

}
