package service;

import model.LichSuTiemPhong;
import model.dto.LichSuTiemPhongView;

import java.util.List;

public interface LichSuTiemPhongService {
    List<LichSuTiemPhongView> getHistory();
    
    List<LichSuTiemPhong> getByMaKH(String maKH);
    
    String getVaccineUsedForDisease(String maKH, String maBenh);
    
    boolean registerVaccination(LichSuTiemPhong record, String maBenh);
    
    boolean registerVaccinationWithDate(LichSuTiemPhong record, String maBenh);
}
