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
    private final DecimalFormat df = new DecimalFormat("##0.#");

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
            double width = pageFormat.getImageableWidth();
            g2.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
            int y = 20;
            int yShift = 10;
            int headerRectHeight = 15;
            
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            g2.drawImage(icon.getImage(), 50, 20, 90, 30, null); y += yShift + 30;
            g2.drawString("-------------------------------------", 12, y); y += yShift;
            g2.drawString("KARAOKE FOURH          Ngày:" + formatNgay.format(hoaDon.getNgayLapHoaDon()), 12, y); y += yShift;
            g2.drawString("KARAOKE GÒ VẤP         Ngày:" + formatTime.format(hoaDon.getNgayLapHoaDon()), 12, y); y += yShift;
            g2.drawString("DT:0368232674          So HD:" + hoaDon.getMaHoaDon(), 12, y); y += yShift;
            g2.drawString("             NVLT:" + hoaDon.getNhanVien().getTenNhanVien(), 12, y); y += yShift;
            g2.drawString("                HOA DON              ", 12, y); y += yShift;
            g2.drawString("Mặt hàng     sl    Gia     Thành tiền", 12, y); y += yShift;
            g2.drawString("-------------------------------------", 12, y); y += yShift;
            List<ChiTietHoaDon> chiTietHoaDons = hoaDon.getDsChiTietHoaDon();
            for (int i = 0; i < 10; i++) {
                g2.drawString("  " + i + chiTietHoaDons.get(i).getMatHang().getTenMatHang(), 10, y); y += yShift;
                g2.drawString("         " + chiTietHoaDons.get(i).getSoLuong() + "  " + df.format(chiTietHoaDons.get(i).getMatHang().getDonGia())
                        + "  " + chiTietHoaDons.get(i).getThanhTien(), 10, y); y += yShift;
            }
            g2.drawString("-------------------------------------", 12, y); y += yShift;
            g2.drawString("TONG CONG:  " + getTongSoLuongMatHang() + "       " + hoaDon.getTongTienMatHang(), 12, y); y += yShift;
            g2.drawString("TIEN PHONG: " + hoaDon.getGioHat() + " X " + hoaDon.getPhong().getLoaiPhong().getGiaPhong() + "   " + hoaDon.getDonGiaPhong(), 12, y); y += yShift;
            g2.drawString("TIEN PHONG CU:              " + hoaDon.getDonGiaPhongCu(), 12, y); y += yShift;
            g2.drawString("CHIET KHAU:                 " + chietKhau(), 12, y); y += yShift;
            g2.drawString("THANH TOAN:                 " + hoaDon.getTongHoaDon(), 12, y); y += yShift;
            g2.drawString("TIEN KHACH DUA:             " + hoaDon.getTienKhachDua(), 12, y); y += yShift;
            g2.drawString("-------------------------------------", 12, y); y += yShift;
            g2.drawString("     Cám ơn quý khách hẹn gặp lại    ", 12, y); y += yShift;
            g2.drawString("-------------------------------------", 12, y);
            g2.setColor(Color.RED);
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
