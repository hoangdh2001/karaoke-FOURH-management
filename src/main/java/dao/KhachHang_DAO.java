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

    public boolean themKhachHang(KhachHang khachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.save(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
        
    public boolean capNhatKhachHang(KhachHang khachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.update(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
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

    public List<KhachHang> layDSKhachHang(String tuKhoa) {
            Session session = sessionFactory.openSession();
            Transaction tr = session.getTransaction();
            try {
                    tr.begin();
                    String sql = "select * from [dbo].[KhachHang] where [tenKhachHang] like N'%"+tuKhoa+"%' or [sdt] like '%"+tuKhoa+"'";
                    //[dbo].[ufn_removeMark]([tenKhachHang])
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
}
