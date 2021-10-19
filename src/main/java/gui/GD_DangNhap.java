package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import gui.dialog.Message;
import gui.swing.button.Button;

public class GD_DangNhap extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xx;
	private int xy;
	private final Animator animator;
	private static final int DEFAULT_FRAME = -1;
	private static final int CLOSE_FRAME = 0;
	private static final int DISPOSE_FRAME = 1;
    private int show = DEFAULT_FRAME;
	private JTextField userField;
	private JPasswordField passField;
	private Button loginBtn;

	public GD_DangNhap(String title) {
		super(title);
		buidGD_DangNhap();
		
		setOpacity(0f);
        getContentPane().setBackground(Color.WHITE);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show == DEFAULT_FRAME) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (show == CLOSE_FRAME)
                	System.exit(0);
                else if(show == DISPOSE_FRAME)
                	dispose();
            }

        };
        animator = new Animator(200, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.start();
	}
	
	private void buidGD_DangNhap() {
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel background = createBackgroudImage();
		background.setLayout(null);
		
		background.add(createTitle());
		
		background.add(createExitBtn());
		
		background.add(createMinimizeBtn());
		
		background.add(createWindowHide());
		
		background.add(createFormLogin());
		
		setContentPane(background);
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createBackgroudImage() {
		final Image image = new ImageIcon(getClass().getResource("/icon/Screenshot 2021-10-11 194917.png")).getImage();
		JPanel backgroundImage = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2d.drawImage(image, 0, 0, null);
			}
		};
		backgroundImage.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		return backgroundImage;
	}
	
	private JLabel createTitle() {
		JLabel title = new JLabel(getTitle());
		title.setFont(new Font("Segoe ui Semilight", Font.PLAIN, 16));
		title.setForeground(Color.LIGHT_GRAY);
		title.setBounds(15, 10, 100, 30);
		return title;
	}
	
	private JButton createExitBtn() {
		Image image = new ImageIcon(getClass().getResource("/icon/multiply_30px.png")).getImage();
		JButton exitBtn = new JButton();
		exitBtn.setIcon(new ImageIcon(image));
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusable(false);
		exitBtn.setToolTipText("Close");
		exitBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/icon/multiply_30px_red.png")));
		exitBtn.setBounds(935, 15, image.getWidth(null), image.getHeight(null));
		exitBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		return exitBtn;
	}
	
	private JButton createMinimizeBtn() {
		Image image = new ImageIcon(getClass().getResource("/icon/horizontal_line_24px.png")).getImage();
		JButton minimizeBtn = new JButton();
		minimizeBtn.setIcon(new ImageIcon(image));
		minimizeBtn.setContentAreaFilled(false);
		minimizeBtn.setToolTipText("Minimize");
		minimizeBtn.setFocusable(false);
		minimizeBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/icon/horizontal_line_24px_red.png")));
		minimizeBtn.setBounds(900, 18, image.getWidth(null), image.getHeight(null));
		minimizeBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setState(ICONIFIED);
			}
		});
		return minimizeBtn;
	}
	
	private JPanel createWindowHide() {
		JPanel windowHide = new JPanel();
		windowHide.setOpaque(false);
		windowHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				xx = e.getX();
				xy = e.getY();
			}
		});
		windowHide.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xx, y - xy);
			}
		});
		windowHide.setBounds(0, 0, 976, 55);
		return windowHide;
	}
	
	private JPanel createFormLogin() {
		JPanel formLogin = new JPanel();
		formLogin.setLayout(null);
		formLogin.setOpaque(false);
		
		JLabel title;
		formLogin.add(title = new JLabel("KARAOKE FOURH", JLabel.CENTER));
		title.setFont(new Font("Segoe ui Semilight", Font.BOLD, 25));
		title.setForeground(Color.LIGHT_GRAY);
		title.setBounds(0, 0, 330, 50);
		
		
		JLabel userText;
		formLogin.add(userText = new JLabel("TÊN ĐĂNG NHẬP"));
		userText.setFont(new Font("Segoe ui Semilight", Font.PLAIN, 15));
		userText.setForeground(Color.LIGHT_GRAY);
		userText.setBounds(0, 80, 200, 30);
		
		formLogin.add(userField = new JTextField());
		userField.setFont(new Font("Segoe ui Semilight", Font.PLAIN, 18));
		userField.setBorder(null);
		userField.setOpaque(false);
		userField.setCaretColor(Color.LIGHT_GRAY);
		userField.setForeground(Color.LIGHT_GRAY);
		userField.setSelectionColor(Color.LIGHT_GRAY);
		userField.setBounds(5, 110, 330, 30);
		
		final JSeparator userSeparator;
		formLogin.add(userSeparator = new JSeparator());
		userSeparator.setBackground(new Color(35, 35, 35));
		userSeparator.setForeground(new Color(35, 35, 35));
		userSeparator.setBounds(0, 150, 330, 20);
		
		userField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				userSeparator.setBackground(Color.LIGHT_GRAY);
				userSeparator.setForeground(Color.LIGHT_GRAY);
			}
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				userSeparator.setBackground(new Color(35, 35, 35));
				userSeparator.setForeground(new Color(35, 35, 35));
			}
		});
		
		JLabel passText;
		formLogin.add(passText = new JLabel("MẬT KHẨU"));
		passText.setFont(new Font("Segoe ui Semilight", Font.PLAIN, 15));
		passText.setForeground(Color.LIGHT_GRAY);
		passText.setBounds(0, 190, 200, 30);
		
		
		formLogin.add(passField = new JPasswordField());
		passField.setFont(new Font("Segoe ui Semilight", Font.PLAIN, 18));
		passField.setBorder(null);
		passField.setOpaque(false);
		passField.setForeground(Color.LIGHT_GRAY);
		passField.setCaretColor(Color.LIGHT_GRAY);
		passField.setSelectionColor(Color.LIGHT_GRAY);
		passField.setBounds(5, 220, 330, 30);
		
		final JSeparator pasSeparator;
		formLogin.add(pasSeparator = new JSeparator());
		pasSeparator.setBackground(new Color(35, 35, 35));
		pasSeparator.setForeground(new Color(35, 35, 35));
		pasSeparator.setBounds(0, 260, 330, 20);
		
		passField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				pasSeparator.setBackground(Color.LIGHT_GRAY);
				pasSeparator.setForeground(Color.LIGHT_GRAY);
			}
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				pasSeparator.setBackground(new Color(35, 35, 35));
				pasSeparator.setForeground(new Color(35, 35, 35));
			}
		});
		
		formLogin.add(loginBtn = new Button("Đăng nhập", false));
		loginBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		loginBtn.setContentAreaFilled(false);
		loginBtn.setFont(new Font("Segoe ui Semilight", Font.PLAIN, 16));
		loginBtn.setForeground(Color.LIGHT_GRAY);
		loginBtn.setBackground(new Color(52, 52, 52));
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				loginBtn.setBackground(new Color(70, 70, 70));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				loginBtn.setBackground(new Color(52, 52, 52));
			}
		});
		loginBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Message.showMessageDialog(null, "Đăng nhập thành công!");
				disposeFrame();
				new GD_Chinh("QUẢN LÝ KARAOKE FOURH").setVisible(true);
			}
		});
		loginBtn.setBounds(0, 320, 330, 50);
		
		
		formLogin.setBounds(580, 100, 400, 500);
		return formLogin;
	}
	
	public void closeFrame() {
		if (animator.isRunning()) {
            animator.stop();
        }
        show = CLOSE_FRAME;
        animator.start();
	}
	
	public void disposeFrame() {
		if(animator.isRunning()) {
			animator.stop();
		}
		show = DISPOSE_FRAME;
		animator.start();
	}
}
