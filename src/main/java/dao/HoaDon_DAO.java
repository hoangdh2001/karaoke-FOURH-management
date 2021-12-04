package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import java.text.SimpleDateFormat;
import entity.TrangThaiHoaDon;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
import service.HoaDonService;
import util.HibernateUtil;

public class HoaDon_DAO implements HoaDonService {

    List<HoaDon> dsPhieu = Collections.emptyList();
    private SessionFactory sessionFactory;

    public HoaDon_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
//  Này để làm gì
//    public HoaDon_DAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public boolean addHoaDon(HoaDon hoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.save(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }


    @Override
    public boolean finishHoaDon(HoaDon hoaDon) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        } finally {
            session.close();
        }
        return false;
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
        String sql = "select h.* from [dbo].[HoaDon] h\n"
                + "where h.ngayLapHoaDon between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')";
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
    public List<HoaDon> getDSHoaDonByTenKhachHang(String tenKhachHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "  select h.*\n"
                + "  from [dbo].[HoaDon] h join [dbo].[KhachHang] k on k.maKhachHang=h.maKhachHang\n"
                + "  where [dbo].[ufn_removeMark](k.tenKhachHang) like N'%" + tenKhachHang + "%'";
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
    public List<HoaDon> getDSHoaDonByTenPhong(String tenPhong) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "   select h.*\n"
                + "  from [dbo].[HoaDon] h join [dbo].[Phong] p on p.maPhong=h.maPhong\n"
                + "  where p.tenPhong like N'%" + tenPhong + "%'";
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
    public List<HoaDon> getDSHoaDonByTieuChiKhac(String tieuChiKhac, String duLieu) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select  h.* \n"
                + "  from [dbo].[HoaDon] h\n"
                + "  where h." + tieuChiKhac + " like '%" + duLieu + "%'";
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
    public String layNgayLapNhoNhat() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = " select convert(varchar,MIN(ngayLapHoaDon),23) from [dbo].[HoaDon]";
        try {
            tr.begin();
            String ngayNho = (String) session.createNativeQuery(sql).uniqueResult();
            tr.commit();
            return ngayNho;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public String layNgayLapLonNhat() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select convert(varchar,MAX(ngayLapHoaDon),23) from [dbo].[HoaDon]";
        try {
            tr.begin();
            String ngayLon = (String) session.createNativeQuery(sql).uniqueResult();
            tr.commit();
            return ngayLon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public List<HoaDon> sapXepHoaDonByThang(String from, String to, int thang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(month, ngayLapHoaDon) = " + thang + ")";
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
    public List<HoaDon> sapXepHoaDonByNam(String from, String to, int nam) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(year, ngayLapHoaDon) = " + nam + ")";
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
    public List<HoaDon> sapXepHoaDonByQuy(String from, String to, int quy) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();//"+from+""+to+"
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(quarter, ngayLapHoaDon) = " + quy + ")";
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
    public List<HoaDon> sapXepHoaDonByThang_Quy(String from, String to, int thang, int quy) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(quarter, ngayLapHoaDon) = " + quy + " and DATEPART(month, ngayLapHoaDon) = " + thang + ")";
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
    public List<HoaDon> sapXepHoaDonByThang_Nam(String from, String to, int thang, int nam) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(year, ngayLapHoaDon) = " + nam + " and DATEPART(month, ngayLapHoaDon) = " + thang + ")";
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
    public List<HoaDon> sapXepHoaDonByQuy_Nam(String from, String to, int quy, int nam) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(year, ngayLapHoaDon) = " + nam + " and DATEPART(quarter, ngayLapHoaDon) = " + quy + ")";
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
    public List<HoaDon> sapXepHoaDonByThang_Quy_Nam(String from, String to, int thang, int quy, int nam) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon]  \n"
                + "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) \n"
                + "	and (DATEPART(year, ngayLapHoaDon) = " + nam + " and DATEPART(quarter, ngayLapHoaDon) = " + quy + " and DATEPART(month, ngayLapHoaDon) = " + thang + ")";
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
    public List<Integer> getDSThangTheoNgayLap() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(DATEPART(month, ngayLapHoaDon)) from [dbo].[HoaDon]";
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

    @Override
    public List<Integer> getDSNamTheoNgayLap() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(DATEPART(year, ngayLapHoaDon)) from [dbo].[HoaDon] order by DATEPART(year, ngayLapHoaDon) desc";
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

    @Override
    public List<Integer> getDSQuyTheoNgayLap() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(DATEPART(quarter, ngayLapHoaDon)) from [dbo].[HoaDon]";
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
    
//    Huu
    @Override
    public boolean insertCTHoaDon(ChiTietHoaDon ctHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(ctHoaDon);  
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    @Override
    public boolean updateCTHoaDon(ChiTietHoaDon ctHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql ="";
        if(ctHoaDon.getSoLuong() >0){
            sql = "update ChiTietHoaDon set soLuong = "+ctHoaDon.getSoLuong()+",thanhTien = "+ctHoaDon.getThanhTien()
                +"where maHoaDon = '"+ctHoaDon.getHoaDon().getMaHoaDon()+"' "
                + "and maMatHang = '" +ctHoaDon.getMatHang().getMaMatHang()+"' ";
        }else{
            sql = "delete ChiTietHoaDon"
                +" where maHoaDon = '"+ctHoaDon.getHoaDon().getMaHoaDon()+"' "
                + "and maMatHang = '"+ctHoaDon.getMatHang().getMaMatHang()+"'";
        }
        
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();  
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public HoaDon getHoaDon(Phong phong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 * from HoaDon where maPhong = '"+phong.getMaPhong()+"' order by maHoaDon desc";
        try {
            tr.begin();
                HoaDon hoadon= session.createNativeQuery(sql,HoaDon.class).getSingleResult();
            tr.commit();
            return hoadon;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon,String gioHat,double tongTienPhong,double tongTien,double tongTienMatHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLapHoaDon = gio.format(date);
        
        String sql = "update HoaDon "
                + "set donGiaPhong = "+tongTienPhong+","
                + "gioHat = '"+gioHat+"',"
                + "ngayLapHoaDon = CAST(N'"+ngayLapHoaDon+"' AS datetime)"
                + ",thoiGianKetThuc = CAST(N'"+ngayLapHoaDon+"' AS datetime)"
                + ",tongHoaDon = "+tongTien
                + ",tongTienMatHang = "+tongTienMatHang+" where maHoaDon = '"+hoaDon.getMaHoaDon()+"' ";
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();  
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return false;
    }
    
    @Override
    public String getlastMaHoaDonTang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maHoaDon from HoaDon order by maHoaDon desc";
        
        try {
            tr.begin();
                String maHoaDon = "";
                String maCuoiCung = "HD";

                try {
                    maHoaDon = (String)session.createNativeQuery(sql).uniqueResult(); 
                    int so = Integer.parseInt(maHoaDon.split("HD")[1]) + 1;
                int soChuSo = String.valueOf(so).length();

                for (int i = 0; i< 7-soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
                } catch (Exception e) {
                    maCuoiCung = "HD0000001";
                }      
            tr.commit();
            return maCuoiCung;
        } catch (Exception e) {
            e.printStackTrace();
             tr.rollback();
        }
        return null;
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
    public boolean updateHoaDonDoiPhong(HoaDon hoaDon, double tongTienPhong,String maPhongMoi) {
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String thoiGianBatDau = gio.format(date);
        
        String sql = "update HoaDon set maPhong = '"+maPhongMoi+"'"
                +",gioHat = '00:00',tongHoaDon = 0"
                + ",donGiaPhong = donGiaPhong + " +tongTienPhong
                + ",thoiGianBatDau = CAST(N'"+thoiGianBatDau+"' AS datetime)"
                + ",thoiGianKetThuc = CAST(N'"+thoiGianBatDau+"' AS datetime)"
                + " where maHoaDon = '"+hoaDon.getMaHoaDon()+"'";
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    
    @Override
    public List<HoaDon> findHoaDon(String batDau, String ketThuc,String ma) {
        String sql;
        if(ma != null){
            sql = "select * from HoaDon as hd join Phong as p on hd.maPhong = p.maPhong WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"' and p.maLoaiPhong = '"+ma+"'";
        }else{
            sql = "select * from HoaDon WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"'";
        }
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
                List<HoaDon> dsHoaDon = null;
                try {
                    dsHoaDon = session.createNativeQuery(sql,HoaDon.class).getResultList();
                } catch (Exception e) {
                    return null;
                }
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }
    
//    select *  from HoaDon where DATEPART(DAY, ngayLapHoaDon) = 9

    @Override
    public List<HoaDon> findHoaDonByThangNam(int thangOrNam,String loaiPhong,Boolean thang,int year) {
        
        String sql ="";
        
        if(loaiPhong !=null){
            if(thang == true){
                sql = "select *  from HoaDon as hd join Phong as p on hd.maPhong = p.maPhong  where DATEPART(MONTH, ngayLapHoaDon)= "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year+" and p.maLoaiPhong = '"+loaiPhong+"'";
            }else{
                sql = "select *  from HoaDon as hd join Phong as p on hd.maPhong = p.maPhong  where DATEPART(YEAR, ngayLapHoaDon)= "+thangOrNam+" and p.maLoaiPhong = '"+loaiPhong+"'";
                
            }
        }else{
            if(thang == true){
                sql= "select *  from HoaDon where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam;
            }else{
                sql= "select *  from HoaDon where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam;
            }
        }
        
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
                List<HoaDon> dsHoaDon = null;
                try {
                    dsHoaDon = session.createNativeQuery(sql,HoaDon.class).getResultList();
                } catch (Exception e) {
                    return null;
                }
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }

    @Override
    public boolean insertHoaDon(HoaDon hoaDon) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(hoaDon);
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
    public HoaDon getHoaDonByIdPhong(String id, TrangThaiHoaDon trangThai) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from HoaDon where maPhong = '" + id + "' and trangThai = '" + trangThai + "'";

        try {
            tr.begin();
            HoaDon hoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .getSingleResult();
            tr.commit();
            return hoaDon;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public Map<Integer, Double> getDoanhThuHoaDonTheoNam(int nam) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "exec doanhThuTheoNam ?";

        try {
            tr.begin();
            Map<Integer, Double> rs = session.doReturningWork(new ReturningWork<Map<Integer, Double>>() {
                @Override
                public Map<Integer, Double> execute(Connection arg0) throws SQLException {
                    PreparedStatement st = null;
                    Map<Integer, Double> map = new HashMap<>();
                    try {
                        st = arg0.prepareStatement(sql);
                        st.setInt(1, nam);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {                            
                            map.put(rs.getInt("thang"), rs.getDouble("tongTien"));
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    return map;
                }
            });
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public Map<Integer, Double> getDoanhThuHoaDonTheoThang(int thang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "";
        try {
            tr.begin();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
}
