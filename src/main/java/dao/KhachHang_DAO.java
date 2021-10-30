package dao;

import java.util.List;

import entity.KhachHang;
import entity.Phong;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.KhachHangService;

public class KhachHang_DAO implements KhachHangService {
        private SessionFactory sessionFactory;

	public boolean themKhachHang(KhachHang khachHang) {
		return false;
	}

	public boolean capNhatKhachHang(KhachHang khachHang) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean xoaKhachHang(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public KhachHang getKhachHang(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<KhachHang> getDSKhachHang() {
            Session session = sessionFactory.openSession();
            Transaction tr = session.getTransaction();

        try {
            tr.begin();
            List<KhachHang> dsKhachHang = session
                    .createNamedQuery("getDSKhachHang", KhachHang.class)
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
	
}
