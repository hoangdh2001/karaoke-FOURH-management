package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DiaChiMauService;
import util.HibernateUtil;

/**
 *
 * @author NGUYE
 */
public class DiaChiMau_DAO implements DiaChiMauService {

    private SessionFactory sessionFactory;

    public DiaChiMau_DAO() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public List<String> getAllTinhThanh() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT  DISTINCT(tinhThanh)  FROM  DiaChiMau";
            List<String> tinhThanhs = session.createNativeQuery(query).getResultList();

            transaction.commit();
            return tinhThanhs;

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    @Override
    public List<String> getQuanHuyenTheoTinhThanh(String tinhThanh) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT  DISTINCT(quanHuyen)  FROM DiaChiMau WHERE tinhThanh = N'" + tinhThanh + "'";
            List<String> quanHuyens = session.createNativeQuery(query).getResultList();

            transaction.commit();

            return quanHuyens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    @Override
    public List<String> getPhuongXaTheoQHTH(String quanHuyen, String tinhThanh) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT DISTINCT(xaPhuong) FROM DiaChiMau "
                    + " WHERE tinhThanh = N'" + tinhThanh + "' AND quanHuyen = N'" + quanHuyen + "'";
            List<String> xaPhuongs = session.createNativeQuery(query).getResultList();

            transaction.commit();
            return xaPhuongs;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }
}
