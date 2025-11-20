package dao.impl;

import dao.KhachHangDAO;
import model.KhachHang;
import model.dto.ThongKeChiPhiView;
import util.DBConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KhachHangDAOImpl implements KhachHangDAO {

    @Override
    public List<KhachHang> findAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT MaKH, HoTenKH, SoDienThoai, DiaChiKH, NgaySinh, GioiTinh FROM KHACHHANG ORDER BY MaKH";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<KhachHang> findById(String maKH) {
        String sql = "SELECT MaKH, HoTenKH, SoDienThoai, DiaChiKH, NgaySinh, GioiTinh FROM KHACHHANG WHERE MaKH=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean insert(KhachHang khachHang) {
        // Kiểm tra xem có cột Username/Password không, nếu không thì chỉ insert các trường cơ bản
        String sql = "INSERT INTO KHACHHANG(MaKH, HoTenKH, SoDienThoai, DiaChiKH, NgaySinh, GioiTinh) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khachHang.getMaKH());
            ps.setString(2, khachHang.getHoTenKH());
            ps.setString(3, khachHang.getSoDienThoai());
            ps.setString(4, khachHang.getDiaChiKH());
            if (khachHang.getNgaySinh() != null) {
                ps.setDate(5, Date.valueOf(khachHang.getNgaySinh()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            ps.setString(6, khachHang.getGioiTinh());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Log lỗi chi tiết để debug
            System.err.println("Lỗi khi insert khách hàng: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
        }
        return false;
    }

    @Override
    public boolean update(KhachHang khachHang) {
        String sql = "UPDATE KHACHHANG SET HoTenKH=?, SoDienThoai=?, DiaChiKH=?, NgaySinh=?, GioiTinh=? WHERE MaKH=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, khachHang.getHoTenKH());
            ps.setString(2, khachHang.getSoDienThoai());
            ps.setString(3, khachHang.getDiaChiKH());
            if (khachHang.getNgaySinh() != null) {
                ps.setDate(4, Date.valueOf(khachHang.getNgaySinh()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setString(5, khachHang.getGioiTinh());
            ps.setString(6, khachHang.getMaKH());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maKH) {
        String sql = "DELETE FROM KHACHHANG WHERE MaKH=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<KhachHang> findByUsername(String username) {
        String sql = "SELECT MaKH, HoTenKH, SoDienThoai, DiaChiKH, NgaySinh, GioiTinh, Username, Password FROM KHACHHANG WHERE Username=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean validatePassword(String username, String password) {
        String sql = "SELECT Password FROM KHACHHANG WHERE Username = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                return storedPassword != null && storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ThongKeChiPhiView> getThongKeChiPhi() {
        List<ThongKeChiPhiView> result = new ArrayList<>();
        String sql = "SELECT kh.MaKH, kh.HoTenKH, kh.DiaChiKH, COALESCE(SUM(vc.GiaVacxin),0) AS TongTien " +
                "FROM KHACHHANG kh " +
                "LEFT JOIN LICHSUTIEMPHONG ls ON kh.MaKH = ls.MaKH " +
                "LEFT JOIN VACXIN vc ON ls.MaVacxin = vc.MaVacxin " +
                "GROUP BY kh.MaKH, kh.HoTenKH, kh.DiaChiKH " +
                "ORDER BY TongTien ASC";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new ThongKeChiPhiView(
                        rs.getString("MaKH"),
                        rs.getString("HoTenKH"),
                        rs.getString("DiaChiKH"),
                        rs.getLong("TongTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private KhachHang mapRow(ResultSet rs) throws SQLException {
        Date date = rs.getDate("NgaySinh");
        LocalDate ngaySinh = date != null ? date.toLocalDate() : null;
        return new KhachHang(
                rs.getString("MaKH"),
                rs.getString("HoTenKH"),
                rs.getString("SoDienThoai"),
                rs.getString("DiaChiKH"),
                ngaySinh,
                rs.getString("GioiTinh")
        );
    }

    // Method này không còn được sử dụng, đã thay bằng code trực tiếp trong insert()
    @Deprecated
    private void fillStatement(KhachHang khachHang, PreparedStatement ps) throws SQLException {
        // Không sử dụng nữa
    }
}
