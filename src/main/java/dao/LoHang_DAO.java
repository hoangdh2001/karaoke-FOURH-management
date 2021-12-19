package dao;

import entity.LoHang;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoHangService;
import util.HibernateUtil;

public class LoHang_DAO implements LoHangService{
    private SessionFactory sessionFactory;  
    
    public LoHang_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    /**
     * Lấy mã lô hàng mới nhất
     * @return id: mã lô hàng
     */
    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maLoHang) from LoHang";

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
     * Thêm một lô hàng mới
     * @param loHang
     * @return boolean: kết quả thêm
     */
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
            tr.rollback();
        }
        return false;
    }
}
