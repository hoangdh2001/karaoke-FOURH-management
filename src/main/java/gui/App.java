package gui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

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

public class App {
	public static void main(String[] args) {
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
				.getMetadataBuilder()
				.build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}
	}
}
