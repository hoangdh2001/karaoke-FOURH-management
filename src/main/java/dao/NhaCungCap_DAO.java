package dao;

import entity.NhaCungCap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import service.NhaCungCapService;

public class NhaCungCap_DAO implements NhaCungCapService{
    private SessionFactory sessionFactory;  
    
    public NhaCungCap_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    
    @Override
    public List<NhaCungCap> getNhaCungCap() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from NhaCungCap";
        try {
            tr.begin();
                List<NhaCungCap> listNCC = session.createNativeQuery(sql, NhaCungCap.class)
                    .getResultList();
            tr.commit();
            return listNCC;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public NhaCungCap getNhaCungCapById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from NhaCungCap where maNCC = '"+id+"'" ;
        
        try {
            tr.begin();
                NhaCungCap ncc = session.createNativeQuery(sql, NhaCungCap.class)
                    .getSingleResult();
            tr.commit();
            return ncc;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean addNhaCungCap(NhaCungCap ncc) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(ncc);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateNhaCungCap(NhaCungCap ncc) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
                session.update(ncc);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    
    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maHoaDon) from HoaDon";

        try {
            tr.begin();
            String id = (String) session
                    .createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return id;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public int getSLNhapByDate(String maMatHang,String batDau, String ketThuc) {
        int sl = 0;
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select sum(soLuongNhap) from LoHang as lh \n" +
        "join ChiTietNhapHang as nh on lh.maLoHang = nh.maLoHang \n" +
        "WHERE ngayNhap BETWEEN  '"+batDau+"' and '"+ketThuc+"' and maMatHang = '"+maMatHang+"'\n" +
        "group by nh.maMatHang ";
        try {
            tr.begin();
                sl = (int)session.createNativeQuery(sql).getSingleResult();
            tr.commit();
            session.clear();
            return sl;
        } catch (Exception e) {
            tr.rollback();
            return 0;
        }
    }
    
    @Override
    public int getSLNhap(String maMatHang,int thangOrNam,Boolean thang, int year) {
        int sl = 0;
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "";
        if(thang == true){
            sql = "select sum(soLuongNhap) from LoHang as lh \n" +
        "join ChiTietNhapHang as nh on lh.maLoHang = nh.maLoHang \n" +
        "where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year +
        " group by nh.maMatHang ";
        }else{
            sql= "select sum(soLuongNhap) from LoHang as lh \n" +
        "join ChiTietNhapHang as nh on lh.maLoHang = nh.maLoHang \n" +
        "where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam +
        " group by nh.maMatHang ";
        }
        
        try {
            tr.begin();
                sl = (int)session.createNativeQuery(sql).getSingleResult();
            tr.commit();
            session.clear();
            return sl;
        } catch (Exception e) {
            tr.rollback();
            return 0;
        }
    }
    
    @Override
    public List<Integer> getAllYearExist() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(DATEPART(year, ngayNhap)) from LoHang";
        try {
            tr.begin();
            List<Integer> dsThang = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsThang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }


}   
