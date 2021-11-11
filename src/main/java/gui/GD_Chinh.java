package gui;

import entity.KhachHang;
import entity.NhanVien;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import gui.component.Content;
import gui.component.Header;
import gui.component.KhachHangDetail;
import gui.component.Menu;
import gui.component.NhanVienDetail;
import gui.component.TabLayout;
import gui.dialog.DL_ThongTinNhanVien;
import gui.component.RoomDetail;
import gui.swing.event.EventMenuSelected;
import gui.swing.event.EventShowInfoOver;
import gui.swing.event.EventShowPopupMenu;
import gui.swing.menu.DropMenu;
import gui.swing.menu.MenuItem;
import gui.swing.menu.PopupMenu;
import gui.swing.scrollbar.ScrollBarCustom;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import gui.swing.event.EventOnClick;

public class GD_Chinh extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static NhanVien NHAN_VIEN;
    private JLayeredPane background;
    private Animator animator; // thực thi animation
    private Animator animator2; // thực thi animation2
    private Menu menu; // thành phân nav kiểu menu chọn nội dung hiện thị
    private Header header; // thành phần header hiện thi thông tin nhân viên
    private Content content; // thành phần content chứa nội dung
    private boolean tabShow;
    private MigLayout layout;
    private final DecimalFormat df = new DecimalFormat("##0.##");
    private TabLayout tab;
    private JScrollPane sp;
    
    public GD_Chinh(String title, NhanVien nhanVien) {
        super(title);
        GD_Chinh.NHAN_VIEN = nhanVien;
        buidGD_Chinh();
    }

    /**
     * Xây dựng GD_Chính
     */
    private void buidGD_Chinh() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createBackground();
        setContentPane(background);
        setMinimumSize(new Dimension(1200, 500));
        pack();
        setLocationRelativeTo(null);
        setState(MAXIMIZED_BOTH);
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
        background.add(createContent()); // content full
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
                }
                if (subMenuIndex == 3) {
                    new GD_DangNhap("Đăng nhập").setVisible(true);
                    dispose();
                }
            }
        };

        header.addEvent2(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                String[] menuItem = {"Hồ sơ", "Xin chào", "Chao xìn", "Đăng xuất"};
                DropMenu dropMenu = new DropMenu(GD_Chinh.this, 0, eventSelected, menuItem);
                int x;
                if (menu.isShowMenu()) {
                    x = GD_Chinh.this.getX() + com.getX() + com.getWidth() * 2 - 40;
                } else {
                    x = GD_Chinh.this.getX() + com.getX() + com.getWidth() - 40;
                }
                int y = GD_Chinh.this.getY() + 55;
                dropMenu.setLocation(x, y);
                dropMenu.setVisible(true);
            }
        });
        return header;
    }

    /**
     * Tạo menu gồm menuItem and subMenu Từng index là để show nột dung
     *
     * @return nav
     */
    private Menu createNav() {
        menu = new Menu();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
//            System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                switch (menuIndex) {
                    case 0:
                    if(subMenuIndex == 0) {
                        GD_SoDoPhongHat soDoPhongHat = new GD_SoDoPhongHat();
                        soDoPhongHat.addEvent(new EventShowInfoOver() {
                            @Override
                            public void showInfoOver(Component com, MouseEvent e) {
                //                MenuItem item = (MenuItem) com;
                                RoomDetail infoOver = new RoomDetail(GD_Chinh.this);
                                int x = 0;
                                int y = 0;
                                if((e.getXOnScreen() + 400) >= 1920) {
                                    x = e.getXOnScreen() - e.getX() - 400;
                                    y = e.getYOnScreen() - e.getY() - 10;
                                } else {
                                    x = e.getXOnScreen() + 200 - e.getX();
                                    y = e.getYOnScreen() - e.getY() - 10;
                                }
                                infoOver.setLocation(x, y);
                                infoOver.setVisible(true);
                                sp.addMouseWheelListener(new MouseWheelListener() {
                                    @Override
                                    public void mouseWheelMoved(MouseWheelEvent arg0) {
                                        infoOver.closeMenu();
                                    }
                                });
                                soDoPhongHat.addEventSp(new MouseWheelListener() {
                                    @Override
                                    public void mouseWheelMoved(MouseWheelEvent arg0) {
                                        infoOver.closeMenu();
                                    }
                                });
                            }

                        });
                        content.showForm(soDoPhongHat);
                        
                    }
                    else if(subMenuIndex == 1) {
                        content.showForm(new GD_DanhSachPhong());
                    }
                    break;
                    case 1:
                       // GD_QLDatPhong qlDatPhong = new GD_QLDatPhong();
                        content.showForm(new GD_QLDatPhong());
                        
//                        qlDatPhong.addEvent(new EventSelectedRow() {
//                            @Override
//                            public void selectedRow(Object object) {
//                                PhieuDatPhong phieuDatPhong = (PhieuDatPhong) object;
//                                
//                                if(!animator2.isRunning()){
//                                    if(!tabShow){
//                                        tab.setVisible(true);
//                                        //Phi
//                                    }
//                                }
//
//                            }
//                        });
                        
                        break;
                    case 2:
                        GD_KhachHang giaoDienKhachHang = new GD_KhachHang();
                        content.showForm(giaoDienKhachHang);
                        
                        giaoDienKhachHang.addEvent(new EventOnClick() {
                            @Override
                            public void onClick(Object object) {
                                KhachHang khachHang = (KhachHang) object;
                                if(!animator2.isRunning()){
                                    if(!tabShow){
                                        tab.setVisible(true);
                                        
                                        KhachHangDetail khachHangDetail =  new KhachHangDetail(khachHang);
                                        tab.showDetail(khachHangDetail);
                                        animator2.start();
                                    }
                                }
                            }
                        });
                        
                        
                        break;
                    case 3:
                        content.showForm(new GD_HoaDon());
                        break;
                    case 4:
                        GD_NhanVien gD_NhanVien = new GD_NhanVien();

                        content.showForm(gD_NhanVien);

                        gD_NhanVien.addEvent(new EventOnClick() {
                            @Override
                            public void onClick(Object object) {
                                
                                
                                NhanVien nhanVien = (NhanVien) object;
                                
                                if (!animator2.isRunning()) {
                                    if (!tabShow) {
                                        tab.setVisible(true);

                                        // Truyền object nhân viên vào NhanVienDetail - vào tab ẩn bên phải của nhân viên
                                        NhanVienDetail nhanVienDetail = new NhanVienDetail(nhanVien);
                                        tab.showDetail(nhanVienDetail);
//chính -> GD_nhanVien -> nhân viên detail-> tab thông tin nhân viên
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
                    default:
                        break;
                }
            }
        });

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
        menu.initMenuItem();
        return menu;
    }

    /**
     * Tạo ngăn hiện lên nội dung
     *
     * @return JPanel content
     */
    private JScrollPane createContent() {
        sp = new JScrollPane();
	content = new Content();
	content.setBackground(new Color(245, 245, 245));
        GD_SoDoPhongHat soDoPhongHat = new GD_SoDoPhongHat();
        soDoPhongHat.addEvent(new EventShowInfoOver() {
            @Override
            public void showInfoOver(Component com, MouseEvent e) {
//                MenuItem item = (MenuItem) com;
                RoomDetail infoOver = new RoomDetail(GD_Chinh.this);
                int x = 0;
                int y = 0;
                if((e.getXOnScreen() + 400) >= 1920) {
                    x = e.getXOnScreen() - e.getX() - 400;
                    y = e.getYOnScreen() - e.getY() - 10;
                } else {
                    x = e.getXOnScreen() + 200 - e.getX();
                    y = e.getYOnScreen() - e.getY() - 10;
                }
                infoOver.setLocation(x, y);
                infoOver.setVisible(true);
                sp.addMouseWheelListener(new MouseWheelListener() {
                    @Override
                    public void mouseWheelMoved(MouseWheelEvent arg0) {
                        infoOver.closeMenu();
                    }
                });
                soDoPhongHat.addEventSp(new MouseWheelListener() {
                    @Override
                    public void mouseWheelMoved(MouseWheelEvent arg0) {
                        infoOver.closeMenu();
                    }
                });
            }

        });
        
	content.showForm(soDoPhongHat);
        sp.getViewport().setBackground(Color.WHITE);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        sp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setViewportView(content);
        sp.getVerticalScrollBar().setUnitIncrement(50);
        sp.setBorder(null);
        return sp;
    }

    /**
     * tạo ngăn tab
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
        header.addAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!animator2.isRunning()) {
                    if (!tabShow) {
                        tab.setVisible(true);
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
}
