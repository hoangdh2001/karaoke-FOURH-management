package dao;

import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiHoaDon;
import entity.TrangThaiPhong;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.PhongService;
import util.HibernateUtil;

public class Phong_DAO implements PhongService {

    private SessionFactory sessionFactory;

    public Phong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    /**
     * Thêm hoặc cập nhật một phòng 
     * @param phong
     * @return true: thêm thành công, false: thêm thất bại
     */
    @Override
    public boolean addPhong(Phong phong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.saveOrUpdate(phong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    /**
     * Cập nhật phòng
     * @param phong
     * @return true: cập nhật thành công, false: cập nhật thất bại
     */
    @Override
    public boolean updatePhong(Phong phong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(phong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    /**
     * Xóa một phòng theo mã phòng
     * @param maPhong
     * @return true: Xóa thành công, false: Xóa thất bại
     */
    @Override
    public boolean deletePhong(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.delete(session.find(Phong.class, maPhong));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    /**
     * Lấy phòng theo mã phòng
     * @param maPhong
     * @return phong
     */
    @Override
    public Phong getPhong(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            Phong phong = session.find(Phong.class, maPhong);
            tr.commit();
            return phong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy danh sách phòng theo tham số
     * @param numPage
     * @param tenPhong
     * @param loaiPhong
     * @return 
     */
    @Override
    public List<Phong> getDsPhong(int numPage, String tenPhong, String loaiPhong) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select p.* from Phong p where tenPhong like N'%" + tenPhong + "%' "
                + "and maLoaiPhong like '%" + loaiPhong + "%' "
                + "order by maPhong offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<Phong> dsPhong = session
                    .createNativeQuery(sql, Phong.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();

            tr.commit();
            return dsPhong;

        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
    /**
     * Lấy số lượng phòng
     * @param tenPhong
     * @param loaiPhong
     * @return soLuong
     */
    @Override
    public int getSoLuongPhong(String tenPhong, String loaiPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select count(*) from Phong where tenPhong like N'%" + tenPhong + "%' "
                + "and maLoaiPhong like '%" + loaiPhong + "%'";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }
    /**
     * Lấy số lượng phòng theo trạng thái
     * @param trangThai
     * @return soLuong
     */
    @Override
    public int getSoLuongPhongTheoTrangThai(TrangThaiPhong trangThai) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select count(*) from Phong where trangThai = '" + trangThai + "'";
        int soLuong = 0;
        try {
            tr.begin();
            soLuong = (Integer) session.createNativeQuery(sql).getSingleResult();
            tr.commit();
        } catch (NumberFormatException e) {
            tr.rollback();
        }
        return soLuong;
    }
    /**
     * Lấy danh sách phòng theo các thuộc tính phòng
     * @param tang
     * @param tenPhong
     * @param loaiPhong
     * @param trangThai
     * @return dsPhong
     */
    @Override
    public List<Phong> getPhongByAttributes(int tang, String tenPhong, LoaiPhong loaiPhong, TrangThaiPhong trangThai) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String maLoaiPhong = loaiPhong == null ? "" : loaiPhong.getMaLoaiPhong();

        String sql = "select * from Phong "
                + "where tenPhong like N'%" + tenPhong + "%' "
                + "and maLoaiPhong like '%" + maLoaiPhong + "%' "
                + "and trangThai like '%" + (trangThai == null ? "" : trangThai) + "%' "
                + "and tang like '%" + (tang == 0 ? "" : tang) + "%'";
        try {
            tr.begin();
            List<Phong> dsPhong = session
                    .createNativeQuery(sql, Phong.class)
                    .getResultList();
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy danh sách phòng theo hóa đơn có tên khách hàng hoặc số điện thoại
     * @param sdtOrTen
     * @param tang
     * @return dsPhong
     */
    @Override
    public List<Phong> getDsPhongBySDTOrTen(String sdtOrTen, int tang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select p.* from Phong p inner join HoaDon hd "
                + "on p.maPhong = hd.maPhong inner join KhachHang kh "
                + "on kh.maKhachHang = hd.maKhachHang "
                + "where (sdt like '%" + sdtOrTen + "%' "
                + "or tenKhachHang like N'%" + sdtOrTen + "%' "
                + "or dbo.ufn_removeMark(tenKhachHang) like N'%" + sdtOrTen + "%') "
                + "and tang like '%" + (tang == 0 ? "" : tang) + "%' "
                + "and hd.trangThai = '" + TrangThaiHoaDon.DANG_XU_LY + "'";

        try {
            tr.begin();
            List<Phong> rs = session
                    .createNativeQuery(sql, Phong.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy tầng cao nhất
     * @return tang
     */
    @Override
    public int getTang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(tang) from  Phong";

        try {
            tr.begin();
            int tang = Integer.valueOf(String.valueOf(session.createNativeQuery(sql).getSingleResult()));
            tr.commit();
            return tang;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }
    /**
     * Lấy danh sách phòng theo tầng, tên phòng, loại phòng.
     * @param tang
     * @param tenPhong
     * @param loaiPhong
     * @return dsPhong
     */
    @Override
    public List<Phong> getDsPhongByTang(int tang, String tenPhong, LoaiPhong loaiPhong) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String maLoaiPhong = loaiPhong == null ? "" : loaiPhong.getMaLoaiPhong();

        String sql = "select * from Phong where tang like '%" + (tang == 0 ? "" : tang) + "%' "
                + "and maLoaiPhong like '%" + maLoaiPhong + "%' "
                + "and (tenPhong like '%" + tenPhong + "%' "
                + "or dbo.ufn_removeMark(tenPhong) like '%" + tenPhong + "%')";

        try {
            tr.begin();
            List<Phong> rs = session
                    .createNativeQuery(sql, Phong.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        } finally {
            session.close();
        }
        return null;
    }
    /**
     * Lấy danh sách phòng theo trạng thái phòng
     * @param trangthai
     * @return 
     */
    @Override
    public List<Phong> getDSPhongByTrangThai(TrangThaiPhong trangthai) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from Phong where trangThai = '" + trangthai + "' and maLoaiPhong != 'LP0003'";

        try {
            tr.begin();
            List<Phong> dsPhong = session.createNativeQuery(sql, Phong.class).getResultList();
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy danh sách phòng chưa đặt, và không phải đang hát
     * @param date
     * @param maLoaiPhong
     * @return dsPhong
     */
    @Override
    public List<Phong> getDSPhongChuaDat(String date, String maLoaiPhong) {
        String sql = "select * from Phong where maPhong not in (\n"
                + "select p.maPhong from Phong p join PhieuDatPhong pdp on p.maPhong = pdp.maPhong and pdp.trangThai = 'DANG_DOI' \n"
                + "where Cast(( CAST(N'" + date + "' AS datetime) - pdp.ngayDat) as Float) * 24.0 < 6) and maPhong in(\n"
                + "	select maPhong from Phong where trangThai = 'TRONG'\n"
                + ")";
        String sqlMa = "select * from Phong where maPhong not in (\n"
                + "select p.maPhong from Phong p join PhieuDatPhong pdp on p.maPhong = pdp.maPhong and pdp.trangThai = 'DANG_DOI' \n"
                + "where Cast(( CAST(N'" + date + "' AS datetime) - pdp.ngayDat) as Float) * 24.0 < 6) "
                + "and maLoaiPhong = '" + maLoaiPhong + "' and maPhong in(\n"
                + "	select maPhong from Phong where trangThai = 'TRONG'\n"
                + ")";
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            List<Phong> dsPhong = null;
            if (maLoaiPhong.equals("")) {
                dsPhong = session.createNativeQuery(sql, Phong.class).getResultList();
            } else {
                dsPhong = session.createNativeQuery(sqlMa, Phong.class).getResultList();
            }
            tr.commit();
            return dsPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy mã phòng mới nhất
     * @return id: mã phòng
     */
    @Override
    public String getMaxId() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maPhong) from Phong";

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
    /**
     * Cập nhật tất cả phòng và phiếu khi phiếu hết hạn
     */
    @Override
    public void updatePhongByPhieu() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "exec updateAll";

        try {
            tr.begin();
            session.createNativeQuery(sql).executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
    }

}
