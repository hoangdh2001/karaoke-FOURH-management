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
import gui.component.Menu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.swing.menu.MenuItem;
import gui.swing.menu.PopupMenu;
import net.miginfocom.swing.MigLayout;

public class GD_Chinh extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Animator animator;
	private Menu menu;
	private Header header;
	private Content content;
	public GD_Chinh(String title) {
		super(title);
		buidGD_Chinh();
	}
	
	private void buidGD_Chinh() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(createBackground());
		pack();
		setLocationRelativeTo(null);
		
	}
	
	private JPanel createBackground() {
		JPanel background = new JPanel();
		background.setPreferredSize(new Dimension(1400, 800));
		MigLayout layout;
		background.setLayout(layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0"));
		background.setBackground(new Color(245, 245, 245));
		
		background.add(createNav(), "w 230!, spany 2");
		background.add(createHeader(), "h 50!, wrap");
		background.add(createContent(), "w 100%, h 100%");
		TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 50 + (180 * (1f - fraction));
                } else {
                    width = 50 + (180 * fraction);
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
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
		return background;
	}
	
	private Header createHeader() {
		header = new Header();
		return header;
	}
	
	private Menu createNav() {
		menu = new Menu();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                switch (menuIndex) {
                    case 0:
                        if(subMenuIndex == 0)
                            content.showForm(new GD_SoDoPhongHat());
                        else if(subMenuIndex == 1)
                            content.showForm(new GD_DanhSachPhong());
                        break;
                    case 1:
                        content.showForm(new GD_DatPhong());
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
                    default:
                        break;
                }
                if(menuIndex == 5) {
                	if(subMenuIndex == 0)
                    	content.showForm(new GD_ThongKeDoanhThu());
                    else if(subMenuIndex == 1) 
                    	content.showForm(new GD_ThongKeHangHoa());
                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(GD_Chinh.this, item.getIndex(), item.getEventSelected(), Color.WHITE, item.getMenu().getSubMenu());
                int x = GD_Chinh.this.getX() + 50;
                int y = GD_Chinh.this.getY() + com.getY() + 130;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
		return menu;
	}
	
	private Content createContent() {
		content = new Content();
		content.setBackground(new Color(245, 245, 245));
		content.showForm(new GD_SoDoPhongHat());
		return content;
	}
}
