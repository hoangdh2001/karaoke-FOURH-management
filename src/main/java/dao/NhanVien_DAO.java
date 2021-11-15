/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public NhanVien getNhanVien(String maNhanVien) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            NhanVien nhanVien = session.find(NhanVien.class, maNhanVien);
            transaction.commit();

            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
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

}
