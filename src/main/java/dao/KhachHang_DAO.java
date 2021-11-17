package dao;

import java.util.List;

import entity.KhachHang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.KhachHangService;
import util.HibernateUtil;

public class KhachHang_DAO implements KhachHangService {

    private SessionFactory sessionFactory;

    public KhachHang_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    public KhachHang_DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean themKhachHang(KhachHang khachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.save(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    public KhachHang getKhachHang(String maKhachHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            KhachHang khachHang = session.find(KhachHang.class, maKhachHang);
            tr.commit();

            return khachHang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<KhachHang> getDSKhachHang() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            List<KhachHang> dsKhachHang = session
                    .createNamedQuery("getDSKhachHang", KhachHang.class)
                    .getResultList();
            tr.commit();
            return dsKhachHang;
        } catch (Exception e) {
            System.out.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public List<KhachHang> layDSKhachHang(String tuKhoa) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "select * from [dbo].[KhachHang] where [dbo].[ufn_removeMark]([tenKhachHang]) like N'%" + tuKhoa + "%' or [sdt] like '%" + tuKhoa + "'";
            List<KhachHang> dsKhachHang = session
                    .createNativeQuery(sql, KhachHang.class)
                    .getResultList();
            tr.commit();
            return dsKhachHang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public List<KhachHang> layDSKhachHang1(String tuKhoa) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            List<KhachHang> dsKhachHang = session
                    .createNamedQuery("getDSKhachHangByName", KhachHang.class)
                    .setParameter(1, tuKhoa)
                    .getResultList();
            tr.commit();
            return dsKhachHang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public boolean xoaKhachHang(String maKhachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.delete(session.find(KhachHang.class, maKhachHang));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        return false;
    }

    @Override
    public List<KhachHang> getDsKhachHangLimit(String tuKhoa) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select Top 6 * from [dbo].[KhachHang] "
                + "where tenKhachHang like N'%" + tuKhoa + "%̀̀̀̀̀̀̀' "
                + "or [dbo].[ufn_removeMark]([tenKhachHang]) like N'%" + tuKhoa + "%' "
                + "or [CCCD] like '%" + tuKhoa + "%' "
                + "or [sdt] like '%" + tuKhoa + "%' "
                + "order by tenKhachHang";

        try {
            tr.begin();
            List<KhachHang> rs = session
                    .createNativeQuery(sql, KhachHang.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maKhachHang) from KhachHang";

        try {
            tr.begin();
            String id = (String) session
                    .createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return id;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean checkKhachHang(String txt) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from KhachHang where soDienThoai = :x or CCCD = :y";

        try {
            tr.begin();
            session.createNativeQuery(sql)
                    .setParameter("x", txt)
                    .setParameter("y", txt)
                    .getSingleResult();
            tr.commit();
            return false;
        } catch (Exception e) {
            tr.rollback();
        }
        return true;
    }

    @Override
    public boolean capNhatKhachHang(String maKhachHang, String soDienThoaiMoi) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "  update KhachHang\n"
                    + "  set sdt = '" + soDienThoaiMoi + "'\n"
                    + "  where maKhachHang = '" + maKhachHang + "'";
            session.createQuery(sql)
                    .executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return false;
    }
}
