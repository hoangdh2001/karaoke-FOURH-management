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
    /**
     * Thêm một chi tiết hóa đơn
     * @param chiTietHoaDon
     * @return 
     */
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
    /**
     * Cập nhật một chi tiết hóa đơn
     * @param chiTietHoaDon
     * @return 
     */
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
    /**
     * Xóa một chi tiết hóa đơn
     * @param chiTietHoaDon
     * @return 
     */
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
    /**
     * Lấy danh sách chi tiết hóa đơn có mã hóa đơn là
     * @param id
     * @return 
     */
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
