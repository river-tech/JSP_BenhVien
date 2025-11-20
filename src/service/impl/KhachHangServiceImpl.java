package service.impl;

import dao.KhachHangDAO;
import dao.impl.KhachHangDAOImpl;
import model.KhachHang;
import model.dto.ThongKeChiPhiView;
import service.KhachHangService;

import java.util.List;
import java.util.Optional;

public class KhachHangServiceImpl implements KhachHangService {

    private final KhachHangDAO khachHangDAO = new KhachHangDAOImpl();

    @Override
    public List<KhachHang> getAll() {
        return khachHangDAO.findAll();
    }

    @Override
    public Optional<KhachHang> getById(String maKH) {
        return khachHangDAO.findById(maKH);
    }

    @Override
    public boolean create(KhachHang khachHang) {
        return khachHangDAO.insert(khachHang);
    }

    @Override
    public List<ThongKeChiPhiView> getThongKeChiPhi() {
        return khachHangDAO.getThongKeChiPhi();
    }
}
