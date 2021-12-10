/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ChiTietNhapHang;
import entity.NhaCungCap;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.NhaCungCapVaNhapHangDaoService;
import util.HibernateUtil;

/**
 *
 * @author 84975
 */
public class NhaCungCapVaNhapHang_DAO implements NhaCungCapVaNhapHangDaoService{
    private SessionFactory sessionFactory;  
    
    public NhaCungCapVaNhapHang_DAO() {
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
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
// lay danh sach loai dich vu
  
    

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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public String getlastNhaCungCap() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maNCC from NhaCungCap order by maNCC desc";
        try {
            tr.begin();
                Object[] obj = (Object[])session.createNativeQuery(sql).getSingleResult();
            tr.commit();
            return (String)obj[0];
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }  

//    @Override
//    public boolean insertHoaDon(HoaDon hoaDon){
//        Session session = sessionFactory.getCurrentSession();
//        Transaction tr = session.getTransaction();
//        SimpleDateFormat formatterGio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String date = formatterGio.format(hoaDon.getThoiGianBatDau().getTime());
//        String sql = "insert into HoaDon (maHoaDon,donGiaPhong,gioHat,ngaylapHoaDon,thoiGianBatDau,thoiGianKetThuc,tongHoaDon,tongTienMatHang,maKhachHang,maNhanVien,maPhong) "
//                + "values "
//                + "('"+ hoaDon.getMaHoaDon() +"',"+(-tienCoc)+",0,"
//                + "CAST(N'"+date+"' AS datetime),"
//                + "CAST(N'"+date+"' AS datetime),"
//                + "CAST(N'"+date+"' AS datetime),"
//                + 0 +","
//                + 0 +","
//                + "'"+ hoaDon.getKhachHang().getMaKhachHang()+ "',"
//                + "'"+hoaDon.getNhanVien().getMaNhanVien()+"',"
//                + "'"+hoaDon.getPhong().getMaPhong()+"'"
//                + ")";
//        try {
//            tr.begin();
//                session.createNativeQuery(sql).executeUpdate();
//            tr.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            tr.rollback();
//        }
//        return false;
//    }


    @Override
    public boolean insertCTNhapHang(ChiTietNhapHang ctNhaphang,String maLoHang){
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLap = gio.format(date);
        String sql = "INSERT [dbo].[ChiTietNhapHang] ([maLoHang], [maMatHang], [giaNhap], [soLuongNhap], [thanhTien]) "
                + "VALUES "
                + "(N'"+maLoHang+"', N'"+ctNhaphang.getMatHang().getMaMatHang()+"', "+ctNhaphang.getGiaNhap()+","
                + " "+ctNhaphang.getSoLuongNhap()+","+ctNhaphang.getThanhTien()+")";
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
    
    @Override
    public String getLastNhaCungCap() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maNCC from NhaCungCap order by maNCC desc";
        
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "NCC";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("NCC")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 3 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "NCC001";
            } 
            tr.commit();
            session.clear();
            return maCuoiCung;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean insertNhaCungCap(NhaCungCap ncc) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        ncc.setMaNCC(getLastNhaCungCap());
        try {
            tr.begin();
                session.save(ncc);
            tr.commit();
            session.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
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
