package service;

import entity.NhaCungCap;
import java.util.List;

public interface NhaCungCapService {
//  Nha Cung Cap
    public boolean addNhaCungCap(NhaCungCap ncc);
    public boolean updateNhaCungCap(NhaCungCap ncc);
    public List<NhaCungCap> getNhaCungCap();
    public NhaCungCap getNhaCungCapById(String id);
    public String getMaxID();
}
