/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.CaLam;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDon_PK;
import entity.ChiTietNhapHang;
import entity.ChiTietNhapHang_PK;
import entity.DiaChi;
import entity.DiaChiMau;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoHang;
import entity.LoaiDichVu;
import entity.LoaiNhanVien;
import entity.LoaiPhong;
import entity.MatHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhieuDat;
import entity.TrangThaiPhong;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Admin
 */
public class HibernateUtil {

    private SessionFactory sessionFactory;
    private static HibernateUtil instance = null;

    private HibernateUtil() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(CaLam.class)
                .addAnnotatedClass(ChiTietHoaDon_PK.class)
                .addAnnotatedClass(ChiTietHoaDon.class)
                .addAnnotatedClass(HoaDon.class)
                .addAnnotatedClass(KhachHang.class)
                .addAnnotatedClass(LoaiDichVu.class)
                .addAnnotatedClass(LoaiNhanVien.class)
                .addAnnotatedClass(LoaiPhong.class)
                .addAnnotatedClass(MatHang.class)
                .addAnnotatedClass(NhanVien.class)
                .addAnnotatedClass(PhieuDatPhong.class)
                .addAnnotatedClass(Phong.class)
                .addAnnotatedClass(NhaCungCap.class)
                .addAnnotatedClass(ChiTietNhapHang.class)
                .addAnnotatedClass(ChiTietNhapHang_PK.class)
                .addAnnotatedClass(LoHang.class)
                .addAnnotatedClass(DiaChi.class)
                .addAnnotatedClass(DiaChiMau.class)
                .addAnnotatedClass(TrangThaiPhieuDat.class)
                .addAnnotatedClass(TrangThaiPhong.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public synchronized static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}
