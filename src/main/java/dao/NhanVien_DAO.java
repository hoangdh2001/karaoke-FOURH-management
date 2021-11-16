package dao;

import entity.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.NhanVienService;
import util.HibernateUtil;

/**
 *
 * @author NGUYE
 */
public class NhanVien_DAO implements NhanVienService {

    private SessionFactory sessionFactory;

    public NhanVien_DAO() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public boolean checkConnect() {
        return sessionFactory.openSession().isConnected();
    }

    @Override
    public boolean addNhanVien(NhanVien nhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.save(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        }
        return false;
    }

    @Override
    public boolean updateNhanVien(NhanVien nhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deleteNhanVien(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.delete(session.find(NhanVien.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public NhanVien getNhanVien(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            NhanVien nhanVien = session.find(NhanVien.class, id);
            transaction.commit();

            return nhanVien;
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        return null;
    }

    @Override
    public List<NhanVien> getNhanViens() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            List<NhanVien> nhanViens = session.createNamedQuery("getDSNhanVien", NhanVien.class).getResultList();

            transaction.commit();

            return nhanViens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }

    @Override
    public NhanVien getLastNhanVien() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM NhanVien "
                    + " WHERE maNhanVien = (SELECT MAX(maNhanVien) FROM NhanVien)";
            NhanVien nhanVien = session.createNativeQuery(query, NhanVien.class).getSingleResult();
            transaction.commit();

            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;

    }

    @Override
    public NhanVien getNhanVienByLogin(String sdt, byte[] matKhau) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from NhanVien "
                + "where sdt = '" + sdt + "' "
                + "and matKhau = :x";

        try {
            tr.begin();
            NhanVien nhanVien = session
                    .createNativeQuery(sql, NhanVien.class)
                    .setParameter("x", matKhau)
                    .getSingleResult();
            tr.commit();
            return nhanVien;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<NhanVien> searchNhanVien(String textSearch, String searchOption, int gioiTinh, String maLoaiNV, String maCaLam) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        int gioiTinh0 = gioiTinh;
        int gioiTinh1 = gioiTinh;
        if (gioiTinh == 2) { // gán lại giá trị cho gioiTinh0 và gioiTinh1 để lấy dc tất cả
            gioiTinh0 = 0;
            gioiTinh1 = 1;
        }

        String query = "select * from NhanVien where  "
                + "tenNhanVien like N'%" + textSearch + "%'"
                + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                + "   and maCa like '%" + maCaLam + "%'"
                + "and maLoaiNhanVien like '%" + maLoaiNV + "%'";

        if (searchOption == "Mã nhân viên") {
            query = "select * from NhanVien where  "
                    + "maNhanVien like '%" + textSearch + "%'"
                    + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                    + "   and maCa like '%" + maCaLam + "%'"
                    + "and maLoaiNhanVien like '%" + maLoaiNV + "%'";
        } else if (searchOption == "Căn cước công dân") {
            query = "select * from NhanVien where  "
                    + "cccd like '%" + textSearch + "%'"
                    + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                    + "   and maCa like '%" + maCaLam + "%'"
                    + "and maLoaiNhanVien like '%" + maLoaiNV + "%'";
        }

        try {
            transaction.begin();

            List<NhanVien> nhanViens = session.createNativeQuery(query, NhanVien.class).getResultList();
            transaction.commit();

            return nhanViens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;

    }

    @Override
    public NhanVien getNhanVienBySdtOrEmail(String sdtOrEmail) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from NhanVien "
                + "where sdt = :x "
                + "or email = :x";

        try {
            tr.begin();
            NhanVien nhanVien = session
                    .createNativeQuery(sql, NhanVien.class)
                    .setParameter("x", sdtOrEmail)
                    .getSingleResult();
            tr.commit();
            return nhanVien;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<String> getMaNhanVienQuanLy() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
         String sql = "select n.maNhanVien from [dbo].[NhanVien] n\n" +
                            "  where maLoaiNhanVien = 'LNV005'";
        try {
            tr.begin();
            List<String> dsMa = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsMa;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    /**
     * Kiểm tra trùng số điện thoại
     *
     * @param sdt
     * @return true: trùng, false: chưa có
     */
    @Override
    public boolean checkSDT(String sdt) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM NhanVien WHERE sdt = '" + sdt + "'";
            NhanVien nhanVien = session.createNativeQuery(query, NhanVien.class).getSingleResult();

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    /**
     * Kiểm tra trùng căn cước công dân
     *
     * @param cccd
     * @return true: trùng, false: chưa có
     */
    @Override
    public boolean checkCCCD(String cccd) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM NhanVien WHERE cccd = '" + cccd + "'";
            NhanVien nhanVien = session.createNativeQuery(query, NhanVien.class).getSingleResult();
            transaction.commit();

           return true;
        } catch (Exception e) {
            transaction.rollback();
        }

        return false;
    }

}
