package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.ThongKeChiPhiView;
import service.ThongKeService;
import service.impl.ThongKeServiceImpl;

import java.io.IOException;
import java.util.List;

public class ThongKeServlet extends HttpServlet {

    private final ThongKeService thongKeService = new ThongKeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ThongKeChiPhiView> data = thongKeService.thongKeTongTien();
        req.setAttribute("statistics", data);
        req.getRequestDispatcher("/WEB-INF/views/statistic.jsp").forward(req, resp);
    }
}
