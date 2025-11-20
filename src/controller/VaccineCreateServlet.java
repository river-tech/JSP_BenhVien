package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PhongBenh;
import model.Vaccine;
import service.BenhService;
import service.PhongBenhService;
import service.VaccineService;
import service.impl.BenhServiceImpl;
import service.impl.PhongBenhServiceImpl;
import service.impl.VaccineServiceImpl;

import java.io.IOException;

public class VaccineCreateServlet extends HttpServlet {

    private final VaccineService vaccineService = new VaccineServiceImpl();
    private final BenhService benhService = new BenhServiceImpl();
    private final PhongBenhService phongBenhService = new PhongBenhServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("diseases", benhService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/create_vaccine.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Vaccine vaccine = buildVaccineFromRequest(req);
        // Validate dữ liệu
        if (vaccine.getMaVacxin() == null || vaccine.getMaVacxin().trim().isEmpty()) {
            req.setAttribute("error", "Mã vaccine không được để trống");
            req.getRequestDispatcher("/WEB-INF/views/create_vaccine.jsp").forward(req, resp);
            return;
        }
        
        // Lấy danh sách bệnh đã chọn
        String[] maBenhArray = req.getParameterValues("maBenh");
        if (maBenhArray == null || maBenhArray.length == 0) {
            req.setAttribute("error", "Vui lòng chọn ít nhất một bệnh mà vaccine này có thể phòng được");
            req.setAttribute("diseases", benhService.getAll());
            req.getRequestDispatcher("/WEB-INF/views/create_vaccine.jsp").forward(req, resp);
            return;
        }
        
        boolean success = vaccineService.create(vaccine);
        if (success) {
            // Tạo các record trong PHONGBENH
            for (String maBenh : maBenhArray) {
                PhongBenh phongBenh = new PhongBenh(vaccine.getMaVacxin(), maBenh, 
                    "Vaccine " + vaccine.getTenVacxin() + " phòng bệnh " + maBenh);
                phongBenhService.create(phongBenh);
            }
            resp.sendRedirect(req.getContextPath() + "/vaccines/search?success=true");
        } else {
            req.setAttribute("error", "Không thể thêm vaccine. Có thể mã vaccine đã tồn tại.");
            req.setAttribute("diseases", benhService.getAll());
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
