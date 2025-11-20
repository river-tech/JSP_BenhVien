package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.KhachHang;
import model.dto.LichSuTiemPhongView;
import service.LichSuTiemPhongService;
import service.impl.LichSuTiemPhongServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerHistoryServlet extends HttpServlet {

    private final LichSuTiemPhongService historyService = new LichSuTiemPhongServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            resp.sendRedirect(req.getContextPath() + "/login?type=customer");
            return;
        }

        KhachHang customer = (KhachHang) session.getAttribute("customer");
        List<LichSuTiemPhongView> allHistory = historyService.getHistory();
        
        // Lọc chỉ lịch sử của khách hàng hiện tại
        List<LichSuTiemPhongView> customerHistory = allHistory.stream()
                .filter(h -> h.getMaKH().equals(customer.getMaKH()))
                .collect(Collectors.toList());

        req.setAttribute("history", customerHistory);
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/views/customer_history.jsp").forward(req, resp);
    }
}

