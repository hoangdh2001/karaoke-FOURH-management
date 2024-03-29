package gui.component;

import entity.HoaDon;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import gui.swing.event.EventRoom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Room extends PanelShadow {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final String fontName = "sansserif";
    private final int fontStyle = Font.PLAIN;
    private HoaDon hoaDon;
    private Phong phong;
    private PhieuDatPhong phieuDatPhong;
    private long thoiGian;
    private JLabel lblGioHat;
    private JMenuItem mniKhachVaoHat;
    private JMenuItem mniThanhToan;
    private JMenuItem mniDoiPhong;
    private JMenuItem mniThemDichVu;
    private JMenuItem mniDatPhong;
    private JMenuItem mniDonPhong;
    private JMenuItem mniSuaPhong;
    private EventRoom event;
    private Thread thread;
    
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
    
    public Room(Phong phong, HoaDon hoaDon) {
        this.phong = phong;
        this.hoaDon = hoaDon;
        buildRoom();
    }
    
    public Room(Phong phong, PhieuDatPhong phieuDatPhong) {
        this.phong = phong;
        this.phieuDatPhong = phieuDatPhong;
        buildRoom();
    }

    public Room() {
        buildRoom();
    }
    
    public void addEvent(EventRoom event) {
        this.event = event;
    }
    
    private void buildRoom() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JPopupMenu pop = new JPopupMenu();
        pop.setPreferredSize(new Dimension(150, 250));
        mniKhachVaoHat = new JMenuItem("Khách vào hát");
        mniKhachVaoHat.setIconTextGap(15);
        mniKhachVaoHat.setIcon(new ImageIcon(getClass().getResource("/icon/key.png")));
        
        mniThanhToan = new JMenuItem("Thanh toán");
        mniThanhToan.setIcon(new ImageIcon(getClass().getResource("/icon/payment.png")));
        mniThanhToan.setIconTextGap(15);
        mniThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.addBtnThanhToanEvent(hoaDon);
                checkTrangThai();
            }
        });
        mniDoiPhong = new JMenuItem("Đổi phòng");
        mniDoiPhong.setIcon(new ImageIcon(getClass().getResource("/icon/change.png")));
        mniDoiPhong.setIconTextGap(15);
        mniThemDichVu = new JMenuItem("Cập nhật dịch vụ");
        mniThemDichVu.setIcon(new ImageIcon(getClass().getResource("/icon/updateservice.png")));
        mniThemDichVu.setIconTextGap(15);
        mniThemDichVu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.addBtnThemDichVuEvent(hoaDon);
            }
        });
        mniDatPhong = new JMenuItem("Đặt phòng");
        mniDatPhong.setIcon(new ImageIcon(getClass().getResource("/icon/book_room.png")));
        mniDatPhong.setIconTextGap(15);
        mniDonPhong = new JMenuItem("Dọn phòng");
        mniDonPhong.setIcon(new ImageIcon(getClass().getResource("/icon/cleaning_small.png")));
        mniDonPhong.setIconTextGap(15);
        mniSuaPhong = new JMenuItem("Sửa phòng");
        mniSuaPhong.setIcon(new ImageIcon(getClass().getResource("/icon/fix_small.png")));
        mniSuaPhong.setIconTextGap(15);
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
    
    private void removeAllListener() {
        for (ActionListener actionListener : mniDonPhong.getActionListeners()) {
            mniDonPhong.removeActionListener(actionListener);
        }
        for (ActionListener actionListener : mniSuaPhong.getActionListeners()) {
            mniSuaPhong.removeActionListener(actionListener);
        }
        for (ActionListener actionListener : mniDoiPhong.getActionListeners()) {
            mniDoiPhong.removeActionListener(actionListener);
        }
        for (ActionListener actionListener : mniDatPhong.getActionListeners()) {
            mniDatPhong.removeActionListener(actionListener);
        }
        for (ActionListener actionListener : mniKhachVaoHat.getActionListeners()) {
            mniKhachVaoHat.removeActionListener(actionListener);
        }
    }

    private JPanel buildPhongDangHaT() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(true);
        mniDoiPhong.setEnabled(true);
        removeAllListener();
        mniDoiPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDoiPhongEvent(phong, hoaDon);
            }
        });
        mniThemDichVu.setEnabled(true);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        
        
        JPanel pnlDangHat = new JPanel();
        pnlDangHat.setBackground(TrangThaiPhong.DANG_HAT.getColor());
        pnlDangHat.setLayout(new MigLayout("wrap", "84[]", "5[]5"));

        JLabel lblSing = new JLabel();
        lblSing.setIcon(new ImageIcon(getClass().getResource("/icon/sing.png")));
        pnlDangHat.add(lblSing, "pos 0al 0.5al");

        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setFont(new Font(fontName, Font.BOLD, 18));
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setText(phong.getTenPhong());
        pnlDangHat.add(lblTenPhong);

        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlDangHat.add(lblLoaiPhong);

        JLabel lblKhachHang = new JLabel("Khách hàng");
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font(fontName, fontStyle, 14));
        
        if(hoaDon.getKhachHang() != null) {
            lblKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        }
        pnlDangHat.add(lblKhachHang);

        JLabel lblBatDau = new JLabel();
        lblBatDau.setForeground(Color.WHITE);
        lblBatDau.setFont(new Font(fontName, fontStyle, 14));
        lblBatDau.setText(sdf.format(hoaDon.getThoiGianBatDau()));
        pnlDangHat.add(lblBatDau);
        
        lblGioHat = new JLabel();
        lblGioHat.setForeground(Color.WHITE);
        lblGioHat.setFont(new Font(fontName, fontStyle, 14));
        
        pnlDangHat.add(lblGioHat);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    thoiGian = (new Date().getTime() - hoaDon.getThoiGianBatDau().getTime()) / 1000;
                    int diffHours = (int) (thoiGian / 3600);
                    thoiGian = thoiGian % 3600;
                    int mins = (int) (thoiGian / 60);
                    lblGioHat.setText(String.format("%02d:%02d", diffHours, mins));
                    sleep();
                    repaint();
                    sleep();
                }
            }
        });
        thread.start();
        
        
        
        Button btnThanhToan = new Button("Thanh toán", true);
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setBackground(new Color(0, 31, 63));
        btnThanhToan.setBorderRadius(5);
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.addBtnThanhToanEvent(hoaDon);
                checkTrangThai();
            }
        });
        pnlDangHat.add(btnThanhToan, "pos 0.95al 0.95al");
        
        return pnlDangHat;
    }
    
    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
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
        mniDonPhong.setText("Dọn phòng");
        mniSuaPhong.setText("Sửa phòng");
        
        removeAllListener();
        
        mniDonPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDonPhongEvent(phong);
                checkTrangThai();
            }
        });
        
        mniSuaPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnSuaPhongEvent(phong);
                checkTrangThai();
            }
        });
        
        mniDatPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDatPhongEvent(phong);
                checkTrangThai();
            }
        });
        
        mniKhachVaoHat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHoaDon(event.addBtnThueEvent(phong));
                checkTrangThai();
            }
        });
        
        JPanel pnlPhongTrong = new JPanel();
        pnlPhongTrong.setBackground(TrangThaiPhong.TRONG.getColor());
        pnlPhongTrong.setLayout(new MigLayout("wrap", "84[]", "5[]5"));
        
        JLabel lblSing = new JLabel();
        lblSing.setIcon(new ImageIcon(getClass().getResource("/icon/micro.png")));
        pnlPhongTrong.add(lblSing, "pos 0al 0.5al");
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, Font.BOLD, 18));
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
        
        Button btnThue = new Button("Thuê", true);
        btnThue.setForeground(Color.WHITE);
        btnThue.setBackground(new Color(0, 31, 63));
        btnThue.setBorderRadius(5);
        btnThue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setHoaDon(event.addBtnThueEvent(phong));
                checkTrangThai();
            }
        });
        pnlPhongTrong.add(btnThue, "pos 0.95al 0.95al");
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
        mniSuaPhong.setEnabled(true);
        mniSuaPhong.setText("Sửa xong");
        
        removeAllListener();
        
        mniSuaPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnSuaXongEvent(phong);
                checkTrangThai();
            }
        });
        
        JPanel pnlPhongSua = new JPanel();
        pnlPhongSua.setBackground(TrangThaiPhong.DANG_SUA.getColor());
        pnlPhongSua.setLayout(new MigLayout("wrap", "84[]", "5[]5"));
        
        JLabel lblFix = new JLabel();
        lblFix.setIcon(new ImageIcon(getClass().getResource("/icon/fix_room.png")));
        pnlPhongSua.add(lblFix, "pos 0al 0.5al");
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, Font.BOLD, 18));
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
        btnSuaXong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnSuaXongEvent(phong);
                checkTrangThai();
            }
        });
        pnlPhongSua.add(btnSuaXong, "pos 0.95al 0.95al");

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
        
        removeAllListener();
        
         mniDonPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDonPhongEvent(phong);
                checkTrangThai();
            }
        });
        
        JPanel pnlPhongBan = new JPanel();
        pnlPhongBan.setBackground(TrangThaiPhong.BAN.getColor());
        pnlPhongBan.setLayout(new MigLayout("wrap", "84[]", "5[]5"));
        
        JLabel lblCleaning = new JLabel();
        lblCleaning.setIcon(new ImageIcon(getClass().getResource("/icon/dirty.png")));
        pnlPhongBan.add(lblCleaning, "pos 0al 0.5al");

        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, Font.BOLD, 18));
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
        btnDonPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDonPhongEvent(phong);
                checkTrangThai();
            }
        });
        pnlPhongBan.add(btnDonPhong, "pos 0.95al 0.95al");
        
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
        mniDonPhong.setEnabled(true);
        mniDonPhong.setText("Dọn xong");
        mniSuaPhong.setEnabled(false);
        
        removeAllListener();
        mniDonPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDonXongEvent(phong);
                checkTrangThai();
            }
        });
        
        JPanel pnlPhongDangDon = new JPanel();
        pnlPhongDangDon.setBackground(TrangThaiPhong.DANG_DON.getColor());
        pnlPhongDangDon.setLayout(new MigLayout("wrap", "84[]", "5[]5"));
        
        JLabel lblCleaning = new JLabel();
        lblCleaning.setIcon(new ImageIcon(getClass().getResource("/icon/cleaning.png")));
        pnlPhongDangDon.add(lblCleaning, "pos 0al 0.5al");
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, Font.BOLD, 18));
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
        btnDonXong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDonXongEvent(phong);
                checkTrangThai();
            }
        });
        pnlPhongDangDon.add(btnDonXong, "pos 0.95al 0.95al n n");
        
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
        mniDatPhong.setEnabled(true);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        removeAllListener();
        mniDatPhong.setText("Hủy");
        mniKhachVaoHat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnThueEvent(phong, phieuDatPhong);
                checkTrangThai();
            }
        });
        mniDatPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnHuyEvent(phong, phieuDatPhong);
                checkTrangThai();
            }
        });
        mniDoiPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                event.addBtnDoiPhongEvent(phieuDatPhong);
            }
        });
        
        JPanel pnlPhongDatTruoc = new JPanel();
        pnlPhongDatTruoc.setBackground(TrangThaiPhong.DAT_TRUOC.getColor());
        pnlPhongDatTruoc.setLayout(new MigLayout("wrap", "84[]", "5[]5"));
        
        JLabel lblDeposit = new JLabel();
        lblDeposit.setIcon(new ImageIcon(getClass().getResource("/icon/deposit.png")));
        pnlPhongDatTruoc.add(lblDeposit, "pos 0al 0.5al");
        
        JLabel lblTenPhong = new JLabel();
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, Font.BOLD, 18));
        lblTenPhong.setText(phong.getTenPhong());
        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongDatTruoc.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel();
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongDatTruoc.add(lblLoaiPhong);
        
        JLabel lblKhachHang = new JLabel("Đỗ Huy Hoàng");
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font(fontName, fontStyle, 14));
        lblKhachHang.setText(phieuDatPhong.getKhachHang().getTenKhachHang());
        pnlPhongDatTruoc.add(lblKhachHang);

        JLabel lblBatDau = new JLabel("22/10/2021 9:00");
        lblBatDau.setForeground(Color.WHITE);
        lblBatDau.setFont(new Font(fontName, fontStyle, 14));
        lblBatDau.setText(sdf.format(phieuDatPhong.getNgayDat()));
        pnlPhongDatTruoc.add(lblBatDau);
        
        
        
        Button btnThue = new Button("Thuê", true);
        btnThue.setForeground(Color.WHITE);
        btnThue.setBackground(new Color(0, 31, 63));
        btnThue.setBorderRadius(5);
        btnThue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHoaDon(event.addBtnThueEvent(phong, phieuDatPhong));
                checkTrangThai();
            }
        });
        pnlPhongDatTruoc.add(btnThue, "pos 0.75al 0.95al");
        
        Button btnHuy = new Button("Hủy", true);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(0, 31, 63));
        btnHuy.setBorderRadius(5);
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.addBtnHuyEvent(phong, phieuDatPhong);
                checkTrangThai();
            }
        });
        pnlPhongDatTruoc.add(btnHuy, "pos 0.95al 0.95al");
        
        return pnlPhongDatTruoc;
    }
}
