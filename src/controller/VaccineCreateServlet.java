package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Vaccine;
import service.VaccineService;
import service.impl.VaccineServiceImpl;

import java.io.IOException;

public class VaccineCreateServlet extends HttpServlet {

    private final VaccineService vaccineService = new VaccineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create_vaccine.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Vaccine vaccine = buildVaccineFromRequest(req);
        boolean success = vaccineService.create(vaccine);
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/vaccines/search?success=true");
        } else {
            req.setAttribute("error", "Không thể thêm vaccine. Vui lòng thử lại");
            req.getRequestDispatcher("/WEB-INF/views/create_vaccine.jsp").forward(req, resp);
        }
    }

    private Vaccine buildVaccineFromRequest(HttpServletRequest req) {
        String maVacxin = req.getParameter("maVacxin");
        String tenVacxin = req.getParameter("tenVacxin");
        int soMui = parseInt(req.getParameter("soMui"));
        String moTa = req.getParameter("moTa");
        int giaVacxin = parseInt(req.getParameter("giaVacxin"));
        String tenHangSX = req.getParameter("tenHangSX");
        return new Vaccine(maVacxin, tenVacxin, soMui, moTa, giaVacxin, tenHangSX);
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
