package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Vaccine;
import service.VaccineService;
import service.impl.VaccineServiceImpl;

import java.io.IOException;
import java.util.List;

public class VaccineSearchServlet extends HttpServlet {

    private final VaccineService vaccineService = new VaccineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleSearch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        handleSearch(req, resp);
    }

    private void handleSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maVacxin = req.getParameter("maVacxin");
        String tenVacxin = req.getParameter("tenVacxin");
        String tenHangSX = req.getParameter("tenHangSX");
        List<Vaccine> vaccines = vaccineService.search(maVacxin, tenVacxin, tenHangSX);
        req.setAttribute("vaccines", vaccines);
        req.getRequestDispatcher("/WEB-INF/views/search_vaccine.jsp").forward(req, resp);
    }
}
