package dao;

import entity.MatHang;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.MatHangService;
import util.HibernateUtil;

public class MatHang_DAO implements MatHangService {

    private SessionFactory sessionFactory;

    public MatHang_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    
    @Override
    public boolean addMatHang(MatHang matHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.save(matHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateMatHang(MatHang matHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.update(matHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deleteMatHang(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.delete(session.find(MatHang.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public MatHang getMatHang(String id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            MatHang matHang = session.find(MatHang.class, id);
            tr.commit();
            return matHang;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<MatHang> getDsMatHang() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<MatHang> rs = session
                    .createNamedQuery("getDsMatHang", MatHang.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
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
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public List<MatHang> findMatHang(String textFind, int type) {
        String sql = "select * from MatHang order by maLoaiDichVu";
        
        switch (type) {
            case 0:
                break;
            case 1:
                sql = "select * from MatHang where tenMatHang like N'%"+textFind+"%' order by maLoaiDichVu";
                break;
            case 2:
                sql = "select * from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu where ldv.tenLoaiDichVu like N'%"+textFind+"%' order by ldv.maLoaiDichVu";;
                break;
            default:
                sql = "select * from MatHang order by maLoaiDichVu";
	}
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
                List<MatHang> dsMatHang = session.createNativeQuery(sql,MatHang.class).getResultList();
            tr.commit();
            return dsMatHang;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return null;
    
    }
    
    @Override
    public List<MatHang> getDanhSachMatHangByLoaiDichVu(String id){
        
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
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<String> getListTKByDate(String batDau, String ketThuc,int page) {
        List<String> list = new ArrayList<String>();
        String sql = "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"'\n" +
        "group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu\n" +
        "order by ldv.tenLoaiDichVu OFFSET "+page*5+" ROWS\n" +
        "FETCH NEXT 5 ROWS ONLY";
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                List<?> listAs = session.createNativeQuery(sql).getResultList();
		Iterator<?> i = listAs.iterator();
		while (i.hasNext()) {
                    Object[] row = (Object[]) i.next();
                    long sl = new NhaCungCap_DAO().getSLNhapByDate(row[0].toString(), batDau, ketThuc);
                    String chuoiData = row[1]+";"+row[2]+";"+row[3]+";"+sl+";"+row[4];
                    list.add(chuoiData);
                }
            tr.commit();
            return list;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return list;
    }

    @Override
    public int getPageByDate(String batDau, String ketThuc) {
        int page = 0;
        String sql = "select COUNT(*) from ("+
        "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"'\n" +
        "group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu ) As Z";
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                page = (int)session.createNativeQuery(sql).uniqueResult();
            tr.commit();
            return page;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return page;
    }
    
    @Override
    public int getPage(int thangOrNam,Boolean thang,int year) {
        int page = 0;
        String sql = "";
        if(thang == true){
            sql = "select COUNT(*) from ("+
        "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year+
        " group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu ) As Z";
        }else{
            sql = "select COUNT(*) from ("+
        "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam+
        " group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu ) As Z";
        }
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                page = (int)session.createNativeQuery(sql).uniqueResult();
            tr.commit();
            return page;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return page;
    }
    
    @Override
    public List<String> getListTK(int thangOrNam,Boolean thang,int year,int page) {
        List<String> list = new ArrayList<String>();
        String sql = "";
        if(thang == true){
            sql = "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year +
        " group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu\n" +
        "order by ldv.tenLoaiDichVu OFFSET "+page*5+" ROWS\n" +
        "FETCH NEXT 5 ROWS ONLY";
        }else{
            sql = "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam +
        " group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu\n" +
        "order by ldv.tenLoaiDichVu OFFSET "+page*5+" ROWS\n" +
        "FETCH NEXT 5 ROWS ONLY";
        }
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                List<?> listAs = session.createNativeQuery(sql).getResultList();
		Iterator<?> i = listAs.iterator();
		while (i.hasNext()) {
                    Object[] row = (Object[]) i.next();
                    long sl = new NhaCungCap_DAO().getSLNhap(row[0].toString(), thangOrNam,thang, year);
                    String chuoiData = row[1]+";"+row[2]+";"+row[3]+";"+sl+";"+row[4];
                    list.add(chuoiData);
                }
            tr.commit();
            return list;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return list;
    }

    @Override
    public double getTotalBydate(String batDau, String ketThuc) {
        double total = 0;
        String sql = "select sum(TongTien) from ("+
        "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"'\n" +
        "group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu ) As Z";
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                BigDecimal bd= (BigDecimal)session.createNativeQuery(sql).uniqueResult();
                total = bd.doubleValue(); 
            tr.commit();
            return total;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return total;
    }

    @Override
    public double getTotal(int thangOrNam, Boolean thang, int year) {
        double total = 0;
        String sql = "";
        if(thang == true){
            sql = "select sum(TongTien) from ("+
        "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year+
        " group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu ) As Z";
        }else{
            sql = "select sum(TongTien) from ("+
        "select mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(ct.thanhTien) from MatHang as mh join LoaiDichVu as ldv on mh.maLoaiDichVu = ldv.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam+
        " group by mh.maMatHang,mh.tenMatHang,ldv.tenLoaiDichVu ) As Z";
        }
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                BigDecimal bd= (BigDecimal)session.createNativeQuery(sql).uniqueResult();
                total = bd.doubleValue(); 
            tr.commit();
            return total;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return total;
    }
}
