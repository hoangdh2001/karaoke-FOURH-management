package gui.component;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.ImageIcon;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BillPrintable implements Printable {

    private HoaDon hoaDon;
    private final SimpleDateFormat formatNgay = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
    private final DecimalFormat df = new DecimalFormat("#,##0");

    public BillPrintable(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        int row = hoaDon.getDsChiTietHoaDon().size();
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/logo_small.png"));
        if (pageIndex > 0) {
            System.out.println("Vào");
            return NO_SUCH_PAGE;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
        int y = 20;
        int yShift = 10;

        g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
        g2.drawImage(icon.getImage(), 70, 0, 100, 100, null);
        y += yShift + 45;
        g2.drawString("------------------------------------------", 12, y);
        y += yShift;
        g2.drawString("KARAOKE FOURH            Ngày:" + formatNgay.format(hoaDon.getNgayLapHoaDon()), 12, y);
        y += yShift;
        g2.drawString("KARAOKE GÒ VẤP              Gio:" + formatTime.format(hoaDon.getNgayLapHoaDon()), 12, y);
        y += yShift;
        g2.drawString("DT:0368232674            So HD:" + hoaDon.getMaHoaDon(), 12, y);
        y += yShift + 5;
        g2.drawString("                NV:" + hoaDon.getNhanVien().getTenNhanVien(), 12, y);
        y += yShift + 10;
        g2.setFont(new Font("Monospaced", Font.BOLD, 9));
        g2.drawString("                HOA DON                  ", 12, y);
        y += yShift + 10;
        g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
        g2.drawString(String.format("%-14s %-6s %s %18s", "Mặt hàng", "SL", "Gia", "Thành tiền"), 12, y);
        y += yShift;
        g2.drawString("------------------------------------------", 12, y);
        y += yShift;
        List<ChiTietHoaDon> chiTietHoaDons = hoaDon.getDsChiTietHoaDon();
        for (int i = 0; i < row; i++) {
            g2.drawString((i + 1) + ". " + chiTietHoaDons.get(i).getMatHang().getTenMatHang(), 10, y);
            y += yShift;
            g2.drawString(String.format("%-12s %-6s %-7s %12s",
                    "", chiTietHoaDons.get(i).getSoLuong(), df.format(chiTietHoaDons.get(i).getMatHang().getDonGia()),
                    df.format(chiTietHoaDons.get(i).getThanhTien())), 10, y);
            y += yShift;
        }
        g2.drawString("------------------------------------------", 12, y);
        y += yShift;
        g2.drawString(String.format("%-11s %-3d %24s", "TONG CONG:", getTongSoLuongMatHang(), df.format(hoaDon.getTongTienMatHang())), 12, y);
        y += yShift;
        g2.drawString(String.format("%s %31s", "GIO HAT:", hoaDon.getGioHat() + " X " + df.format(hoaDon.getPhong().getLoaiPhong().getGiaPhong())), 12, y);
        y += yShift;
        g2.drawString(String.format("%s %28s", "TIEN PHONG:", df.format(hoaDon.getDonGiaPhong())), 12, y);
        y += yShift;
        if (hoaDon.getDonGiaPhongCu() != 0) {
            g2.drawString(String.format("%s %25s", "TIEN PHONG CU:", df.format(hoaDon.getDonGiaPhongCu())), 12, y);
            y += yShift;
        }
        if (hoaDon.getTienCoc() != 0) {
            g2.drawString(String.format("%s %21s", "DA COC:", df.format(hoaDon.getDonGiaPhongCu())), 12, y);
            y += yShift;
        }
        g2.drawString(String.format("%s %28s", "CHIET KHAU:", df.format(chietKhau())), 12, y);
        y += yShift;
        g2.drawString(String.format("%s %28s", "THANH TOAN:", df.format(hoaDon.getTongHoaDon())), 12, y);
        y += yShift;
        g2.drawString(String.format("%s %24s", "TIEN KHACH DUA:", df.format(hoaDon.getTienKhachDua())), 12, y);
        y += yShift;
        if (hoaDon.getKhachHang() != null) {
            g2.drawString(String.format("%s %28s", "KHACH HANG:", hoaDon.getKhachHang().getTenKhachHang()), 12, y);
            y += yShift + 30;
        }
        g2.drawString("------------------------------------------", 12, y);
        y += yShift;
        g2.drawString("       Cám ơn quý khách hẹn gặp lại       ", 12, y);
        y += yShift;
        g2.drawString("------------------------------------------", 12, y);
        return PAGE_EXISTS;
    }

    private int getTongSoLuongMatHang() {
        int tongSl = 0;
        for (ChiTietHoaDon chiTietHoaDon : hoaDon.getDsChiTietHoaDon()) {
            tongSl += chiTietHoaDon.getSoLuong();
        }
        return tongSl;
    }

    private double chietKhau() {
        return hoaDon.getTongHoaDon() / (1 - hoaDon.getChietKhau()) - hoaDon.getTongHoaDon();
    }

}
