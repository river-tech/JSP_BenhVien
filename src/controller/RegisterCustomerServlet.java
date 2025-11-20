package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KhachHang;
import service.KhachHangService;
import service.impl.KhachHangServiceImpl;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterCustomerServlet extends HttpServlet {

    private final KhachHangService khachHangService = new KhachHangServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register_customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String maKH = req.getParameter("maKH");
        String hoTenKH = req.getParameter("hoTenKH");
        String soDienThoai = req.getParameter("soDienThoai");
        String diaChiKH = req.getParameter("diaChiKH");
        String ngaySinhStr = req.getParameter("ngaySinh");
        String gioiTinh = req.getParameter("gioiTinh");

        LocalDate ngaySinh = null;
        if (ngaySinhStr != null && !ngaySinhStr.isEmpty()) {
            ngaySinh = LocalDate.parse(ngaySinhStr);
        }

        KhachHang khachHang = new KhachHang(maKH, hoTenKH, soDienThoai, diaChiKH, ngaySinh, gioiTinh);

        // Kiểm tra mã khách hàng đã tồn tại chưa
        if (khachHangService.getById(maKH).isPresent()) {
            req.setAttribute("error", "Mã khách hàng đã tồn tại. Vui lòng chọn mã khác.");
            req.getRequestDispatcher("/WEB-INF/views/register_customer.jsp").forward(req, resp);
            return;
        }

        boolean success = khachHangService.create(khachHang);
        if (success) {
            req.setAttribute("success", "Đăng ký khách hàng thành công! Mã khách hàng: " + maKH);
            req.setAttribute("maKH", maKH);
        } else {
            req.setAttribute("error", "Đăng ký thất bại. Vui lòng kiểm tra lại thông tin hoặc liên hệ quản trị viên.");
        }
        req.getRequestDispatcher("/WEB-INF/views/register_customer.jsp").forward(req, resp);
    }
}

