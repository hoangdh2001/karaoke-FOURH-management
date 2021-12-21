package gui;

import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuDatPhong;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import gui.component.Content;
import gui.component.HoaDonDetail;
import gui.component.Menu;
import gui.component.NhanVienDetail;
import gui.component.PanelThemNhanVien;
import gui.component.PhieuDatPhongDetail;
import gui.component.Room;
import gui.component.TabLayout;
import gui.dialog.DL_ThongTinNhanVien;
import gui.component.RoomDetail;
import gui.dialog.DL_DoiMatKhau;
import gui.swing.event.EventAdd;
import gui.swing.event.EventAddNhanVien;
import gui.swing.image.WindowIcon;
import gui.swing.event.EventMenuSelected;
import gui.swing.event.EventShowPopupMenu;
import gui.swing.menu.MenuItem;
import gui.swing.menu.PopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import java.awt.Frame;
import gui.swing.event.EventSelectedRow;
import gui.swing.event.EventShowInfoOver;
import javax.swing.JOptionPane;
import gui.component.Header;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GD_Chinh extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static NhanVien NHAN_VIEN;
    public static Frame FRAME;
    private JLayeredPane background;
    private Animator animator; // thực thi animation
    private Animator animator2; // thực thi animation2
    private Menu menu; // thành phân nav kiểu menu chọn nội dung hiện thị
    private Header header; // thành phần header hiện thi thông tin nhân viên
    private Content content; // thành phần content chứa nội dung
    private boolean tabShow;
    private MigLayout layout;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private final DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    private TabLayout tab;
    
    public GD_Chinh(Frame frame, String title, NhanVien nhanVien) {
        super(title);
        GD_Chinh.NHAN_VIEN = nhanVien;
        GD_Chinh.FRAME = frame;
        buidGD_Chinh();
        
    }

    /**
     * Xây dựng GD_Chính
     */
    private void buidGD_Chinh() {
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        WindowIcon.addWindowIcon(this);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
        createBackground();
        setContentPane(background);
        setMinimumSize(new Dimension(1200, 500));
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Tạo nên chứa cái thành phần header, nav dọc kiểu menu, content
     *
     * @return JPanel background
     */
    private void createBackground() {
        background = new JLayeredPane();
        background.setPreferredSize(new Dimension(1400, 800));
        // layout 2 cột 1 dòng
        layout = new MigLayout("fill, insets 0", "0[]0[100%, fill]0", "0[fill, top]0");
        background.setLayout(layout);
        background.setBackground(new Color(236, 240, 245));

        background.add(createNav(), "w 230!, spany 2"); // nav sẽ chiếm hai dòng
        background.add(createHeader(), "h 50!, wrap"); // header xuống dòng
        background.add(createContent(), "w 100%, h 100%"); // content full
        background.add(createTabPane(), "pos 45% 1al n n, w 100%, h 90%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 50 + (180 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                } else {
                    width = 50 + (180 * fraction);
                    menu.setAlpha(fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany 2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target); // thực thi sư kiện thời gian trong 5s
        animator.setResolution(0); // Mượt
        animator.setDeceleration(0.5f); // ngưỡng 50% sẽ giảm tốc
        animator.setAcceleration(0.5f); // ngưỡng 50% sẽ tăng tốc 
        // Bù trù
        // Khi click vào nút menu sẽ mở menu rộng ra
    }

    /**
     * Tạo header bào gồm hiện thị thông tin nhân viên, loại nhân viên Thời gian
     *
     * @return JPanel header
     */
    private Header createHeader() {
        header = new Header();
        header.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideAllMenu();
                }
            }
        });
        EventMenuSelected eventSelected = new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                if (subMenuIndex == 0) {
                    new DL_ThongTinNhanVien(GD_Chinh.this, true).setVisible(true);
                } else if(subMenuIndex == 1) {
                    new DL_DoiMatKhau(GD_Chinh.this).setVisible(true);
                } else if(subMenuIndex == 2){
                    try {
                        Application.openWebpage(new URL("https://nguyenhung-hub.github.io/user-manual-karaokeFourH/"));
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(GD_Chinh.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (subMenuIndex == 3) {
                    if(JOptionPane.showConfirmDialog(rootPane, "Bạn có đồng ý đăng xuất!", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        FRAME.setVisible(true);
                        dispose();
                    }
                    
                }
            }
        };
        header.addEventMenu(eventSelected);

        return header;
    }

    /**
     * Tạo menu gồm menuItem and subMenu Từng index là để show nột dung
     *
     * @return nav
     */
    private Menu createNav() {
        menu = new Menu();
        if(NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV().equals("Quản lý")) {
            phanQuyenQuanLy();
            menu.initMenuItemQuanLy();
        } else if(NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV().equals("Lễ tân")) {
            phanQuyenNhanVienLeTan();
            menu.initMenuItemNhanVienLT();
        } else if(NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV().equals("Kế toán")) {
            phanQuyenNhanVienKeToan();
            menu.initMenuItemNhanVienKT();
        }

        // mở cửa sổ nhỏ khi menu đóng
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(GD_Chinh.this, item.getIndex(), item.getEventSelected(), Color.WHITE, item.getMenu().getSubMenu());
                int x = GD_Chinh.this.getX() + 50;
                int y = GD_Chinh.this.getY() + com.getY() + 90;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
//        menu.initMenuItem();
        return menu;
    }

    /**
     * Tạo ngăn hiện lên nội dung
     *
     * @return JPanel content
     */
    private Content createContent() {
        content = new Content();
        content.setBackground(new Color(245, 245, 245));
        if(NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV().equals("Quản lý") || NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV().equals("Lễ tân")) {
            GD_SoDoPhongHat soDoPhongHat = new GD_SoDoPhongHat();
            soDoPhongHat.addEvent(new EventShowInfoOver() {
                @Override
                public void showInfoOver(Component com, MouseEvent e) {
                    Room item = (Room) com;
                    RoomDetail infoOver = new RoomDetail(GD_Chinh.this, item.getPhong());
                    int x = 0;
                    int y = 0;
                    if ((e.getXOnScreen() + 400) >= 1920) {
                        x = e.getXOnScreen() - e.getX() - 400;
                        y = e.getYOnScreen() - e.getY() - 10;
                    } else {
                        x = e.getXOnScreen() + 240 - e.getX();
                        y = e.getYOnScreen() - e.getY() - 10;
                    }
                    infoOver.setLocation(x, y);
                    infoOver.setVisible(true);
                }

            });

            content.showForm(soDoPhongHat);
        } else if (NHAN_VIEN.getLoaiNhanVien().getTenLoaiNV().equals("Kế toán")) {
            content.showForm(new GD_ThongKeDoanhThu());
        }
        return content;
    }

    /**
     * tạo ngăn ts
     */
    private TabLayout createTabPane() {

        tab = new TabLayout();
        background.setLayer(tab, JLayeredPane.POPUP_LAYER);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (tabShow) {
                    width = 45 * fraction;
                } else {
                    width = 45 * (1f - fraction);
                }
                width = Double.valueOf(df.format(width));
                layout.setComponentConstraints(tab, "pos " + width + "% 1al n n, w 100%, h 100%");
                tab.repaint();
                tab.revalidate();

                background.revalidate();
            }

            @Override
            public void end() {
                tabShow = !tabShow;
                if (!tabShow) {
                    tab.setVisible(false);
                }
            }
        };
        animator2 = new Animator(400, target);
        animator2.setResolution(0);
        animator2.setAcceleration(0.5f);
        animator2.setDeceleration(0.5f);
        tab.addAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!animator2.isRunning()) {
                    if (tabShow) {
                        animator2.start();
                    }
                }
            }
        });
        tab.addEventCloseTab(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (!animator2.isRunning()) {
                        if (tabShow) {
                            animator2.start();
                        }
                    }
                }
            }
        });
        return tab;
    }
    
    private void phanQuyenQuanLy() {
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
//            System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                switch (menuIndex) {
                    case 0:
                        if (subMenuIndex == 0) {
                            GD_SoDoPhongHat soDoPhongHat = new GD_SoDoPhongHat();
                            soDoPhongHat.addEvent(new EventShowInfoOver() {
                                @Override
                                public void showInfoOver(Component com, MouseEvent e) {
                                    Room item = (Room) com;
                                    RoomDetail infoOver = new RoomDetail(GD_Chinh.this, item.getPhong());
                                    int x = 0;
                                    int y = 0;
                                    if ((e.getXOnScreen() + 400) >= 1920) {
                                        x = e.getXOnScreen() - e.getX() - 400;
                                        y = e.getYOnScreen() - e.getY() - 10;
                                    } else {
                                        x = e.getXOnScreen() + 240 - e.getX();
                                        y = e.getYOnScreen() - e.getY() - 10;
                                    }
                                    infoOver.setLocation(x, y);
                                    infoOver.setVisible(true);
                                    soDoPhongHat.addEventSp(new MouseWheelListener() {
                                        @Override
                                        public void mouseWheelMoved(MouseWheelEvent arg0) {
                                            infoOver.closeMenu();
                                        }
                                    });
                                }

                            });
                            content.showForm(soDoPhongHat);

                        } else if (subMenuIndex == 1) {
                            content.showForm(new GD_DanhSachPhong());
                        }
                        break;
                    case 1:
                        GD_QuanLyDatPhong qlDatPhong = new GD_QuanLyDatPhong();
                        content.showForm(qlDatPhong);
                        qlDatPhong.addEvent(new EventSelectedRow() {
                            @Override
                            public void selectedRow(Object object) {
                                PhieuDatPhong phieuDatPhong = (PhieuDatPhong) object;
                                    if(!animator2.isRunning()){
                                        if(!tabShow){
                                            tab.setVisible(true);
                                            PhieuDatPhongDetail phieuDetail = new PhieuDatPhongDetail(phieuDatPhong);
                                            tab.showDetail(phieuDetail);
                                            animator2.start();
                                        }
                                    }
                                }
                        });
                        
                        break;
                    case 2:
                        GD_QuanLyKhachHang giaoDienKhachHang = new GD_QuanLyKhachHang();
                        content.showForm(giaoDienKhachHang);

                        break;
                    case 3:
                        GD_QuanLyHoaDon giaoDienHoaDon = new GD_QuanLyHoaDon();
                        content.showForm(giaoDienHoaDon);
                        
                        giaoDienHoaDon.addEvent(new EventSelectedRow() {
                            @Override
                            public void selectedRow(Object object) {
                                HoaDon hoaDon = (HoaDon) object;
                                if(!animator2.isRunning()){
                                    if(!tabShow){
                                        tab.setVisible(true);
                                        
                                        HoaDonDetail hoaDonDetail =  new HoaDonDetail(hoaDon);
                                        tab.showDetail(hoaDonDetail);
                                        animator2.start();
                                    }
                                }
                            }
                        });
                        break;
                    case 4:
                        GD_QuanLyNhanVien gD_NhanVien = new GD_QuanLyNhanVien();

                        content.showForm(gD_NhanVien);
                        gD_NhanVien.addEventSelectedRow(new EventSelectedRow() {
                            @Override
                            public void selectedRow(Object object) {

                                NhanVien nhanVien = (NhanVien) object;

                                if (!animator2.isRunning()) {
                                    if (!tabShow) {
                                        tab.setVisible(true);

                                        // Truyền object nhân viên vào NhanVienDetail - vào tab ẩn bên phải của nhân viên
                                        NhanVienDetail nhanVienDetail = new NhanVienDetail(nhanVien);
                                        tab.showDetail(nhanVienDetail);
                                        animator2.start();
                                    }
                                }

                            }
                        });
                        gD_NhanVien.addEventAddNhanVien(new EventAddNhanVien() {
                            @Override
                            public void AddNhanVien() {
                                if (!animator2.isRunning()) {
                                    if (!tabShow) {
                                        tab.setVisible(true);
                                        
                                        PanelThemNhanVien pnlThemNhanVien = new PanelThemNhanVien();
                                        pnlThemNhanVien.addThemEvent(new EventAdd() {
                                            @Override
                                            public void add(Object obj) {
                                                if(obj instanceof Boolean) {
                                                    boolean rs = (Boolean) obj;
                                                    if(rs) {
                                                        JOptionPane.showMessageDialog(GD_Chinh.this, "Thêm thành công!");
                                                        gD_NhanVien.pnlPageHandle();
                                                        
                                                    } else {
                                                        JOptionPane.showMessageDialog(GD_Chinh.this, "Thêm thất bại!");
                                                    }
                                                }
                                            }
                                        });
                                        tab.showDetail(pnlThemNhanVien);
                                        animator2.start();
                                    }
                                }
                            }
                        });
                        break;
                    case 5:
                        if (subMenuIndex == 0) {
                            content.showForm(new GD_ThongKeDoanhThu());
                        } else if (subMenuIndex == 1) {
                            content.showForm(new GD_ThongKeHangHoa());
                        }
                        break;
                    case 6:
                        if(subMenuIndex == 0) {
                            content.showForm(new GD_XemMatHang());
                        } else if (subMenuIndex == 1) {
                            content.showForm(new GD_NhapHangHoa());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
    
    private void phanQuyenNhanVienLeTan() {
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                switch (menuIndex) {
                    case 0:
                            GD_SoDoPhongHat soDoPhongHat = new GD_SoDoPhongHat();
                            soDoPhongHat.addEvent(new EventShowInfoOver() {
                                @Override
                                public void showInfoOver(Component com, MouseEvent e) {
                                    Room item = (Room) com;
                                    RoomDetail infoOver = new RoomDetail(GD_Chinh.this, item.getPhong());
                                    int x = 0;
                                    int y = 0;
                                    if ((e.getXOnScreen() + 400) >= 1920) {
                                        x = e.getXOnScreen() - e.getX() - 400;
                                        y = e.getYOnScreen() - e.getY() - 10;
                                    } else {
                                        x = e.getXOnScreen() + 200 - e.getX();
                                        y = e.getYOnScreen() - e.getY() - 10;
                                    }
                                    infoOver.setLocation(x, y);
                                    infoOver.setVisible(true);
                                    soDoPhongHat.addEventSp(new MouseWheelListener() {
                                        @Override
                                        public void mouseWheelMoved(MouseWheelEvent arg0) {
                                            infoOver.closeMenu();
                                        }
                                    });
                                }

                            });
                            content.showForm(soDoPhongHat);
                        break;
                    case 1:
                        GD_QuanLyDatPhong qlDatPhong = new GD_QuanLyDatPhong();
                        content.showForm(qlDatPhong);
                        qlDatPhong.addEvent(new EventSelectedRow() {
                            @Override
                            public void selectedRow(Object object) {
                                PhieuDatPhong phieuDatPhong = (PhieuDatPhong) object;
                                    if(!animator2.isRunning()){
                                        if(!tabShow){
                                            tab.setVisible(true);
                                            PhieuDatPhongDetail phieuDetail = new PhieuDatPhongDetail(phieuDatPhong);
                                            tab.showDetail(phieuDetail);
                                            animator2.start();
                                        }
                                    }
                                }
                        });
                        break;
                    case 2:
                        GD_QuanLyKhachHang giaoDienKhachHang = new GD_QuanLyKhachHang();
                        content.showForm(giaoDienKhachHang);
                        break;
                    case 3:
                        GD_QuanLyHoaDon giaoDienHoaDon = new GD_QuanLyHoaDon();
                        content.showForm(giaoDienHoaDon);
                        giaoDienHoaDon.addEvent(new EventSelectedRow() {
                            @Override
                            public void selectedRow(Object object) {
                                HoaDon hoaDon = (HoaDon) object;
                                if(!animator2.isRunning()){
                                    if(!tabShow){
                                        tab.setVisible(true);
                                        
                                        HoaDonDetail hoaDonDetail =  new HoaDonDetail(hoaDon);
                                        tab.showDetail(hoaDonDetail);
                                        animator2.start();
                                    }
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }
    
    private void phanQuyenNhanVienKeToan() {
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                switch (menuIndex) {
                    case 0:
                        if (subMenuIndex == 0) {
                            content.showForm(new GD_ThongKeDoanhThu());
                        } else if (subMenuIndex == 1) {
                            content.showForm(new GD_ThongKeHangHoa());
                        }
                        break;
                    case 1:
                        if(subMenuIndex == 0) {
                            content.showForm(new GD_XemMatHang());
                        } else if (subMenuIndex == 1) {
                            content.showForm(new GD_NhapHangHoa());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
