package gui.swing.event;

public interface EventLogin {
    public void login(String sdt, byte[] matKhau);
    public void forgotPass(String sdt, String email, byte[] rePass);
}
