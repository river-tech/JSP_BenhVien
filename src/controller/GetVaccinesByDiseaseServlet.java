package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Vaccine;
import service.VaccineService;
import service.impl.VaccineServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetVaccinesByDiseaseServlet extends HttpServlet {

    private final VaccineService vaccineService = new VaccineServiceImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String maBenh = req.getParameter("maBenh");
        
        if (maBenh == null || maBenh.trim().isEmpty()) {
            resp.getWriter().write("[]");
            return;
        }
        
        List<Vaccine> vaccines = vaccineService.getByMaBenh(maBenh);
        
        // Convert to JSON format
        List<Map<String, Object>> result = vaccines.stream().map(vc -> {
            Map<String, Object> map = new HashMap<>();
            map.put("maVacxin", vc.getMaVacxin());
            map.put("tenVacxin", vc.getTenVacxin());
            map.put("soMui", vc.getSoMui());
            map.put("giaVacxin", vc.getGiaVacxin());
            map.put("tenHangSX", vc.getTenHangSX());
            map.put("moTa", vc.getMoTa());
            return map;
        }).collect(Collectors.toList());
        
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.flush();
    }
}

