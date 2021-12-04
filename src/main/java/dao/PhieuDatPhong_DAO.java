/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
public class PhieuDatPhong_DAO implements PhieuDatPhongService{
    
    List<PhieuDatPhong> dsPhieu = new ArrayList<>();
            //Collections.emptyList();
    private SessionFactory sessionFactory;

    public PhieuDatPhong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    // Này để làm gì v
//    public PhieuDatPhong_DAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

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
                    + "where (k.tenKhachHang like N'%" + tuKhoa + "%' and pd.trangThai = '" + trangThai + "' and (YEAR(pd.ngayDat)= " + nam + " and MONTH(pd.ngayDat)= " + thang + " and DAY(pd.ngayDat)= " + ngay + ")) \n"
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

    @Override
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            PhieuDatPhong phieu = session.find(PhieuDatPhong.class, maPhieuDat);
            tr.commit();

            return phieu;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return null;
    }
    @Override
    public boolean addPhieuDatPhong(PhieuDatPhong phieu,String ngayDat) {
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLap = gio.format(date);
        
        String sql = "INSERT [dbo].[PhieuDatPhong] ([maPhieuDat], [ngayDat], [ngayTao], [tienCoc], [trangThai], [maKhachHang], [maPhong],[maNhanVien]) "
                + "VALUES (N'"+phieu.getMaPhieuDat()+"', CAST(N'"+ngayDat+"' AS datetime),"
                + " CAST(N'"+ngayLap+"' AS datetime), "+phieu.getTienCoc()+", N'DANG_DOI', N'"+phieu.getKhachHang().getMaKhachHang()+"',"
                + " N'"+phieu.getPhong().getMaPhong()+"','"+phieu.getNhanVien().getMaNhanVien()+"')";
        Session session = sessionFactory.getCurrentSession();
        
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public double getTienCoc(String maPhieuDat) {
    Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select tienCoc from PhieuDatPhong where maPhieuDat = '"+maPhieuDat+"'";
        try {
            tr.begin();
                BigDecimal obj = (BigDecimal)session.createNativeQuery(sql).getSingleResult();
                double tien = obj.doubleValue();
            tr.commit();
            return tien;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return 0;
    }
    
    @Override
    public PhieuDatPhong getPhieuCuaPhong(String maKhachhang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLap = gio.format(date);
        String sql = "select top 1 * from PhieuDatPhong where maKhachHang = '"+maKhachhang+"' "
                + "and datediff(DAY,ngayDat,'"+ngayLap+"') = 0"
                + " and trangThai = 'DA_TIEP_NHAN' order by ngayDat desc";
        try {
            tr.begin();
                PhieuDatPhong pdp = session.createNativeQuery(sql,PhieuDatPhong.class).getSingleResult();
            tr.commit();
            session.clear();
            return pdp;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public String getLastPhieuDatPhong() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maPhieuDat from PhieuDatPhong order by maPhieuDat desc";
        
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "PD";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("PD")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 7 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "PD0000001";
            } 
            tr.commit();
            return maCuoiCung;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public boolean updatePhieuDatPhong(String maPhieu) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "update PhieuDatPhong set trangThai = 'DA_TIEP_NHAN' where maPhieuDat = '"+maPhieu+"'";
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    @Override
    public PhieuDatPhong getPhieuById(String maPhieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        Date date = new Date( System.currentTimeMillis());
        SimpleDateFormat formatterNgay = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "select * from PhieuDatPhong where maPhieuDat = '"+maPhieuDatPhong+"'";
        try {
            tr.begin();
                PhieuDatPhong phieuDatPhong = session.createNativeQuery(sql,PhieuDatPhong.class).getSingleResult();
            tr.commit();
            return phieuDatPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }
     @Override
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        Date date = new Date( System.currentTimeMillis());
        SimpleDateFormat formatterNgay = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        String sql = "select * from PhieuDatPhong where datediff(day,NgayDat,'"+formatterNgay.format(date)+"') = 0 "
                + "and datediff(MINUTE,'"+formatterNgay.format(date)+"',NgayDat) > 0"
                + "and maPhong = '"+maPhong+"' and trangThai = 'DANG_DOI'";
       
        try {
            tr.begin();
                List<PhieuDatPhong> phieuDatPhong = session.createNativeQuery(sql,PhieuDatPhong.class).getResultList();
            tr.commit();
            return phieuDatPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
        
    }
}
