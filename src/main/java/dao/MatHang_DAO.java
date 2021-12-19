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
    /**
     * Thêm một mặt hàng
     * @param matHang
     * @return kq
     */
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
    
    /**
     * Cập nhật môt mặt hàng
     * @param matHang
     * @return 
     */
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
    /**
     * Xóa một mặt hàng
     * @param id: mã mặt hàng
     * @return kq
     */
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
    /**
     * Lấy một mặt hàng theo mã mặt hàng
     * @param id: maMatHang
     * @return matHang
     */
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
    /**
     * Lấy danh sách mặt hàng
     * @return dsMatHang
     */
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
    /**
     * Lấy mã mặt hàng mới nhất
     * @return id
     */
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
    /**
     * Tìm kiếm mặt hàng theo từ khóa
     * @param textFind
     * @param type
     * @return dsMatHang
     */
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
    /**
     * Lấy danh sách mặt hàng theo loại dịch vụ
     * @param id: maLoaiDichVu
     * @return dsMatHang
     */
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
    /**
     * Lấy danh sách TK theo thoiGian
     * @param batDau
     * @param ketThuc
     * @param page
     * @return 
     */
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
    /**
     * Lấy danh sách số trang theo thời gian
     * @param batDau
     * @param ketThuc
     * @return 
     */
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
    
    /**
     * Lấy số lượng trang theo thời gian
     * @param thangOrNam
     * @param thang
     * @param year
     * @return page
     */
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
    
    /**
     * Lấy danh sách TK
     * @param thangOrNam
     * @param thang
     * @param year
     * @param page
     * @return 
     */
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
    /**
     * Lấy tổng tiền mặt hàng theo thời gian
     * @param batDau
     * @param ketThuc
     * @return total
     */
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
    /**
     * Lấy tổng tiền mặt hàng theo tháng hoặc năm
     * @param thangOrNam
     * @param thang
     * @param year
     * @return 
     */
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
