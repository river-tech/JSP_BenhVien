package dao;

import model.LichSuTiemPhong;
import model.dto.LichSuTiemPhongView;

import java.util.List;

public interface LSTiemPhongDAO {
    List<LichSuTiemPhong> findAll();
    
    List<LichSuTiemPhong> findByMaKH(String maKH);

    boolean insert(LichSuTiemPhong record);
    
    // Kiểm tra xem khách hàng đã tiêm vaccine nào cho bệnh này chưa
    String getVaccineUsedForDisease(String maKH, String maBenh);

    List<LichSuTiemPhongView> findHistoryView();
}
