package service.impl;

import dao.LSTiemPhongDAO;
import dao.PhongBenhDAO;
import dao.VaccineDAO;
import dao.impl.LSTiemPhongDAOImpl;
import dao.impl.PhongBenhDAOImpl;
import dao.impl.VaccineDAOImpl;
import model.LichSuTiemPhong;
import model.PhongBenh;
import model.Vaccine;
import model.dto.LichSuTiemPhongView;
import service.LichSuTiemPhongService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LichSuTiemPhongServiceImpl implements LichSuTiemPhongService {

    private final LSTiemPhongDAO lsTiemPhongDAO = new LSTiemPhongDAOImpl();
    private final VaccineDAO vaccineDAO = new VaccineDAOImpl();
    private final PhongBenhDAO phongBenhDAO = new PhongBenhDAOImpl();

    @Override
    public List<LichSuTiemPhongView> getHistory() {
        return lsTiemPhongDAO.findHistoryView();
    }

    @Override
    public List<LichSuTiemPhong> getByMaKH(String maKH) {
        return lsTiemPhongDAO.findByMaKH(maKH);
    }

    @Override
    public String getVaccineUsedForDisease(String maKH, String maBenh) {
        return lsTiemPhongDAO.getVaccineUsedForDisease(maKH, maBenh);
    }

    @Override
    public boolean registerVaccination(LichSuTiemPhong record, String maBenh) {
        // Kiểm tra ràng buộc: nếu đã tiêm vaccine khác cho bệnh này thì không cho phép
        String existingVaccine = getVaccineUsedForDisease(record.getMaKH(), maBenh);
        if (existingVaccine != null && !existingVaccine.equals(record.getMaVacxin())) {
            return false; // Đã tiêm vaccine khác cho bệnh này
        }

        // Đảm bảo có record trong PHONGBENH (quan hệ vaccine - bệnh)
        if (!phongBenhDAO.exists(record.getMaVacxin(), maBenh)) {
            PhongBenh phongBenh = new PhongBenh(record.getMaVacxin(), maBenh, "Phòng bệnh " + maBenh);
            phongBenhDAO.insert(phongBenh);
        }

        // Lấy thông tin vaccine để tính ngày hẹn
        Optional<Vaccine> vaccineOpt = vaccineDAO.findById(record.getMaVacxin());
        if (!vaccineOpt.isPresent()) {
            return false;
        }
        Vaccine vaccine = vaccineOpt.get();

        // Tính ngày hẹn tiếp theo nếu chưa tiêm đủ mũi
        // Dùng giá trị mặc định 30 ngày vì không có cột ThoiGianCho trong database
        if (record.getSttMui() < vaccine.getSoMui()) {
            LocalDate ngayHen = record.getNgayTiemPhong().plusDays(30); // Mặc định 30 ngày
            record.setNgayHenTiepTheo(ngayHen);
        } else {
            record.setNgayHenTiepTheo(null); // Đã tiêm đủ mũi
        }

        return lsTiemPhongDAO.insert(record);
    }

    @Override
    public boolean registerVaccinationWithDate(LichSuTiemPhong record, String maBenh) {
        // Kiểm tra ràng buộc: nếu đã tiêm vaccine khác cho bệnh này thì không cho phép
        String existingVaccine = getVaccineUsedForDisease(record.getMaKH(), maBenh);
        if (existingVaccine != null && !existingVaccine.equals(record.getMaVacxin())) {
            return false; // Đã tiêm vaccine khác cho bệnh này
        }
        
        // Đảm bảo có record trong PHONGBENH (quan hệ vaccine - bệnh)
        if (!phongBenhDAO.exists(record.getMaVacxin(), maBenh)) {
            PhongBenh phongBenh = new PhongBenh(record.getMaVacxin(), maBenh, "Phòng bệnh " + maBenh);
            phongBenhDAO.insert(phongBenh);
        }
        
        // Nếu đã có ngày hẹn từ form, giữ nguyên, không tính lại
        return lsTiemPhongDAO.insert(record);
    }
}
