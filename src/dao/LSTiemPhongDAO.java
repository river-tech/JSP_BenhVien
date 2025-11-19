package dao;

import model.LichSuTiemPhong;
import model.dto.LichSuTiemPhongView;

import java.util.List;

public interface LSTiemPhongDAO {
    List<LichSuTiemPhong> findAll();

    boolean insert(LichSuTiemPhong record);

    List<LichSuTiemPhongView> findHistoryView();
}
