package entity;

import gui.swing.table2.EventAction;
import gui.swing.model.ModelAction;
import java.text.DecimalFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JCheckBox;
import objectcombobox.ObjectComboBox;

@Entity
@Table(name = "Phong")
@NamedQueries({
    @NamedQuery(name = "getDsPhong", query = "select p from Phong p")
})
public class Phong {

    @Id
    private String maPhong;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenPhong;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "nvarchar(255)")
    private TrangThaiPhong trangThai;
    @ManyToOne
    @JoinColumn(name = "maLoaiPhong", nullable = false)
    private LoaiPhong loaiPhong;
    private int tang;
    @JoinColumn(columnDefinition = "image")
    private byte[] anhPhong;

    /**
     * @param maPhong
     * @param tenPhong
     * @param trangThai
     * @param loaiPhong
     * @param tang
     * @param anhPhong
     */
    public Phong(String maPhong, String tenPhong, TrangThaiPhong trangThai, LoaiPhong loaiPhong, int tang, byte[] anhPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.loaiPhong = loaiPhong;
        this.tang = tang;
        this.anhPhong = anhPhong;
    }

    /**
     *
     */
    public Phong() {
    }

    /**
     * @return the maPhong
     */
    public String getMaPhong() {
        return maPhong;
    }

    /**
     * @param maPhong the maPhong to set
     */
    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    /**
     * @return the tenPhong
     */
    public String getTenPhong() {
        return tenPhong;
    }

    /**
     * @param tenPhong the tenPhong to set
     */
    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiPhong getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiPhong trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the loaiPhong
     */
    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    /**
     * @param loaiPhong the loaiPhong to set
     */
    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    /**
     * @return the tang
     */
    public int getTang() {
        return tang;
    }
    
    /**
     * @param tang the tang to set
     */
    public void setTang(int tang) {
        this.tang = tang;
    }
    /**
     * @return the anhPhong
     */
    public byte[] getAnhPhong() {
        return anhPhong;
    }
    
    /**
     * @param anhPhong 
     */
    public void setAnhPhong(byte[] anhPhong) {
        this.anhPhong = anhPhong;
    }
    
    @Override
    public String toString() {
        return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", trangThai=" + trangThai + ", loaiPhong="
                + loaiPhong + "]";
    }
    
    public Object[] convertToRowTable(EventAction event) {
        return new Object[]{JCheckBox.class,  maPhong, tenPhong, tang, trangThai, loaiPhong.getTenLoaiPhong(), new ModelAction(this, event)};
    }
    
    public Object[] convertToRowTableInGDoiPhong() {
        DecimalFormat df;
            df = new DecimalFormat("#,##0.00");
        return new Object[]{new ObjectComboBox(tenPhong,maPhong),loaiPhong.getTenLoaiPhong(), tang, df.format(loaiPhong.getGiaPhong())};
    }

    public Object[] convertToRowTableInGDDatPhong() {
        DecimalFormat df;
        df = new DecimalFormat("#,##0.00");
        return new Object[]{new ObjectComboBox(tenPhong,maPhong),loaiPhong.getTenLoaiPhong(), df.format(loaiPhong.getGiaPhong())};
    }
}
