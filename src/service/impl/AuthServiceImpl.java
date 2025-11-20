package service.impl;

import dao.AdminDAO;
import dao.KhachHangDAO;
import dao.impl.AdminDAOImpl;
import dao.impl.KhachHangDAOImpl;
import model.Admin;
import model.KhachHang;
import service.AuthService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final AdminDAO adminDAO = new AdminDAOImpl();
    private final KhachHangDAO khachHangDAO = new KhachHangDAOImpl();

    @Override
    public Optional<Admin> loginAdmin(String username, String password) {
        // Hardcode admin account: username = "admin", password = "admin"
        // Không cần query database
        if ("admin".equals(username) && "admin".equals(password)) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setHoTen("Quản trị viên");
            admin.setEmail("admin@benhvien.vn");
            return Optional.of(admin);
        }
        return Optional.empty();
    }

    @Override
    public Optional<KhachHang> loginCustomer(String username, String password) {
        Optional<KhachHang> khachHang = khachHangDAO.findByUsername(username);
        if (khachHang.isPresent() && khachHangDAO.validatePassword(username, password)) {
            return khachHang;
        }
        return Optional.empty();
    }

    @Override
    public boolean registerCustomer(KhachHang khachHang) {
        // Kiểm tra username đã tồn tại chưa
        if (khachHangDAO.findByUsername(khachHang.getUsername()).isPresent()) {
            return false;
        }
        return khachHangDAO.insert(khachHang);
    }
}

