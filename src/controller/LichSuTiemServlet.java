package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.LichSuTiemPhongView;
import service.LichSuTiemPhongService;
import service.impl.LichSuTiemPhongServiceImpl;

import java.io.IOException;
import java.util.List;

public class LichSuTiemServlet extends HttpServlet {

    private final LichSuTiemPhongService historyService = new LichSuTiemPhongServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LichSuTiemPhongView> history = historyService.getHistory();
        req.setAttribute("history", history);
        req.getRequestDispatcher("/WEB-INF/views/history.jsp").forward(req, resp);
    }
}
