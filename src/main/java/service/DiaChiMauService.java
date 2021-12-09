package service;

import java.util.List;

public interface DiaChiMauService {

    /**
     * lấy tất cả tỉnh/thành phố
     *
     * @return
     */
    public List<String> getAllTinhThanh();

    /**
     * lấy quận/huyện dựa vào tỉnh thành
     *
     * @param tinhThanh
     * @return
     */
    public List<String> getQuanHuyenTheoTinhThanh(String tinhThanh);

    /**
     * lấy phường/xã dựa vào quận/huyện và tỉnh thành
     *
     * @param quanHuyen
     * @param tinhThanh
     * @return
     */
    public List<String> getPhuongXaTheoQHTH(String quanHuyen, String tinhThanh);
}
