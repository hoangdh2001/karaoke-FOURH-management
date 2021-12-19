package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DiaChiService;
import util.HibernateUtil;

public class DiaChi_DAO implements DiaChiService{
    
    private SessionFactory sessionFactory;

        public DiaChi_DAO () {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    
    /**
     * Lấy danh sách quận huyện
     * @return dsQuanHuyen
     */
    @Override
    public List<String> getDSQuanHuyen() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.quanHuyen) from DiaChiMau d order by d.quanHuyen";
        try {
            tr.begin();
            List<String> dsQuanHuyen = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsQuanHuyen;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
    /**
     * Lấy danh sách tỉnh thành
     * @return dsTinhThanh
     */
    @Override
    public List<String> getDSTinhThanh() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.tinhThanh) from DiaChiMau d order by d.tinhThanh";
        try {
            tr.begin();
            List<String> dsTinhThanh = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsTinhThanh;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
    /**
     * Lấy danh sách phường xã
     * @return 
     */
    @Override
    public List<String> getDSXaPhuong() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.xaPhuong) from DiaChiMau d order by d.xaPhuong";
        try {
            tr.begin();
            List<String> dsXaPhuong = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsXaPhuong;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
    /**
     * Lấy danh sách quan huyen theo tinh
     * @param tinh
     * @return 
     */
    @Override
    public List<String> getDSQuanHuyenByTinh(String tinh) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.[quanHuyen]) from [dbo].[DiaChiMau] d\n" +
                                    "where d.[tinhThanh] = N'"+tinh+"'";
        try {
            tr.begin();
            List<String> dsXaPhuong = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsXaPhuong;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
    /**
     * Lấy danh sách xa phường theo tỉnhThanh, quận huyện
     * @param tinh
     * @param quanHuyen
     * @return 
     */
    @Override
    public List<String> getDSXaPhuongByQuanHuyen(String tinh, String quanHuyen) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.xaPhuong)from [dbo].[DiaChiMau] d\n" +
                        "where d.tinhThanh = N'"+tinh+"' and d.quanHuyen = N'"+quanHuyen+"'";
        try {
            tr.begin();
            List<String> dsXaPhuong = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsXaPhuong;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
}
