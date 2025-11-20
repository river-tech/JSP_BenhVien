package service;

import model.Admin;
import model.KhachHang;

import java.util.Optional;

public interface AuthService {
    Optional<Admin> loginAdmin(String username, String password);
    Optional<KhachHang> loginCustomer(String username, String password);
    boolean registerCustomer(KhachHang khachHang);
}

