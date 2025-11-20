package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LichSuTiemPhong;
import service.BenhService;
import service.KhachHangService;
import service.LichSuTiemPhongService;
import service.VaccineService;
import service.impl.BenhServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.LichSuTiemPhongServiceImpl;
import service.impl.VaccineServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class RegisterVaccinationServlet extends HttpServlet {

    private final LichSuTiemPhongService lsService = new LichSuTiemPhongServiceImpl();
    private final KhachHangService khService = new KhachHangServiceImpl();
    private final VaccineService vcService = new VaccineServiceImpl();
    private final BenhService benhService = new BenhServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", khService.getAll());
        // Không load tất cả vaccine nữa, sẽ load theo bệnh được chọn
        req.setAttribute("diseases", benhService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/register_vaccination.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String maKH = req.getParameter("maKH");
        String maVacxin = req.getParameter("maVacxin");
        String maBenh = req.getParameter("maBenh");
        String ngayTiemStr = req.getParameter("ngayTiemPhong");
        String ngayHenStr = req.getParameter("ngayHenTiepTheo");
        int sttMui = Integer.parseInt(req.getParameter("sttMui"));

        // Kiểm tra khách hàng và vaccine có tồn tại không
        if (!khService.getById(maKH).isPresent() || !vcService.getById(maVacxin).isPresent()) {
            req.setAttribute("error", "Khách hàng hoặc vaccine không tồn tại");
            doGet(req, resp);
            return;
        }

        // Kiểm tra ràng buộc: nếu đã tiêm vaccine khác cho bệnh này
        String existingVaccine = lsService.getVaccineUsedForDisease(maKH, maBenh);
        if (existingVaccine != null && !existingVaccine.equals(maVacxin)) {
            req.setAttribute("error", "Khách hàng đã tiêm vaccine khác cho bệnh này. Không thể đổi vaccine!");
            doGet(req, resp);
            return;
        }

        LocalDate ngayTiem = LocalDate.parse(ngayTiemStr);
        LocalDate ngayHen = null;
        if (ngayHenStr != null && !ngayHenStr.trim().isEmpty()) {
            ngayHen = LocalDate.parse(ngayHenStr);
        }
        
        LichSuTiemPhong record = new LichSuTiemPhong(maKH, maVacxin, sttMui, ngayTiem, ngayHen);
        
        // Nếu đã có ngày hẹn từ form, set vào record và kiểm tra ràng buộc trước khi insert
        if (ngayHen != null) {
            // Vẫn cần kiểm tra ràng buộc vaccine
            boolean success = lsService.registerVaccinationWithDate(record, maBenh);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/history?success=true");
            } else {
                req.setAttribute("error", "Đăng ký tiêm phòng thất bại. Có thể đã vi phạm ràng buộc vaccine.");
                doGet(req, resp);
            }
        } else {
            // Nếu chưa có ngày hẹn, tự động tính
            boolean success = lsService.registerVaccination(record, maBenh);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/history?success=true");
            } else {
                req.setAttribute("error", "Đăng ký tiêm phòng thất bại");
                doGet(req, resp);
            }
        }
    }
}

