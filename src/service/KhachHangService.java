package service;

import model.KhachHang;
import model.dto.ThongKeChiPhiView;

import java.util.List;
import java.util.Optional;

public interface KhachHangService {
    List<KhachHang> getAll();

    Optional<KhachHang> getById(String maKH);
    
    boolean create(KhachHang khachHang);

    List<ThongKeChiPhiView> getThongKeChiPhi();
}
