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
    public NhanVien getNhanVienByLogin(String sdt, byte[] matKhau) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from NhanVien "
                + "where sdt = '"+ sdt +"' "
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
}
