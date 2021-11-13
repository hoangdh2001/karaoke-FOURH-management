package gui.swing.event;

import java.awt.Component;

public interface EventLogin {
    public void login(String sdt, byte[] matKhau);
    public void searchUser(String sdtOrEmail, Component comShow, Component comHidden);
    public void forgotPass(byte[] rePass);
}
