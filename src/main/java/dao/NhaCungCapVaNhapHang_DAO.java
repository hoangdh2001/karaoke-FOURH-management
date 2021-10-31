/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.LoaiDichVu;
import entity.MatHang;
import entity.NhaCungCap;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.NhaCungCapVaNhapHangDaoService;
import util.HibernateUtil;

/**
 *
 * @author 84975
 */
public class NhaCungCapVaNhapHang_DAO implements NhaCungCapVaNhapHangDaoService{
    private SessionFactory sessionFactory;  
    
    public NhaCungCapVaNhapHang_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    
    @Override
    public List<NhaCungCap> getNhaCungCap() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from NhaCungCap";
        try {
            tr.begin();
                List<NhaCungCap> listNCC = session.createNativeQuery(sql, NhaCungCap.class)
                    .getResultList();
            tr.commit();
            return listNCC;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
// lay danh sach loai dich vu
    @Override
    public List<LoaiDichVu> getLoaiDichVu() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from LoaiDichVu";
        try {
            tr.begin();
                List<LoaiDichVu> listDV = session.createNativeQuery(sql, LoaiDichVu.class)
                    .getResultList();
            tr.commit();
            return listDV;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }
    
    public List<MatHang> getDanhSachMatHang(String id){
        
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

    @Override
    public NhaCungCap getNhaCungCapById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from NhaCungCap where maNCC = '"+id+"'" ;
        
        try {
            tr.begin();
                NhaCungCap ncc = session.createNativeQuery(sql, NhaCungCap.class)
                    .getSingleResult();
            tr.commit();
            return ncc;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean addNhaCungCap(NhaCungCap ncc) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(ncc);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateNhaCungCap(NhaCungCap ncc) {
//        update NhaCungCap set tenNCC = 'abc' where maNCC = 'NCC00test'
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
//        String sql = "update NhaCungCap set tenNCC = '"+ncc.getTenNCC()+"'"
//                +",sdt = '"+ncc.getSoDienThoai()+"'"
//                +",soNha = '"+ncc.getDiaChi().getSoNha()+"'"
//                +",tenDuong = '"+ncc.getDiaChi().getTenDuong()+"'"
//                +",xaPhuong = '"+ncc.getDiaChi().getXaPhuong()+"'"
//                +",quanHuyen = '"+ncc.getDiaChi().getQuanHuyen()+"'"
//                +",tinhThanh = '"+ncc.getDiaChi().getTinhThanh()+"'"
//                + " where maNCC = 'NCC00test'" ;

        try {
            tr.begin();
                session.update(ncc);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public String getlastNhaCungCap() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select top 1 maNCC from NhaCungCap order by maNCC desc";
        try {
            tr.begin();
                Object[] obj = (Object[])session.createNativeQuery(sql).getSingleResult();
            tr.commit();
            return (String)obj[0];
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }
}
