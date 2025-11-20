package dao;

import model.KhachHang;
import model.dto.ThongKeChiPhiView;

import java.util.List;
import java.util.Optional;

public interface KhachHangDAO {
    List<KhachHang> findAll();

    Optional<KhachHang> findById(String maKH);
    
    Optional<KhachHang> findByUsername(String username);

    boolean insert(KhachHang khachHang);

    boolean update(KhachHang khachHang);

    boolean delete(String maKH);
    
    boolean validatePassword(String username, String password);

    List<ThongKeChiPhiView> getThongKeChiPhi();
}
