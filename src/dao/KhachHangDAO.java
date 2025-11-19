package dao;

import model.KhachHang;
import model.dto.ThongKeChiPhiView;

import java.util.List;
import java.util.Optional;

public interface KhachHangDAO {
    List<KhachHang> findAll();

    Optional<KhachHang> findById(String maKH);

    boolean insert(KhachHang khachHang);

    boolean update(KhachHang khachHang);

    boolean delete(String maKH);

    List<ThongKeChiPhiView> getThongKeChiPhi();
}
