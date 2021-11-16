package gui.swing.model;

public class AutoID {
    /**
     * Thuật toán tạo mã tăng tự động
     *
     * @param idCurrent mã hiện tại
     * @return idNew mã được tăng lên 1 Ví dụ: idCurrent= "KH00001" ->
     * idNew="NV0002"
     */
    public static String generateId(String idCurrent, String role) {
        String[] idSplit = idCurrent.split(""); // tách các chữ số trong id ra thành từng phần tử của mảng

        int i = 2; //vị trí chia mã nhân viên ra làm 2 phần, ví dụ KH000039 -> phần đầu: KH0000 ; phần đuôi:39
        while (i != idSplit.length) {
            if (!idSplit[i].equals("0")) {
                break;
            }
            i++;
        }

        String head_id = role; // phần đầu của mã nhân viên mới
        String tail_id = ""; // phần đuôi của mã nhân viên mới
        for (int j = 2; j < idSplit.length; j++) {

            if (j < i) {
                head_id += idSplit[j];
            } else {
                tail_id += idSplit[j];
            }
        }

        tail_id = Integer.toString(Integer.parseInt(tail_id) + 1); // tăng id lên 1
        return head_id + tail_id;
    }
}
