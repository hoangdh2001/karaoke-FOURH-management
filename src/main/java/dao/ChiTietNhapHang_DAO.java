package dao;

import entity.ChiTietNhapHang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.ChiTietNhapHangService;
import util.HibernateUtil;

public class ChiTietNhapHang_DAO implements ChiTietNhapHangService{
    private SessionFactory sessionFactory;

    public ChiTietNhapHang_DAO() {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    /**
     * Thêm một chi tiết nhập hàng
     * @param chiTietNhapHang
     * @return 
     */
    @Override
    public boolean addChiTietNhapHang(ChiTietNhapHang chiTietNhapHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.save(chiTietNhapHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
}
