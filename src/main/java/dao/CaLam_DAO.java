/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.CaLam;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.CaLamService;
import util.HibernateUtil;

/**
 *
 * @author NGUYE
 */
public class CaLam_DAO implements CaLamService {

    private SessionFactory sessionFactory;

    public CaLam_DAO() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public List<CaLam> getCaLams() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            List<CaLam> caLams = session.createNamedQuery("getCaLams", CaLam.class).getResultList();
            transaction.commit();

            return caLams;

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        }
        return null;
    }

}
