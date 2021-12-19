package dao;

import entity.LoaiNhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoaiNhanVienService;
import util.HibernateUtil;

public class LoaiNhanVien_DAO implements LoaiNhanVienService {

    private SessionFactory sessionFactory;

    public LoaiNhanVien_DAO() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }
    /**
     * Lấy danh sách nhân viên
     * @return dsNhanVien
     */
    @Override
    public List<LoaiNhanVien> getLoaiNhanViens() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            List<LoaiNhanVien> loaiNhanViens = session.createNamedQuery("getLoaiNhanViens", LoaiNhanVien.class).getResultList();

            transaction.commit();

            return loaiNhanViens;
        } catch (Exception e) {
            transaction.rollback();
        }

        return null;
    }

}
