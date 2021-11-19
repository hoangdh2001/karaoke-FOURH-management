package dao;

import entity.ChiTietHoaDon;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.ChiTietHoaDonService;
import util.HibernateUtil;

public class ChiTietHoaDon_DAO implements ChiTietHoaDonService{
    private SessionFactory sessionFactory;

    public ChiTietHoaDon_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    
    @Override
    public boolean addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.save(chiTietHoaDon);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.saveOrUpdate(chiTietHoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deleteChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.delete(chiTietHoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public List<ChiTietHoaDon> getDsChiTietHoaDonByMaHoaDon(String id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<ChiTietHoaDon> rs = session
                    .createNamedQuery("getDsChiTietHoaDonByMaHoaDon", ChiTietHoaDon.class)
                    .setParameter("maHoaDon", id)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
}
