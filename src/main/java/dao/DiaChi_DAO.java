/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DiaChiService;
import util.HibernateUtil;

/**
 *
 * @author Hao
 */
public class DiaChi_DAO implements DiaChiService{
    
    private SessionFactory sessionFactory;

        public DiaChi_DAO () {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    @Override
    public List<String> getDSQuanHuyen() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.quanHuyen) from DiaChiMau d order by d.quanHuyen";
        try {
            tr.begin();
            List<String> dsQuanHuyen = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsQuanHuyen;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public List<String> getDSTinhThanh() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.tinhThanh) from DiaChiMau d order by d.tinhThanh";
        try {
            tr.begin();
            List<String> dsTinhThanh = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsTinhThanh;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public List<String> getDSXaPhuong() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.xaPhuong) from DiaChiMau d order by d.xaPhuong";
        try {
            tr.begin();
            List<String> dsXaPhuong = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsXaPhuong;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public List<String> getDSQuanHuyenByTinh(String tinh) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.[quanHuyen]) from [dbo].[DiaChiMau] d\n" +
                                    "where d.[tinhThanh] = N'"+tinh+"'";
        try {
            tr.begin();
            List<String> dsXaPhuong = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsXaPhuong;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public List<String> getDSXaPhuongByQuanHuyen(String tinh, String quanHuyen) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(d.xaPhuong)from [dbo].[DiaChiMau] d\n" +
                        "where d.tinhThanh = N'"+tinh+"' and d.quanHuyen = N'"+quanHuyen+"'";
        try {
            tr.begin();
            List<String> dsXaPhuong = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return  dsXaPhuong;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }
}
