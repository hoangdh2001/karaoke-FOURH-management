package dao;

import entity.ChiTietHoaDon;
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
    
}
