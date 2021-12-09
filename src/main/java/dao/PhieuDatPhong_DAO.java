package dao;

import entity.PhieuDatPhong;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.PhieuDatPhongService;
import util.HibernateUtil;

public class PhieuDatPhong_DAO implements PhieuDatPhongService{
    
    List<PhieuDatPhong> dsPhieu = new ArrayList<>();
    private SessionFactory sessionFactory;

    public PhieuDatPhong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    @Override
    public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr =  session.getTransaction();
        
        try {
            tr.begin();
            session.saveOrUpdate(phieuDatPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return  false;
    }
    
    /**
     * Lấy tất cả các phiếu đặt phòng
     * @param numPage: số dòng dữ liệu hiển thị lên trang đầu tiên
     * @return danh sách phiếu đặt phòng
     */
    @Override
    public List<PhieuDatPhong> getDsPhieuDatPhong(int numPage) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select p.* from PhieuDatPhong p order by p.maPhieuDat DESC offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<PhieuDatPhong> dsPhieuDatPhong = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();
            tr.commit();
            return dsPhieuDatPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Lấy ra tất cả trạng thái của phiếu
     * @return danh sách trạng thái phiếu
     */
    @Override
    public List<String> getDSTrangThaiPhieu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(p.trangThai) from PhieuDatPhong p";
        try {
            tr.begin();
            List<String> dsTrangThai = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsTrangThai;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    /**
     * Cập nhật trang phiếu thành 'DA_HUY' 
     * @param maPhieuDat: mã phiếu đặt
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    @Override
    public boolean capNhatTrangThaiPhieu(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "update PhieuDatPhong set trangThai = 'DA_HUY'  where maPhieuDat = '"+maPhieuDat+"'";
            int rs = session.createNativeQuery(sql)
                    .executeUpdate();
            tr.commit();
            return rs > 0 ? true : false;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return false;
    }

    /**
     * Cập nhật thông tin phiếu đặt phòng
     * @param phieuDatPhong thông tin phiếu đặt phòng mới
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    @Override
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.update(phieuDatPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        return false;
    }

    /**
     * Tìm những phiếu đặt phòng thỏa thông tin truyền vào dưới đây
     * @param tenPhong: tên phòng
     * @param tenKhachHang: tên khách hàng
     * @param trangThai: trạng thái
     * @param ngayDat: ngày đặt
     * @param numPage: số dòng dữ liệu hiển thị lên trang đầu tiên
     * @return danh sách phiếu đặt phòng
     */
    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat, int numPage) {
        String sql; 
        if(ngayDat==null){
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like N'%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%'"
                    + "order by PhieuDatPhong.maPhieuDat DESC offset :x row fetch next 20 rows only";
        }else{
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like N'%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%' and CONVERT(date, ngayDat) = CONVERT(date, '"+ngayDat+"') "
                    + "order by PhieuDatPhong.maPhieuDat DESC offset :x row fetch next 20 rows only";
        }
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return null;
    }

    @Override
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            PhieuDatPhong phieu = session.find(PhieuDatPhong.class, maPhieuDat);
            tr.commit();

            return phieu;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return null;
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
    public PhieuDatPhong getPhieuCuaPhong(String maKhachhang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLap = gio.format(date);
        String sql = "select top 1 * from PhieuDatPhong where maKhachHang = '"+maKhachhang+"' "
                + "and datediff(DAY,ngayDat,'"+ngayLap+"') = 0"
                + " and trangThai = 'DA_TIEP_NHAN' order by ngayDat desc";
        try {
            tr.begin();
                PhieuDatPhong pdp = session.createNativeQuery(sql,PhieuDatPhong.class).getSingleResult();
            tr.commit();
            session.clear();
            return pdp;
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

        String sql = "select max(maPhieuDat) from PhieuDatPhong";

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
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        Date date = new Date( System.currentTimeMillis());
        SimpleDateFormat formatterNgay = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        String sql = "select * from PhieuDatPhong where datediff(day,NgayDat,'"+formatterNgay.format(date)+"') = 0 "
                + "and datediff(MINUTE,'"+formatterNgay.format(date)+"',NgayDat) > 0"
                + "and maPhong = '"+maPhong+"' and trangThai = 'DANG_DOI'";
       
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
    public boolean updatePhieuDatPhong(PhieuDatPhong maPhieu) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.update(maPhieu);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    public int getSoLuongPhieuDatPhong() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from PhieuDatPhong";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

    /**
     * Trả về số lượng của phiếu bởi nhiều thuộc tinh truyền vào
     * @param tenPhong
     * @param tenKhachHang
     * @param trangThai
     * @param ngayDat
     * @return 
     */
    @Override
    public int getSoLuongPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql; 
        if(ngayDat==null){
            sql = "SELECT count(*) FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like N'%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%'";
        }else{
            sql = "SELECT count(*) FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like N'%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%' and CONVERT(date, ngayDat) = CONVERT(date, '"+ngayDat+"')";
        }
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return 0;
    }

    @Override
    public void capNhatTrangThaiHuy() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "  update [dbo].[PhieuDatPhong] set trangThai = 'HET_HAN' where ngayDat < GETDATE() and trangThai = 'DANG_DOI'";
        try {
            tr.begin();
            session.createNativeQuery(sql).executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
    }

}
