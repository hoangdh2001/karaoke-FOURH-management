package gui;

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
import gui.component.Header;
import gui.component.Menu;
import gui.dialog.DL_ThongTinNhanVien;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.swing.menu.DropMenu;
import gui.swing.menu.MenuItem;
import gui.swing.menu.PopupMenu;
import gui.swing.scrollbar.ScrollBarCustom;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class GD_Chinh extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Animator animator; // thực thi animation
    private Menu menu; // thành phân nav kiểu menu chọn nội dung hiện thị
    private Header header; // thành phần header hiện thi thông tin nhân viên
    private Content content; // thành phần content chứa nội dung
    public GD_Chinh(String title) {
	super(title);
	buidGD_Chinh();
    }
    /**
     * Xây dựng GD_Chính
     */
    private void buidGD_Chinh() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setContentPane(createBackground());
        setMinimumSize(new Dimension(1200, 500));
        setState(MAXIMIZED_BOTH);
	pack();
	setLocationRelativeTo(null);
		
    }
    /**
     * Tạo nên chứa cái thành phần header, nav dọc kiểu menu, content
     * @return JPanel background
     */
    private JPanel createBackground() {
	JPanel background = new JPanel();
	background.setPreferredSize(new Dimension(1400, 800));
	MigLayout layout;
        // layout 2 cột 1 dòng
	background.setLayout(layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0")); 
	background.setBackground(new Color(236, 240, 245));
		
	background.add(createNav(), "w 230!, spany 2"); // nav sẽ chiếm hai dòng
	background.add(createHeader(), "h 50!, wrap"); // header xuống dòng
	background.add(createContent(), "w 100%, h 100%"); // content full
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
        animator.setDeceleration(0.5f); // giảm tốc 50%
        animator.setAcceleration(0.5f); // tăng tốc 50% suy ra bình thường
        // Khi click vào nút menu sẽ mở menu rộng ra
        return background;
    }
    /**
     * Tạo header bào gồm hiện thị thông tin nhân viên, loại nhân viên
     * Thời gian
     * @return JPanel header
     */
    private Header createHeader() {
	header = new Header();
        header.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if(menu.isShowMenu()) {
                    menu.hideAllMenu();
                }
            }
        });
        EventMenuSelected eventSelected = new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                if(subMenuIndex == 0) {
                    new DL_ThongTinNhanVien(GD_Chinh.this, true).setVisible(true);
                }
                if(subMenuIndex == 3) {
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
                if(menu.isShowMenu()) {
                    x = GD_Chinh.this.getX() + com.getX() + 160;
                } else {
                    x = GD_Chinh.this.getX() + com.getX() - 20;
                }
                int y = GD_Chinh.this.getY()  + 55;
                dropMenu.setLocation(x, y);
                dropMenu.setVisible(true);
            }
        });
	return header;
    }
    /**
     * Tạo menu gồm menuItem and subMenu
     * Từng index là để show nột dung
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
                    if(subMenuIndex == 0)
                        content.showForm(new GD_SoDoPhongHat());
                    else if(subMenuIndex == 1)
                        content.showForm(new GD_DanhSachPhong());
                    break;
                case 1:
                    content.showForm(new GD_QLDatPhong());
                    break;
                case 2:
                    content.showForm(new GD_KhachHang());
                    break;
                case 3:
                    content.showForm(new GD_HoaDon());
                    break;
                case 4:
                    content.showForm(new GD_NhanVien());
                    break;
                case 5:
                    if(subMenuIndex == 0)
                        content.showForm(new GD_ThongKeDoanhThu());
                    else if(subMenuIndex == 1)
                        content.showForm(new GD_ThongKeHangHoa());
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
     * @return JPanel content
     */
    private JScrollPane createContent() {
        JScrollPane sp = new JScrollPane();
	content = new Content();
	content.setBackground(new Color(245, 245, 245));
	content.showForm(new GD_SoDoPhongHat());
        sp.getViewport().setBackground(Color.WHITE);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        sp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setViewportView(content);
        sp.getVerticalScrollBar().setUnitIncrement(30);
        sp.setBorder(null);
	return sp;
    }
}
 