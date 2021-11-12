/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.PhongService;
import util.HibernateUtil;

/**
 *
 * @author Admin
 */
public class Phong_DAO implements PhongService {

    private SessionFactory sessionFactory;

    public Phong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    @Override
    public boolean addPhong(Phong phong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.save(phong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updatePhong(Phong phong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(phong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deletePhong(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.delete(session.find(Phong.class, maPhong));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public Phong getPhong(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            Phong phong = session.find(Phong.class, maPhong);
            tr.commit();
            return phong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<Phong> getDsPhong() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            List<Phong> dsPhong = session
                    .createNamedQuery("getDsPhong", Phong.class)
                    .getResultList();

            tr.commit();
            return dsPhong;

        } catch (Exception e) {
            System.out.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public int getSoLuongPhongTheoTrangThai(TrangThaiPhong trangThai) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select count(*) from Phong where trangThai = '" + trangThai + "'";
        int soLuong = 0;
        try {
            tr.begin();
            soLuong = (Integer) session.createNativeQuery(sql).getSingleResult();
            tr.commit();
        } catch (NumberFormatException e) {
            tr.rollback();
        }
        return soLuong;
    }

    @Override
    public List<Phong> getPhongByAttributes(int tang, String tenPhong, LoaiPhong loaiPhong, TrangThaiPhong trangThai) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String maLoaiPhong = loaiPhong == null ? "" : loaiPhong.getMaLoaiPhong();

        String sql = "select * from Phong "
                + "where tenPhong like N'%" + tenPhong + "%' "
                + "and maLoaiPhong like '%" + maLoaiPhong + "%' "
                + "and trangThai like '%" + (trangThai == null ? "" : trangThai) + "%' "
                + "and tang like '%"+ (tang == 0 ? "":tang) +"%'";
        try {
            tr.begin();
            List<Phong> dsPhong = session
                    .createNativeQuery(sql, Phong.class)
                    .getResultList();
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<Phong> getPhongBySDT(String sdt, int tang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select p.* from Phong p inner join HoaDon hd "
                + "on p.maPhong = hd.maPhong inner join KhachHang kh "
                + "on kh.maKhachHang = hd.maKhachHang "
                + "where sdt like '%"+ sdt +"%'"
                + "and tang like '%"+ (tang == 0 ? "":tang) +"%'";
        
        try {
            tr.begin();
            List<Phong> rs = session
                    .createNativeQuery(sql, Phong.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
}
