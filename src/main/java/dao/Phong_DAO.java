/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Phong;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePhong(String maPhong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
