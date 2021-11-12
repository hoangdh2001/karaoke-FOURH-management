/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.HoaDon;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.HoaDonService;
import util.HibernateUtil;

/**
 *
 * @author Hao
 */
public class HoaDon_DAO implements HoaDonService{
    
    List<HoaDon> dsPhieu = Collections.emptyList();
    private SessionFactory sessionFactory;

        public HoaDon_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    public HoaDon_DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<HoaDon> getDsHoaDon() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select h.* from [dbo].[HoaDon] h order by h.[ngayLapHoaDon] desc";
        try {
            tr.begin();
            List<HoaDon> dsHoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .getResultList();
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public HoaDon getHoaDon(String maHoaDon) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            HoaDon hoaDon = session.find(HoaDon.class, maHoaDon);
            tr.commit();

            return hoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<HoaDon> getDSHoaDonFromDateToDate(String from, String to) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select h.* from [dbo].[HoaDon] h\n" +
                        "where h.ngayLapHoaDon between '"+from+"' and '"+to+"'";
        try {
            tr.begin();
            List<HoaDon> dsHoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .getResultList();
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }
    
}
