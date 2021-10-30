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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NhanVien getNhanVien(String maNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
