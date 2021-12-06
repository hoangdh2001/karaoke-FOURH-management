package dao;

import entity.PhieuDatPhong;
import java.text.SimpleDateFormat;
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
            //Collections.emptyList();
    private SessionFactory sessionFactory;

    public PhieuDatPhong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

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

    @Override
    public boolean capNhatTrangThaiPhieu(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "update PhieuDatPhong p set p.trangThai = 'DA_HUY'  where p.maPhieuDat = :maPhieuDat";
            session.createQuery(sql)
                    .setParameter("maPhieuDat", maPhieuDat)
                    .executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        session.close();
        return false;
    }

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

    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, Date ngayDat) {
        String sql; 
        if(ngayDat==null){
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like '%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%'";
        }else{
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n" +
                    "where Phong.tenPhong like '%"+tenPhong+"%' and KhachHang.tenKhachHang like '%"+tenKhachHang+"%' and PhieuDatPhong.trangThai like '%"+trangThai+"%' and CONVERT(date, ngayDat) = CONVERT(date, '"+ngayDat+"')";
        }
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
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
    
    
    @Override
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        java.sql.Date date = new java.sql.Date( System.currentTimeMillis());
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
}
