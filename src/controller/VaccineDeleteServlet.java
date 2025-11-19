package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.VaccineService;
import service.impl.VaccineServiceImpl;

import java.io.IOException;

public class VaccineDeleteServlet extends HttpServlet {

    private final VaccineService vaccineService = new VaccineServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maVacxin = req.getParameter("maVacxin");
        vaccineService.delete(maVacxin);
        resp.sendRedirect(req.getContextPath() + "/vaccines/search?deleted=true");
    }
}
