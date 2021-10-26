package gui.component;

import gui.dropshadow.ShadowType;
import gui.swing.panel.PanelShadow;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;

import gui.swing.image.ImageAvatar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Header extends PanelShadow {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel role;
    private JLabel userName;
    private ImageAvatar avartar;
    private JSeparator separator;
    private Thread thread;
    private JLabel lblTime;

    public Header() {
        buidHeader();
    }


    private void buidHeader() {
        setShadowOpacity(0.3f);
        setShadowType(ShadowType.BOT);
        setShadowSize(2);
        
        setLayout(null);
        setBackground(new Color(255, 255, 255));
        add(createUserName());
        add(createRole());
        add(createAvartar());
        add(createSeparator());
        add(createDate());
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                                .addGap(3, 3, 3)
                                .addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userName, GroupLayout.Alignment.TRAILING)
                                        .addComponent(role, GroupLayout.Alignment.TRAILING))
                                .addGap(10)
                                .addComponent(avartar, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userName)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(role))
                                        .addComponent(avartar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(separator))
                                .addContainerGap())
        );
    }

    private JLabel createUserName() {
        userName = new JLabel("Đỗ Huy Hoàng");
        userName.setFont(new Font("sansserif", 1, 12));
        userName.setForeground(new Color(127, 127, 127));
        return userName;
    }

    private JLabel createRole() {
        role = new JLabel("Manager");
        role.setForeground(new Color(127, 127, 127));
        return role;
    }

    private ImageAvatar createAvartar() {
        avartar = new ImageAvatar();
        avartar.setIcon(new ImageIcon(getClass().getResource("/icon/add_male_user_60px.png")));
        return avartar;
    }

    private JSeparator createSeparator() {
        separator = new JSeparator();
        separator.setOrientation(JSeparator.VERTICAL);
        return separator;
    }
    
    private void initTime() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }
    
    private JLabel createDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        lblTime = new JLabel(df.format(new Date()));
        lblTime.setForeground(new Color(127, 127, 127));
        return lblTime;
    }
    
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
