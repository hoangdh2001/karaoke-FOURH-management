/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.PhieuDatPhongService;
import util.HibernateUtil;

/**
 *
 * @author Hao
 */
public class PhieuDatPhong_DAO implements PhieuDatPhongService {

    List<PhieuDatPhong> dsPhieu = Collections.emptyList();
    private SessionFactory sessionFactory;

    public PhieuDatPhong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    public PhieuDatPhong_DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PhieuDatPhong> getDsPhieuDatPhong() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select p.* from PhieuDatPhong p";
        try {
            tr.begin();
            List<PhieuDatPhong> dsPhieuDatPhong = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieuDatPhong;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public List<String> getDSTrangThaiPhieu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(p.trangThai) from PhieuDatPhong p";
        try {
            tr.begin();
            List<String> dsTrangThai = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsTrangThai;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByName(String tuKhoa) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "  select * from [dbo].[PhieuDatPhong] pp join [dbo].[Phong] p on pp.maPhong = p.maPhong join [dbo].[KhachHang] k on k.maKhachHang= pp.maKhachHang\n"
                    + "  where p.[tenPhong] like N'%" + tuKhoa + "%' or k.tenKhachHang like N'%" + tuKhoa + "%'";
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return Collections.emptyList();
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByName_TrangThai(String tuKhoa, TrangThaiPhieuDat trangThai) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = " select * from [dbo].[PhieuDatPhong] pp join [dbo].[Phong] p on pp.maPhong = p.maPhong join [dbo].[KhachHang] k on k.maKhachHang= pp.maKhachHang\n"
                    + "  where (p.[tenPhong] like N'%" + tuKhoa + "%' and pp.[trangThai]  = '" + trangThai + "' ) or (k.tenKhachHang like N'%" + tuKhoa + "%' and pp.[trangThai]  = '" + trangThai + "')";
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public boolean capNhatTrangThaiPhieu(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = " update PhieuDatPhong p set p.trangThai = 'DA_HUY'  where p.maPhieuDat = :maPhieuDat";
            session.createQuery(sql)
                    .setParameter("maPhieuDat", maPhieuDat)
                    .executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return false;
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongNgay(int ngay, int thang, int nam) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "  select * from [dbo].[PhieuDatPhong] p where YEAR(p.ngayDat) = " + nam + " and MONTH(p.ngayDat) = " + thang + " and DAY(p.ngayDat) = " + ngay + " ";
        try {
            tr.begin();
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return Collections.emptyList();
    }

    @Override
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.update(phieuDatPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        return false;
    }

    @Override
    public boolean xoaPhieuDatPhong(String maPhieuDat) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.delete(session.find(PhieuDatPhong.class, maPhieuDat));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        return false;
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tuKhoa, TrangThaiPhieuDat trangThai, int nam, int thang, int ngay) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "select * from KhachHang k join PhieuDatPhong pd ON k.maKhachHang = pd.maKhachHang join Phong p ON pd.maPhong = p.maPhong\n"
                    + "where ([dbo].[ufn_removeMark](k.tenKhachHang) like N'%" + tuKhoa + "%' and pd.trangThai = '" + trangThai + "' and (YEAR(pd.ngayDat)= " + nam + " and MONTH(pd.ngayDat)= " + thang + " and DAY(pd.ngayDat)= " + ngay + ")) \n"
                    + "	or (p.tenPhong like N'%" + tuKhoa + "%' and pd.trangThai = '" + trangThai + "' and (YEAR(pd.ngayDat)= " + nam + " and MONTH(pd.ngayDat)= " + thang + " and DAY(pd.ngayDat)= " + ngay + "))";
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return Collections.emptyList();
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByName_Ngay(String tuKhoa, int nam, int thang, int ngay) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "select * from KhachHang k join PhieuDatPhong pd ON k.maKhachHang = pd.maKhachHang join Phong p ON pd.maPhong = p.maPhong\n"
                    + "where ((k.tenKhachHang like N'%" + tuKhoa + "%') and (YEAR(pd.ngayDat)= " + nam + " and MONTH(pd.ngayDat)= " + thang + " and DAY(pd.ngayDat)= " + ngay + ")) \n"
                    + "	or ((p.tenPhong like N'%" + tuKhoa + "%') and  (YEAR(pd.ngayDat)= " + nam + " and MONTH(pd.ngayDat)= " + thang + " and DAY(pd.ngayDat)= " + ngay + "))";
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return Collections.emptyList();
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByTrangThai_Ngay(TrangThaiPhieuDat trangThai, int nam, int thang, int ngay) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "select * from PhieuDatPhong pd join Phong p ON pd.maPhong = p.maPhong\n"
                    + "where pd.trangThai = '" + trangThai + "' and (YEAR(pd.ngayDat)= " + nam + " and MONTH(pd.ngayDat)= " + thang + " and DAY(pd.ngayDat)= " + ngay + ")";
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return Collections.emptyList();
    }

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByTrangThai(TrangThaiPhieuDat trangThai) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "select * from PhieuDatPhong pd where pd.trangThai = '" + trangThai + "' ";
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return Collections.emptyList();
    }

}
