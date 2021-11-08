/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiDichVu;
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
    public boolean updatePhieuDatHang(String maPhieu) {
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
    
    public boolean insertHoaDon(HoaDon hoaDon){
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        SimpleDateFormat formatterGio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = formatterGio.format(hoaDon.getThoiGianBatDau().getTime());
        String sql = "insert into HoaDon (maHoaDon,donGiaPhong,gioHat,ngaylapHoaDon,thoiGianBatDau,thoiGianKetThuc,tongHoaDon,tongTienMatHang,maKhachHang,maNhanVien,maPhong) "
                + "values "
                + "('"+ hoaDon.getMaHoaDon() +"',0,0,"
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
    public boolean updateHoaDon(HoaDon hoaDon,double tongTienPhong,double tongTien,double tongTienMatHang,String date) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "update HoaDon "
                + "set donGiaPhong = "+tongTienPhong+","
                + "gioHat = 1,"
                + "ngayLapHoaDon = CAST(N'"+date+"' AS datetime)"
                + ",thoiGianKetThuc = CAST(N'"+date+"' AS datetime)"
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
        String sql = "select * from Phong where trangThai = '"+trangthai+"'";
        
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
                String maPhong = (String)session.createNativeQuery(sql).uniqueResult();  
                int so = Integer.parseInt(maPhong.split("HD")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                String maCuoiCung = "HD";
                for (int i = 0; i< 7-soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
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
                String maKhachCuoi = (String)session.createNativeQuery(sql).uniqueResult();  
                int so = Integer.parseInt(maKhachCuoi.split("HD")[1]) + 1;
                int soChuSo = String.valueOf(so).length();
                String maCuoiCung = "KH";
                for (int i = 0; i< 7-soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
            tr.commit();
            return maCuoiCung;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
}   
