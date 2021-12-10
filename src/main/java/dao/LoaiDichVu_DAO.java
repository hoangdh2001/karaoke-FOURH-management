package dao;

import entity.LoaiDichVu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.LoaiDichVuService;
import util.HibernateUtil;

public class LoaiDichVu_DAO implements LoaiDichVuService {
    private SessionFactory sessionFactory;

    public LoaiDichVu_DAO() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public LoaiDichVu getLoaiDichVu(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            LoaiDichVu loaiDichVu = session.find(LoaiDichVu.class, id);
            tr.commit();
            return loaiDichVu;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<LoaiDichVu> getDsLoaiDichVu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<LoaiDichVu> rs = session
                    .createNamedQuery("getDsLoaiDichVu", LoaiDichVu.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<String> getDsTenLoaiDichVu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            List<String> rs = session.
                    createNamedQuery("getDsTenLoaiDichVu")
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public LoaiDichVu getLoaiDichVuByMa(String ma) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                LoaiDichVu ldv = session.find(LoaiDichVu.class, ma);
            tr.commit();
            return ldv;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<String> getPercent(String batDau, String ketThuc) {
        List<String> list = new ArrayList<String>();
        String sql = "select ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(thanhTien) from LoaiDichVu as ldv join MatHang as mh on ldv.maLoaiDichVu = mh.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"'\n" +
        "group by ldv.tenLoaiDichVu";
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                List<?> listAs = session.createNativeQuery(sql).getResultList();
		Iterator<?> i = listAs.iterator();
		while (i.hasNext()) {
                    Object[] row = (Object[]) i.next();
                    String chuoiData = row[0]+";"+row[1]+";"+row[2];
                    list.add(chuoiData);
                }
            tr.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return list;
    }

    @Override
    public List<String> getPercent(int thangOrNam, Boolean thang, int year) {
        List<String> list = new ArrayList<String>();
        String sql = "";
        if(thang == true){
            sql = "select ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(thanhTien) from LoaiDichVu as ldv join MatHang as mh on ldv.maLoaiDichVu = mh.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year+
        "group by ldv.tenLoaiDichVu";
        }else{
            sql = "select ldv.tenLoaiDichVu,soLuongTieuThu = sum(soLuong),TongTien = sum(thanhTien) from LoaiDichVu as ldv join MatHang as mh on ldv.maLoaiDichVu = mh.maLoaiDichVu\n" +
        "join ChiTietHoaDon as ct on mh.maMatHang = ct.maMatHang\n" +
        "join HoaDon as hd on ct.maHoaDon = hd.maHoaDon\n" +
        "where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam+
        " group by ldv.tenLoaiDichVu";
        }
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
         try {
            tr.begin();
                List<?> listAs = session.createNativeQuery(sql).getResultList();
		Iterator<?> i = listAs.iterator();
		while (i.hasNext()) {
                    Object[] row = (Object[]) i.next();
                    String chuoiData = row[0]+";"+row[1]+";"+row[2];
                    list.add(chuoiData);
                }
            tr.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return list;
    }
}

