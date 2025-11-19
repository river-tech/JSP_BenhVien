package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Vaccine;
import service.VaccineService;
import service.impl.VaccineServiceImpl;

import java.io.IOException;
import java.util.Optional;

public class VaccineEditServlet extends HttpServlet {

    private final VaccineService vaccineService = new VaccineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maVacxin = req.getParameter("maVacxin");
        Optional<Vaccine> vaccine = vaccineService.getById(maVacxin);
        if (!vaccine.isPresent()) {
            resp.sendRedirect(req.getContextPath() + "/vaccines/search?notfound=true");
            return;
        }
        req.setAttribute("vaccine", vaccine.get());
        req.getRequestDispatcher("/WEB-INF/views/edit_vaccine.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Vaccine vaccine = new Vaccine(
                req.getParameter("maVacxin"),
                req.getParameter("tenVacxin"),
                parseInt(req.getParameter("soMui")),
                req.getParameter("moTa"),
                parseInt(req.getParameter("giaVacxin")),
                req.getParameter("tenHangSX")
        );
        boolean success = vaccineService.update(vaccine);
        if (success) {
            resp.sendRedirect(req.getContextPath() + "/vaccines/search?updated=true");
        } else {
            req.setAttribute("error", "Cập nhật thất bại");
            req.setAttribute("vaccine", vaccine);
            req.getRequestDispatcher("/WEB-INF/views/edit_vaccine.jsp").forward(req, resp);
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
