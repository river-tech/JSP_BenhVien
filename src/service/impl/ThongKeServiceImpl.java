package service.impl;

import dao.KhachHangDAO;
import dao.impl.KhachHangDAOImpl;
import model.dto.ThongKeChiPhiView;
import service.ThongKeService;

import java.util.List;

public class ThongKeServiceImpl implements ThongKeService {

    private final KhachHangDAO khachHangDAO = new KhachHangDAOImpl();

    @Override
    public List<ThongKeChiPhiView> thongKeTongTien() {
        return khachHangDAO.getThongKeChiPhi();
    }
}
