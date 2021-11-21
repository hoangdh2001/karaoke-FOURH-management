package gui.swing.model;

public class AutoID {
    public static String generateId(String idCurrent, String role) {
        int i = role.length();
        int soLuong = idCurrent.length() - i;
        int num = Integer.parseInt(idCurrent.substring(i));
        return String.format("%s%0" + soLuong + "d", role, num + 1);
    }
}
