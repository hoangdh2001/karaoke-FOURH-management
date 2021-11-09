package gui.component;

import entity.HoaDon;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.event.EventMenuSelected;
import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Admin
 */
public class Room extends PanelShadow {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
    private final String fontName = "sansserif";
    private final int fontStyle = Font.PLAIN;
    private HoaDon hoaDon;
    private Phong phong;
    private double thoiGian;
    private JLabel lblGioHat;
    private Timer tm;
    private JPanel pnlDangHat;
    private JPanel pnlPhongTrong;
    private JPanel pnlPhongSua;
    private JPanel pnlPhongBan;
    private JPanel pnlPhongDangDon;
    private JPanel pnlPhongDatTruoc;
    private JMenuItem mniKhachVaoHat;
    private JMenuItem mniThanhToan;
    private JMenuItem mniDoiPhong;
    private JMenuItem mniThemDichVu;
    private JMenuItem mniDatPhong;
    private JMenuItem mniDonPhong;
    private JMenuItem mniSuaPhong;
    private int index;
    
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public Room(Phong phong) {
        this.phong = phong;
        buildRoom();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public Room() {
        buildRoom();
    }
    
    private void buildRoom() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JPopupMenu pop = new JPopupMenu();
        pop.setPreferredSize(new Dimension(150, 250));
        mniKhachVaoHat = new JMenuItem("Khách vào hát");
        mniThanhToan = new JMenuItem("Thanh toán");
        mniDoiPhong = new JMenuItem("Đổi phòng");
        mniThemDichVu = new JMenuItem("Thêm dịch vụ");
        mniDatPhong = new JMenuItem("Đặt phòng");
        mniDonPhong = new JMenuItem("Dọn phòng");
        mniSuaPhong = new JMenuItem("Sửa phòng");
        pop.add(mniKhachVaoHat);
        pop.addSeparator();
        pop.add(mniThanhToan);
        pop.add(mniDoiPhong);
        pop.add(mniThemDichVu);
        pop.add(mniDatPhong);
        pop.addSeparator();
        pop.add(mniDonPhong);
        pop.add(mniSuaPhong);
        setComponentPopupMenu(pop);
        checkTrangThai();
        
    }

    private void checkTrangThai() {
        if(null != phong.getTrangThai()) switch (phong.getTrangThai()) {
            case TRONG:
                showPanel(buildPhongTrong());
                break;
            case DANG_HAT:
                showPanel(buildPhongDangHaT());
                break;
            case DAT_TRUOC:
                showPanel(buildPhongDatTruoc());
                break;
            case BAN:
                showPanel(buildPhongBan());
                break;
            case DANG_DON:
                showPanel(buildPhongDangDon());
                break;
            case DANG_SUA:
                showPanel(buildPhongSua());
                break;
            default:
                break;
        }
    }

    private void showPanel(JPanel pane) {
        removeAll();
        add(pane);
        repaint();
        revalidate();
    }

    private JPanel buildPhongDangHaT() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(true);
        mniDoiPhong.setEnabled(true);
        mniThemDichVu.setEnabled(true);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        pnlDangHat = new JPanel();
        pnlDangHat.setBackground(TrangThaiPhong.DANG_HAT.getColor());
        pnlDangHat.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]5[]5[]5[]push"));

        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlDangHat.add(lblIcon);

        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setText(phong.getTenPhong());
        pnlDangHat.add(lblTenPhong);

        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlDangHat.add(lblLoaiPhong);

        JLabel lblTrangThai = new JLabel();
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setText(phong.getTrangThai().getTrangThai());
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        pnlDangHat.add(lblTrangThai);

        JLabel lblKhachHang = new JLabel("Đỗ Huy Hoàng");
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font(fontName, fontStyle, 14));
//        lblKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        pnlDangHat.add(lblKhachHang);

        JLabel lblBatDau = new JLabel("22/10/2021 9:00");
        lblBatDau.setForeground(Color.WHITE);
        lblBatDau.setFont(new Font(fontName, fontStyle, 14));
//        lblBatDau.setText(sdf.format(hoaDon.getThoiGianBatDau()));
        pnlDangHat.add(lblBatDau);
        lblGioHat = new JLabel("15 phút");
        lblGioHat.setForeground(Color.WHITE);
        lblGioHat.setFont(new Font(fontName, fontStyle, 14));
        pnlDangHat.add(lblGioHat);
        tm = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                thoiGian = (hoaDon.getThoiGianBatDau().getTime() - Date.valueOf(LocalDate.now()).getTime()) / 1000;
                lblGioHat.setText(String.valueOf((int) thoiGian));
            }
        });

        Button btnThanhToan = new Button("Thanh toán", true);
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setBackground(new Color(0, 31, 63));
        btnThanhToan.setBorderRadius(5);
        pnlDangHat.add(btnThanhToan, "split 2");

        Button btnThemDichVu = new Button("DV", true);
        btnThemDichVu.setForeground(Color.WHITE);
        btnThemDichVu.setBackground(new Color(0, 31, 63));
        btnThemDichVu.setBorderRadius(5);
        pnlDangHat.add(btnThemDichVu);
        return pnlDangHat;
    }

    public void tinhGio() {
        if (!tm.isRunning()) {
            tm.start();
        }
    }

    public void ketThuc() {
        if (tm.isRunning()) {
            tm.stop();
        }
    }

    private JPanel buildPhongTrong() {
        mniKhachVaoHat.setEnabled(true);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(true);
        mniDonPhong.setEnabled(true);
        mniSuaPhong.setEnabled(true);
        pnlPhongTrong = new JPanel();
        pnlPhongTrong.setBackground(TrangThaiPhong.TRONG.getColor());
        pnlPhongTrong.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongTrong.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongTrong.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongTrong.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel();
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        lblTrangThai.setText(phong.getTrangThai().getTrangThai());
        pnlPhongTrong.add(lblTrangThai);
        
        Button btnDat = new Button("Đặt", true);
        btnDat.setForeground(Color.WHITE);
        btnDat.setBackground(new Color(0, 31, 63));
        btnDat.setBorderRadius(5);
        pnlPhongTrong.add(btnDat, "split 2");

        Button btnThue = new Button("Thuê", true);
        btnThue.setForeground(Color.WHITE);
        btnThue.setBackground(new Color(0, 31, 63));
        btnThue.setBorderRadius(5);
        pnlPhongTrong.add(btnThue);
        return pnlPhongTrong;
    }

    /**
     * Xây dựng phòng sửa
     */
    private JPanel buildPhongSua() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        pnlPhongSua = new JPanel();
        pnlPhongSua.setBackground(TrangThaiPhong.DANG_SUA.getColor());
        pnlPhongSua.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongSua.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongSua.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongSua.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel();
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        lblTrangThai.setText(phong.getTrangThai().getTrangThai());
        pnlPhongSua.add(lblTrangThai);
        
        Button btnSuaXong = new Button("Sửa xong", true);
        btnSuaXong.setForeground(Color.WHITE);
        btnSuaXong.setBackground(new Color(0, 31, 63));
        btnSuaXong.setBorderRadius(5);
        pnlPhongSua.add(btnSuaXong);

        return pnlPhongSua;
    }

    /**
     * Xây dựng phòng bẩn
     */
    private JPanel buildPhongBan() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(true);
        mniSuaPhong.setEnabled(false);
        
        pnlPhongBan = new JPanel();
        pnlPhongBan.setBackground(TrangThaiPhong.BAN.getColor());
        pnlPhongBan.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongBan.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongBan.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongBan.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel();
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        lblTrangThai.setText(phong.getTrangThai().getTrangThai());
        pnlPhongBan.add(lblTrangThai);
        
        Button btnDonPhong = new Button("Dọn phòng", true);
        btnDonPhong.setForeground(Color.WHITE);
        btnDonPhong.setBackground(new Color(0, 31, 63));
        btnDonPhong.setBorderRadius(5);
        pnlPhongBan.add(btnDonPhong);
        
        return pnlPhongBan;
    }

    /**
     * Xây dựng phòng đang dọn
     */
    private JPanel buildPhongDangDon() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        
        pnlPhongDangDon = new JPanel();
        pnlPhongDangDon.setBackground(TrangThaiPhong.DANG_DON.getColor());
        pnlPhongDangDon.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongDangDon.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongDangDon.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongDangDon.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel();
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        lblTrangThai.setText(phong.getTrangThai().getTrangThai());
        pnlPhongDangDon.add(lblTrangThai);
        
        Button btnDonXong = new Button("Dọn xong", true);
        btnDonXong.setForeground(Color.WHITE);
        btnDonXong.setBackground(new Color(0, 31, 63));
        btnDonXong.setBorderRadius(5);
        pnlPhongDangDon.add(btnDonXong);
        
        return pnlPhongDangDon;
    }

    /**
     * Xây dựng phòng đặt trước
     */
    private JPanel buildPhongDatTruoc() {
        mniKhachVaoHat.setEnabled(true);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(true);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        
        pnlPhongDatTruoc = new JPanel();
        pnlPhongDatTruoc.setBackground(TrangThaiPhong.DAT_TRUOC.getColor());
        pnlPhongDatTruoc.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]5[]5[]50[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongDatTruoc.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongDatTruoc.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongDatTruoc.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel("Đã đặt trước");
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        lblTrangThai.setText(phong.getTrangThai().getTrangThai());
        pnlPhongDatTruoc.add(lblTrangThai);
        
        JLabel lblKhachHang = new JLabel("Đỗ Huy Hoàng");
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font(fontName, fontStyle, 14));
//        lblKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        pnlPhongDatTruoc.add(lblKhachHang);

        JLabel lblBatDau = new JLabel("22/10/2021 9:00");
        lblBatDau.setForeground(Color.WHITE);
        lblBatDau.setFont(new Font(fontName, fontStyle, 14));
//        lblBatDau.setText(sdf.format(hoaDon.getThoiGianBatDau()));
        pnlPhongDatTruoc.add(lblBatDau);
        
        Button btnThue = new Button("Thuê", true);
        btnThue.setForeground(Color.WHITE);
        btnThue.setBackground(new Color(0, 31, 63));
        btnThue.setBorderRadius(5);
        pnlPhongDatTruoc.add(btnThue, "split 2");
        
        Button btnHuy = new Button("Hủy", true);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(0, 31, 63));
        btnHuy.setBorderRadius(5);
        pnlPhongDatTruoc.add(btnHuy);
        
        return pnlPhongDatTruoc;
    }
}
