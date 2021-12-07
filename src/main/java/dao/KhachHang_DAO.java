package dao;

import java.util.List;

import entity.KhachHang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.KhachHangService;
import util.HibernateUtil;

/**
 * 
 * @author Hao
 */
public class KhachHang_DAO implements KhachHangService {

    private SessionFactory sessionFactory;

    public KhachHang_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    public KhachHang_DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Thêm khách hàng
     * @param khachHang: khách hàng muốn thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    @Override
    public boolean themKhachHang(KhachHang khachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.save(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    /**
     * Cập nhật thông tin của khách hàng
     * @param khachHang: thông tin cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    @Override
    public boolean capNhatKhachHang(KhachHang khachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.update(khachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    
    /**
     * Tìm khách hàng khi biết mã
     * @param maKhachHang mã khách hàng cần tìm
     * @return khách hàng có mã là mã truyền vào
     */
    public KhachHang getKhachHang(String maKhachHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            KhachHang khachHang = session.find(KhachHang.class, maKhachHang);
            tr.commit();

            return khachHang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return null;
    }

    /**
     * Lấy danh sách tất cả các khách hàng
     * @param numPage: số dòng dữ liệu của trang đầu tiên
     * @return danh sách khách hàng
     */
    @Override
    public List<KhachHang> getDSKhachHang(int numPage) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            String sql = "select kh.* from KhachHang kh order by kh.maKhachHang offset :x row fetch next 20 rows only";
            tr.begin();
            List<KhachHang> dsKhachHang = session
                    .createNativeQuery(sql, KhachHang.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();
            tr.commit();
            return dsKhachHang;
        } catch (Exception e) {
            System.out.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Tìm khách hàng theo số điện thoại hoặc tên, hoặc cccd 
     * @param tuKhoa: số điện thoại hoặc tên,hoặc cccd 
     * @param numPage: số dòng dữ liệu hiện thị lên trang đầu tiên
     * @return những khách hàng phù hợp với dữ liệu tìm kiếm
     */
    @Override
    public List<KhachHang> getDSKhachHangByTuKhoa(String tuKhoa, int numPage){
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "select * from [dbo].[KhachHang] kh where kh.tenKhachHang like N'%" + tuKhoa + "%' or kh.CCCD like '%" + tuKhoa + "%'  or kh.sdt like '%" + tuKhoa + "%' order by kh.maKhachHang offset :x row fetch next 20 rows only";
            List<KhachHang> dsKhachHang = session
                    .createNativeQuery(sql, KhachHang.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();
            tr.commit();
            return dsKhachHang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Xóa 1 khách hàng khi biết mã
     * @param maKhachHang: mã khách hàng
     * @return true nếu xóa thành công, false nếu xóa thất bại
     */
    @Override
    public boolean xoaKhachHang(String maKhachHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.delete(session.find(KhachHang.class, maKhachHang));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            System.err.println(e);
        }
        return false;
    }

    @Override
    public List<KhachHang> getDsKhachHangLimit(String tuKhoa) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select Top 6 * from [dbo].[KhachHang] "
                + "where tenKhachHang like N'%" + tuKhoa + "%̀̀̀̀̀̀̀' "
                + "or [dbo].[ufn_removeMark]([tenKhachHang]) like N'%" + tuKhoa + "%' "
                + "or [CCCD] like '%" + tuKhoa + "%' "
                + "or [sdt] like '%" + tuKhoa + "%' "
                + "order by tenKhachHang";

        try {
            tr.begin();
            List<KhachHang> rs = session
                    .createNativeQuery(sql, KhachHang.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maKhachHang) from KhachHang";

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
    public boolean checkKhachHang(String txt) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from KhachHang where soDienThoai = :x or CCCD = :y";

        try {
            tr.begin();
            session.createNativeQuery(sql)
                    .setParameter("x", txt)
                    .setParameter("y", txt)
                    .getSingleResult();
            tr.commit();
            return false;
        } catch (Exception e) {
            tr.rollback();
        }
        return true;
    }

    /**
     * Cập nhật số điện thoại cho khách hàng
     * @param maKhachHang: mã khách hàng
     * @param soDienThoaiMoi: số điện thoại mới
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    @Override
    public boolean capNhatKhachHang(String maKhachHang, String soDienThoaiMoi) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            String sql = "  update KhachHang\n"
                    + "  set sdt = '" + soDienThoaiMoi + "'\n"
                    + "  where maKhachHang = '" + maKhachHang + "'";
            session.createQuery(sql)
                    .executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return false;
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
    
    /**
     * tính số lượng khách hàng
     * @return số lượng khách hàng
     */
    @Override
    public int getSoLuongKhachHang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from KhachHang";
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
     * tính số lượng khách hàng thoe từ khóa
     * @param tuKhoa: từ khóa
     * @return : số lượng khách hàng phù hợp
     */
    @Override
    public int getSoLuongKhachHangByTuKhoa(String tuKhoa) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from [dbo].[KhachHang] kh where kh.tenKhachHang like N'%" + tuKhoa + "%' or kh.sdt like '%" + tuKhoa + "%'";
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
}
