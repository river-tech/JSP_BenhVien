package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.KhachHang;
import service.AuthService;
import service.impl.AuthServiceImpl;

import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if ("admin".equals(type)) {
            req.getRequestDispatcher("/WEB-INF/views/login_admin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/login_customer.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        if ("admin".equals(type)) {
            Optional<Admin> admin = authService.loginAdmin(username, password);
            if (admin.isPresent()) {
                session.setAttribute("admin", admin.get());
                session.setAttribute("userType", "admin");
                resp.sendRedirect(req.getContextPath() + "/vaccines/search");
            } else {
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
                req.getRequestDispatcher("/WEB-INF/views/login_admin.jsp").forward(req, resp);
            }
        } else {
            Optional<KhachHang> khachHang = authService.loginCustomer(username, password);
            if (khachHang.isPresent()) {
                session.setAttribute("customer", khachHang.get());
                session.setAttribute("userType", "customer");
                resp.sendRedirect(req.getContextPath() + "/customer/history");
            } else {
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
                req.getRequestDispatcher("/WEB-INF/views/login_customer.jsp").forward(req, resp);
            }
        }
    }
}

