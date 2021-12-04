package dao;

import entity.MatHang;
import java.math.BigDecimal;
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
    public List<MatHang> layDsMatHangTheoTen(String tenMatHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from MatHang where tenMatHang = :x";
        
        try {
            tr.begin();
            List<MatHang> rs = session
                    .createNativeQuery(sql, MatHang.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public boolean updateSLMatHang(String maMH,int sl,String type) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql ="";
        if(type.trim().equalsIgnoreCase("increase")){
            sql = "update MatHang set sLTonKho = sLTonKho + "+ sl +" where maMatHang = '"+maMH+"'";
        }else{
            sql = "update MatHang set sLTonKho = sLTonKho - "+ sl +" where maMatHang = '"+maMH+"'";
        }
            
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
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public boolean insertMatHang(MatHang matHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        matHang.setMaMatHang(getLastMatHang());
         try {
            tr.begin();
                session.save(matHang);
            tr.commit();
            session.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    
    @Override
    public boolean updateDonGiaMatHang(MatHang matHang) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        String matHangQuery = "select donGia from MatHang where maMatHang = '"+matHang.getMaMatHang()+"'";
        double donGiaCu = 0;
        try {
            tr.begin();
                BigDecimal obj = (BigDecimal)session.createNativeQuery(matHangQuery).getSingleResult();
                donGiaCu = obj.doubleValue();
            tr.commit();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        String sql = "";
        if(matHang.getDonGia() > donGiaCu){
            sql = "update MatHang set sLTonKho = sLTonKho + "+matHang.getsLTonKho()+""
                + ",donGia = "+matHang.getDonGia()+" "
                + "where maMatHang = '"+matHang.getMaMatHang()+"'";
        }else{
            sql = "update MatHang set sLTonKho = sLTonKho + "+matHang.getsLTonKho()
                + "where maMatHang = '"+matHang.getMaMatHang()+"'";
        }
         try {
             
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            session.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
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
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    
    }
    
    @Override
    public List<MatHang> getDanhSachMatHang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from MatHang order by maLoaiDichVu ";
        try {
            tr.begin();
                List<MatHang> dsMatHang = session.createNativeQuery(sql,MatHang.class).getResultList();
            tr.commit();
            return dsMatHang;
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
}
