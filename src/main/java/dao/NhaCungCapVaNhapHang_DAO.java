/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ChiTietHoaDon;
import entity.ChiTietNhapHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoHang;
import entity.LoaiDichVu;
import entity.LoaiPhong;
import entity.MatHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.Phong;
import entity.PhieuDatPhong;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.NhaCungCapVaNhapHangDaoService;
import util.HibernateUtil;
import entity.TrangThaiPhong;
import java.math.BigDecimal;

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
    public List<LoaiDichVu> getLoaiDichVu() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from LoaiDichVu";
        try {
            tr.begin();
                List<LoaiDichVu> listDV = session.createNativeQuery(sql, LoaiDichVu.class)
                    .getResultList();
            tr.commit();
            return listDV;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    
    public List<MatHang> getDanhSachMatHang(String id){
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from MatHang as mh join LoaiDichVu as lnd on mh.maLoaiDichVu = lnd.maLoaiDichVu\n" +
                    "where mh.maLoaiDichVu = '"+id+"'";
        try {
            tr.begin();
                List<MatHang> listDV = session.createNativeQuery(sql, MatHang.class)
                    .getResultList();
            tr.commit();
            return listDV;
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public KhachHang getKhachHangBySDT(String sdt) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "  select * from Khachhang where sdt = '"+sdt+"' ";
        try {
            tr.begin();
                KhachHang kh;
                try {
                    kh = session.createNativeQuery(sql,KhachHang.class).getSingleResult();
                } catch (Exception e) {
                    tr.rollback();
                    return null;
                }
            tr.commit();
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }

    @Override
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        Date date = new Date( System.currentTimeMillis());
        SimpleDateFormat formatterNgay = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "select * from PhieuDatPhong where datediff(day,NgayDat,'"+formatterNgay.format(date)+"') = 0"
                + "and maPhong = '"+maPhong+"'" +" and trangThai = 'DANG_DOI'";
        try {
            tr.begin();
                List<PhieuDatPhong> phieuDatPhong = session.createNativeQuery(sql,PhieuDatPhong.class).getResultList();
            tr.commit();
            return phieuDatPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
        
    }

    @Override
    public PhieuDatPhong getPhieuById(String maPhieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        Date date = new Date( System.currentTimeMillis());
        SimpleDateFormat formatterNgay = new SimpleDateFormat("yyyy-MM-dd");
        
        String sql = "select * from PhieuDatPhong where maPhieuDat = '"+maPhieuDatPhong+"'";
        try {
            tr.begin();
                PhieuDatPhong phieuDatPhong = session.createNativeQuery(sql,PhieuDatPhong.class).getSingleResult();
            tr.commit();
            return phieuDatPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }

    @Override
    public List<MatHang> getDanhSachMatHang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from MatHang";
        try {
            tr.begin();
                List<MatHang> dsMatHang = session.createNativeQuery(sql,MatHang.class).getResultList();
            tr.commit();
            return dsMatHang;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }

    @Override
    public NhanVien getNhanVienByID(String maNhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                NhanVien nhanVien = session.find(NhanVien.class, maNhanVien);
            tr.commit();
            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }

    @Override
    public boolean addKhachHang(KhachHang kh) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(kh);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updatePhieuDatPhong(String maPhieu) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "update PhieuDatPhong set trangThai = 'DA_TIEP_NHAN' where maPhieuDat = '"+maPhieu+"'";
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
    public boolean updateSLMatHang(String maMH,int sl,String type) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql ="";
        if(type.trim().equalsIgnoreCase("increase")){
            sql = "update MatHang set sLTonKho = sLTonKho + "+ sl +" where maMatHang = '"+maMH+"'";
        }else{
            sql = "update MatHang set sLTonKho = sLTonKho - "+ sl +" where maMatHang = '"+maMH+"'";
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
    public boolean updatePhong(String maPhong,TrangThaiPhong trangThai) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "update Phong set trangThai = '"+trangThai+"' where maPhong = '"+maPhong+"'";
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
    
    public boolean insertHoaDon(HoaDon hoaDon,double tienCoc){
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        SimpleDateFormat formatterGio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = formatterGio.format(hoaDon.getThoiGianBatDau().getTime());
        String sql = "insert into HoaDon (maHoaDon,donGiaPhong,gioHat,ngaylapHoaDon,thoiGianBatDau,thoiGianKetThuc,tongHoaDon,tongTienMatHang,maKhachHang,maNhanVien,maPhong) "
                + "values "
                + "('"+ hoaDon.getMaHoaDon() +"',"+(-tienCoc)+",0,"
                + "CAST(N'"+date+"' AS datetime),"
                + "CAST(N'"+date+"' AS datetime),"
                + "CAST(N'"+date+"' AS datetime),"
                + 0 +","
                + 0 +","
                + "'"+ hoaDon.getKhachHang().getMaKhachHang()+ "',"
                + "'"+hoaDon.getNhanVien().getMaNhanVien()+"',"
                + "'"+hoaDon.getPhong().getMaPhong()+"'"
                + ")";
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
    public MatHang getMatHang(String maMatHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                MatHang mh = session.find(MatHang.class,maMatHang);
            tr.commit();
            return mh;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean insertCTHoaDon(ChiTietHoaDon ctHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "insert into ChiTietHoaDon (maHoaDon,maMatHang,chietKhau,soLuong,thanhTien) values ("
                + "'"+ ctHoaDon.getHoaDon().getMaHoaDon()+ "',"
                + "'"+ ctHoaDon.getMatHang().getMaMatHang()+ "',"
                + ctHoaDon.getChietKhau() + ","
                + ctHoaDon.getSoLuong() + ","
                + ctHoaDon.getThanhTien()
                + ")";
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
                + "gioHat = 1,"
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
    public List<Phong> getDSPhongByTrangThai(TrangThaiPhong trangthai) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from Phong where trangThai = '"+trangthai+"' and maLoaiPhong != 'LP0003'";
        
        try {
            tr.begin();
                List<Phong> dsPhong = session.createNativeQuery(sql, Phong.class).getResultList();  
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
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
    
    public String getlastKhachHangTang(){
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maKhachHang from KhachHang order by maKhachHang desc";
        
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "KH";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("KH")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 7 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "KH0000001";
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
    public boolean updateHoaDonDoiPhong(HoaDon hoaDon, double tongTienPhong,String maPhongMoi) {
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String thoiGianBatDau = gio.format(date);
        
        String sql = "update HoaDon set maPhong = '"+maPhongMoi+"'"
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
    public List<LoaiPhong> getDSLoaiPhong() {
        String sql = "select * from LoaiPhong";
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                List<LoaiPhong> dsLoai = session.createNativeQuery(sql,LoaiPhong.class).getResultList();
            tr.commit();
            return dsLoai;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public String getLastPhieuDatPhong() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maPhieuDat from PhieuDatPhong order by maPhieuDat desc";
        
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "PD";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("PD")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 7 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "PD0000001";
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
    public List<Phong> getDSPhongChuaDat(String date,String maLoaiPhong) {
        String sql = "select * from Phong where maPhong not in (\n" +
                    "select p.maPhong from Phong p join PhieuDatPhong pdp on p.maPhong = pdp.maPhong and pdp.trangThai = 'DANG_DOI' \n" +
                    "where Cast(( CAST(N'"+date+"' AS datetime) - pdp.ngayDat) as Float) * 24.0 < 6) ";
        String sqlMa = "select * from Phong where maPhong not in (\n" +
                    "select p.maPhong from Phong p join PhieuDatPhong pdp on p.maPhong = pdp.maPhong and pdp.trangThai = 'DANG_DOI' \n" +
                    "where Cast(( CAST(N'"+date+"' AS datetime) - pdp.ngayDat) as Float) * 24.0 < 6) "
                    + "and maLoaiPhong = '"+maLoaiPhong+"'";
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            List<Phong> dsPhong = null;
            if(maLoaiPhong.equals("")){
                dsPhong = session.createNativeQuery(sql,Phong.class).getResultList();
            }else{
                dsPhong = session.createNativeQuery(sqlMa,Phong.class).getResultList();
            }
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean addPhieuDatPhong(PhieuDatPhong phieu,String ngayDat) {
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLap = gio.format(date);
        
        String sql = "INSERT [dbo].[PhieuDatPhong] ([maPhieuDat], [ngayDat], [ngayTao], [tienCoc], [trangThai], [maKhachHang], [maPhong]) "
                + "VALUES (N'"+phieu.getMaPhieuDat()+"', CAST(N'"+ngayDat+"' AS datetime),"
                + " CAST(N'"+ngayLap+"' AS datetime), "+phieu.getTienCoc()+", N'DANG_DOI', N'"+phieu.getKhachHang().getMaKhachHang()+"',"
                + " N'"+phieu.getPhong().getMaPhong()+"')";
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
    public double getTienCoc(String maPhieuDat) {
    Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select tienCoc from PhieuDatPhong where maPhieuDat = '"+maPhieuDat+"'";
        try {
            tr.begin();
                BigDecimal obj = (BigDecimal)session.createNativeQuery(sql).getSingleResult();
                double tien = obj.doubleValue();
            tr.commit();
            return tien;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return 0;
    }
    
    @Override
    public String getLastMatHang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maMatHang from MatHang order by maMatHang desc";
        
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "MH";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("MH")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 4 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "PD0001";
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
    public LoaiDichVu getLoaiDichVuByMa(String ma) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                LoaiDichVu ldv = session.find(LoaiDichVu.class, ma);
            tr.commit();
            return ldv;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean insertMatHang(MatHang matHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        matHang.setMaMatHang(getLastMatHang());
         try {
            tr.begin();
                session.save(matHang);
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
    public boolean updateMatHang(MatHang matHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        String matHangQuery = "select donGia from MatHang where maMatHang = '"+matHang.getMaMatHang()+"'";
        double donGiaCu = 0;
        try {
            tr.begin();
                BigDecimal obj = (BigDecimal)session.createNativeQuery(matHangQuery).getSingleResult();
                donGiaCu = obj.doubleValue();
            tr.commit();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        String sql = "";
        if(matHang.getDonGia() > donGiaCu){
            sql = "update MatHang set sLTonKho = sLTonKho + "+matHang.getsLTonKho()+""
                + ",donGia = "+matHang.getDonGia()+" "
                + "where maMatHang = '"+matHang.getMaMatHang()+"'";
        }else{
            sql = "update MatHang set sLTonKho = sLTonKho + "+matHang.getsLTonKho()
                + "where maMatHang = '"+matHang.getMaMatHang()+"'";
        }
        
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
    public String getLastLoHang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maLoHang from LoHang order by maLoHang desc";
        
        try {
            tr.begin();
            String maKhachCuoi="";
            String maCuoiCung = "LH";
            try {
                maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();
                int so = Integer.parseInt(maKhachCuoi.split("LH")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                
                for (int i = 0; i< 4 - soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            } catch (Exception e) {
                maCuoiCung = "LH0001";
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
}   
