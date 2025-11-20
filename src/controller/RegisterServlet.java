package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.KhachHang;
import service.AuthService;
import service.impl.AuthServiceImpl;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterServlet extends HttpServlet {

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LocalDate ngaySinh = null;
        if (ngaySinhStr != null && !ngaySinhStr.isEmpty()) {
            ngaySinh = LocalDate.parse(ngaySinhStr);
        }

        KhachHang khachHang = new KhachHang(maKH, hoTenKH, soDienThoai, diaChiKH, ngaySinh, gioiTinh);
        khachHang.setUsername(username);
        khachHang.setPassword(password);

        boolean success = authService.registerCustomer(khachHang);
        if (success) {
            HttpSession session = req.getSession();
            session.setAttribute("customer", khachHang);
            session.setAttribute("userType", "customer");
            resp.sendRedirect(req.getContextPath() + "/customer/history");
        } else {
            req.setAttribute("error", "Đăng ký thất bại. Username có thể đã tồn tại.");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}

