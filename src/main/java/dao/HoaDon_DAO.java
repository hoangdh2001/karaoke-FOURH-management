package dao;

import entity.HoaDon;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.HoaDonService;
import util.HibernateUtil;

public class HoaDon_DAO implements HoaDonService{
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
        String sql = "select h.* from [dbo].[HoaDon] h\n" +
                        "where h.ngayLapHoaDon between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')";
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
        String sql = "  select h.*\n" +
                        "  from [dbo].[HoaDon] h join [dbo].[KhachHang] k on k.maKhachHang=h.maKhachHang\n" +
                        "  where [dbo].[ufn_removeMark](k.tenKhachHang) like N'%"+tenKhachHang+"%'";
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
        String sql = "   select h.*\n" +
                            "  from [dbo].[HoaDon] h join [dbo].[Phong] p on p.maPhong=h.maPhong\n" +
                            "  where p.tenPhong like N'%"+tenPhong+"%'";
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
       String sql = "select  h.* \n" +
                            "  from [dbo].[HoaDon] h\n" +
                            "  where h."+tieuChiKhac+" like '%"+duLieu+"%'";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(month, ngayLapHoaDon) = "+thang+")";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(year, ngayLapHoaDon) = "+nam+")";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(quarter, ngayLapHoaDon) = "+quy+")";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(quarter, ngayLapHoaDon) = "+quy+" and DATEPART(month, ngayLapHoaDon) = "+thang+")";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(year, ngayLapHoaDon) = "+nam+" and DATEPART(month, ngayLapHoaDon) = "+thang+")";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(year, ngayLapHoaDon) = "+nam+" and DATEPART(quarter, ngayLapHoaDon) = "+quy+")";
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
        String sql = "select * from [dbo].[HoaDon]  \n" +
                "   where (convert(date,ngayLapHoaDon) between CONVERT(date, '"+from+"') and CONVERT(date, '"+to+"')) \n" +
                "	and (DATEPART(year, ngayLapHoaDon) = "+nam+" and DATEPART(quarter, ngayLapHoaDon) = "+quy+" and DATEPART(month, ngayLapHoaDon) = "+thang+")";
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

    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select max(maHoaDon) from HoaDon";
        
        try {
            tr.begin();
            String id = String.valueOf(session
                    .createNativeQuery(sql)
                    .getSingleResult());
            tr.commit();
            return id;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
}

