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
import java.util.Date;
import java.util.List;
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

    @Override
    public List<PhieuDatPhong> getDsPhieuDatPhong(int numPage) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select p.* from PhieuDatPhong p order by p.maPhieuDat DESC offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<PhieuDatPhong> dsPhieuDatPhong = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", numPage * 20)
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
    public boolean capNhatTrangThaiPhieu(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "update PhieuDatPhong p set p.trangThai = 'DA_HUY'  where p.maPhieuDat = :maPhieuDat and trangThai =  'DANG_DOI'";
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
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, Date ngayDat, int numPage) {
        String sql; 
        if(ngayDat==null){
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like '%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%'"
                    + "order by PhieuDatPhong.maPhieuDat DESC offset :x row fetch next 20 rows only";
        }else{
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like '%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%' and CONVERT(date, ngayDat) = CONVERT(date, '"+ngayDat+"')"
                    + "order by PhieuDatPhong.maPhieuDat DESC offset :x row fetch next 20 rows only";
        }
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", numPage * 20)
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
    public int getSoLuongPhieuDatPhong() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from PhieuDatPhong";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

    @Override
    public int getSoLuongPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, Date ngayDat) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql; 
        if(ngayDat==null){
            sql = "SELECT count(*) FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like '%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%'";
        }else{
            sql = "SELECT count(*) FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like '%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%' and CONVERT(date, ngayDat) = CONVERT(date, '"+ngayDat+"')";
        }
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

}
