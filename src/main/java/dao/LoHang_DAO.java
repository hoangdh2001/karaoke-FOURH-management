/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.LoHang;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoHangService;
import util.HibernateUtil;

/**
 *
 * @author 84975
 */
public class LoHang_DAO implements LoHangService{
    private SessionFactory sessionFactory;  
    
    public LoHang_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    
//    @Override
//    public String getLastLoHang() {
//        Session session = sessionFactory.getCurrentSession();
//        Transaction tr = session.getTransaction();
//        String sql = "select top 1 maLoHang from LoHang order by maLoHang desc";
//        
//        try {
//            tr.begin();
//            String maKhachCuoi="";
//            String maCuoiCung = "LH";
//            try {
//                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
//                int so = Integer.parseInt(maKhachCuoi.split("LH")[1]) + 1;
//                int soChuSo = String.valueOf(so).length();
//                
//                for (int i = 0; i< 4 - soChuSo; i++){
//                    maCuoiCung += "0";
//                }
//                maCuoiCung += String.valueOf(so);
//            } catch (Exception e) {
//                maCuoiCung = "LH0001";
//            } 
//            tr.commit();
//            return maCuoiCung;
//        } catch (Exception e) {
//            e.printStackTrace();
//            tr.rollback();
//        }
//        return null;
//    }

    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maLoHang) from LoHang";

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
    public boolean insertLohang(LoHang loHang) {
    Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLap = gio.format(date);
        String sql = "INSERT [dbo].[LoHang] ([maLoHang], [ngayNhap], [tongTien], [maNhanVien], [maNCC]) "
                + "VALUES (N'"+loHang.getMaLoHang()+"', "
                + "CAST(N'"+ngayLap+"' AS datetime), "+ loHang.getTongTien()+", N'"+loHang.getNguoiNhap().getMaNhanVien()+"',"
                + " N'"+loHang.getNhaCungCap().getMaNCC()+"')";
         try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            session.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
}
