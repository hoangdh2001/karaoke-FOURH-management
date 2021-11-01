/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.LoaiNhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoaiNhanVienService;
import util.HibernateUtil;

/**
 *
 * @author NGUYE
 */
public class LoaiNhanVien_DAO implements LoaiNhanVienService {

    private SessionFactory sessionFactory;

    public LoaiNhanVien_DAO() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public List<LoaiNhanVien> getLoaiNhanViens() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            List<LoaiNhanVien> loaiNhanViens = session.createNamedQuery("getLoaiNhanViens", LoaiNhanVien.class).getResultList();

            transaction.commit();

            return loaiNhanViens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

}
