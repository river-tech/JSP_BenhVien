package dao.impl;

import dao.LSTiemPhongDAO;
import model.LichSuTiemPhong;
import model.dto.LichSuTiemPhongView;
import util.DBConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LSTiemPhongDAOImpl implements LSTiemPhongDAO {

    @Override
    public List<LichSuTiemPhong> findAll() {
        List<LichSuTiemPhong> list = new ArrayList<>();
        String sql = "SELECT MaKH, MaVacxin, STTMui, NgayTiemPhong, NgayHenTiepTheo FROM LICHSUTIEMPHONG";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date ngayTiem = rs.getDate("NgayTiemPhong");
                Date ngayHen = rs.getDate("NgayHenTiepTheo");
                list.add(new LichSuTiemPhong(
                        rs.getString("MaKH"),
                        rs.getString("MaVacxin"),
                        rs.getInt("STTMui"),
                        ngayTiem != null ? ngayTiem.toLocalDate() : null,
                        ngayHen != null ? ngayHen.toLocalDate() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(LichSuTiemPhong record) {
        String sql = "INSERT INTO LICHSUTIEMPHONG(MaKH, MaVacxin, STTMui, NgayTiemPhong, NgayHenTiepTheo) VALUES(?,?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, record.getMaKH());
            ps.setString(2, record.getMaVacxin());
            ps.setInt(3, record.getSttMui());
            if (record.getNgayTiemPhong() != null) {
                ps.setDate(4, Date.valueOf(record.getNgayTiemPhong()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            if (record.getNgayHenTiepTheo() != null) {
                ps.setDate(5, Date.valueOf(record.getNgayHenTiepTheo()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LichSuTiemPhong> findByMaKH(String maKH) {
        List<LichSuTiemPhong> list = new ArrayList<>();
        String sql = "SELECT MaKH, MaVacxin, STTMui, NgayTiemPhong, NgayHenTiepTheo FROM LICHSUTIEMPHONG WHERE MaKH = ? ORDER BY MaVacxin, STTMui";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date ngayTiem = rs.getDate("NgayTiemPhong");
                Date ngayHen = rs.getDate("NgayHenTiepTheo");
                list.add(new LichSuTiemPhong(
                        rs.getString("MaKH"),
                        rs.getString("MaVacxin"),
                        rs.getInt("STTMui"),
                        ngayTiem != null ? ngayTiem.toLocalDate() : null,
                        ngayHen != null ? ngayHen.toLocalDate() : null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getVaccineUsedForDisease(String maKH, String maBenh) {
        // Kiểm tra xem khách hàng đã tiêm vaccine nào cho bệnh này chưa
        String sql = "SELECT DISTINCT ls.MaVacxin " +
                "FROM LICHSUTIEMPHONG ls " +
                "JOIN PHONGBENH pb ON ls.MaVacxin = pb.MaVacxin " +
                "WHERE ls.MaKH = ? AND pb.MaBenh = ? " +
                "LIMIT 1";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            ps.setString(2, maBenh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MaVacxin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LichSuTiemPhongView> findHistoryView() {
        List<LichSuTiemPhongView> list = new ArrayList<>();
        // Dùng LEFT JOIN để hiển thị cả khi chưa có PHONGBENH
        // Nếu có nhiều bệnh cho 1 vaccine, sẽ hiển thị nhiều dòng
        String sql = "SELECT DISTINCT kh.MaKH, kh.HoTenKH, COALESCE(b.TenBenh, 'Chưa xác định') AS TenBenh, " +
                "vc.MaVacxin, vc.TenVacxin, vc.SoMui " +
                "FROM KHACHHANG kh " +
                "JOIN LICHSUTIEMPHONG ls ON kh.MaKH = ls.MaKH " +
                "JOIN VACXIN vc ON ls.MaVacxin = vc.MaVacxin " +
                "LEFT JOIN PHONGBENH pb ON vc.MaVacxin = pb.MaVacxin " +
                "LEFT JOIN BENH b ON pb.MaBenh = b.MaBenh " +
                "ORDER BY kh.MaKH, vc.MaVacxin";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LichSuTiemPhongView(
                        rs.getString("MaKH"),
                        rs.getString("HoTenKH"),
                        rs.getString("TenBenh"),
                        rs.getString("MaVacxin"),
                        rs.getString("TenVacxin"),
                        rs.getInt("SoMui")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi query lịch sử tiêm phòng: " + e.getMessage());
        }
        return list;
    }
}
