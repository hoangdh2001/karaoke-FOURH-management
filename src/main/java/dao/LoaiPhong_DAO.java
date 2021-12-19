package dao;

import entity.LoaiPhong;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoaiPhongService;
import util.HibernateUtil;

public class LoaiPhong_DAO implements LoaiPhongService {
    
    private SessionFactory sessionFactory;

    public LoaiPhong_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    /**
     * Thêm loại phòng
     * @param loaiPhong
     * @return kq
     */
    @Override
    public boolean addLoaiPhong(LoaiPhong loaiPhong) {
        Session sesion = sessionFactory.getCurrentSession();
        Transaction tr = sesion.getTransaction();
        
        try {
            tr.begin();
            sesion.save(loaiPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    
    /**
     * Cập nhật loại phòng
     * @param loaiPhong
     * @return kq
     */
    @Override
    public boolean updateLoaiPhong(LoaiPhong loaiPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.update(loaiPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    /**
     * Xóa một loại phòng theo mã loại phòng
     * @param id
     * @return kq
     */
    @Override
    public boolean deleteLoaiPhong(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.delete(session.find(LoaiPhong.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    /**
     * Lấy loại phòng theo mã loại phòng
     * @param id
     * @return kq 
     */
    @Override
    public LoaiPhong getLoaiPhong(String id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            LoaiPhong loaiPhong = session.find(LoaiPhong.class, id);
            tr.commit();
            return loaiPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy loại phòng theo tên loại phòng
     * @param name
     * @return loaiPhong
     */
    @Override
    public LoaiPhong getLoaiPhongByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from LoaiPhong where tenLoaiPhong = :x";
        
        try {
            tr.begin();
            LoaiPhong loaiPhong = session
                    .createNativeQuery(sql, LoaiPhong.class)
                    .setParameter("x", name)
                    .getSingleResult();
            tr.commit();
            return loaiPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy danh sách loại phòng
     * @return dsLoaiPhong
     */
    @Override
    public List<LoaiPhong> getDsLoaiPhong() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<LoaiPhong> rs = session
                    .createNamedQuery("getDsLoaiPhong", LoaiPhong.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
}
