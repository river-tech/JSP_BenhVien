package dao.impl;

import dao.VaccineDAO;
import model.Vaccine;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VaccineDAOImpl implements VaccineDAO {

    private static final String BASE_SELECT = "SELECT MaVacxin, TenVacxin, SoMui, MoTa, GiaVacxin, TenHangSX FROM VACXIN";

    @Override
    public List<Vaccine> findAll() {
        List<Vaccine> vaccines = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(BASE_SELECT + " ORDER BY MaVacxin")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vaccines.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccines;
    }

    @Override
    public Optional<Vaccine> findById(String maVacxin) {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(BASE_SELECT + " WHERE MaVacxin = ?")) {
            ps.setString(1, maVacxin);
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
    public boolean insert(Vaccine vaccine) {
        // Insert không có ThoiGianCho vì cột này không có trong database
        String sql = "INSERT INTO VACXIN(MaVacxin, TenVacxin, SoMui, MoTa, GiaVacxin, TenHangSX) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vaccine.getMaVacxin());
            ps.setString(2, vaccine.getTenVacxin());
            ps.setInt(3, vaccine.getSoMui());
            ps.setString(4, vaccine.getMoTa());
            ps.setInt(5, vaccine.getGiaVacxin());
            ps.setString(6, vaccine.getTenHangSX());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi SQL khi insert vaccine: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
        }
        return false;
    }

    @Override
    public boolean update(Vaccine vaccine) {
        // Update không có ThoiGianCho vì cột này không có trong database
        String sql = "UPDATE VACXIN SET TenVacxin=?, SoMui=?, MoTa=?, GiaVacxin=?, TenHangSX=? WHERE MaVacxin=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vaccine.getTenVacxin());
            ps.setInt(2, vaccine.getSoMui());
            ps.setString(3, vaccine.getMoTa());
            ps.setInt(4, vaccine.getGiaVacxin());
            ps.setString(5, vaccine.getTenHangSX());
            ps.setString(6, vaccine.getMaVacxin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maVacxin) {
        String sql = "DELETE FROM VACXIN WHERE MaVacxin=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maVacxin);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Vaccine> search(String maVacxin, String tenVacxin, String tenHangSX) {
        List<Vaccine> vaccines = new ArrayList<>();
        StringBuilder sql = new StringBuilder(BASE_SELECT + " WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (maVacxin != null && !maVacxin.isEmpty()) {
            sql.append(" AND LOWER(MaVacxin) LIKE ?");
            params.add("%" + maVacxin.toLowerCase() + "%");
        }
        if (tenVacxin != null && !tenVacxin.isEmpty()) {
            sql.append(" AND LOWER(TenVacxin) LIKE ?");
            params.add("%" + tenVacxin.toLowerCase() + "%");
        }
        if (tenHangSX != null && !tenHangSX.isEmpty()) {
            sql.append(" AND LOWER(TenHangSX) LIKE ?");
            params.add("%" + tenHangSX.toLowerCase() + "%");
        }
        sql.append(" ORDER BY MaVacxin");

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vaccines.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccines;
    }

    @Override
    public List<Vaccine> findByMaBenh(String maBenh) {
        List<Vaccine> vaccines = new ArrayList<>();
        String sql = "SELECT DISTINCT vc.MaVacxin, vc.TenVacxin, vc.SoMui, vc.MoTa, vc.GiaVacxin, vc.TenHangSX " +
                "FROM VACXIN vc " +
                "JOIN PHONGBENH pb ON vc.MaVacxin = pb.MaVacxin " +
                "WHERE pb.MaBenh = ? " +
                "ORDER BY vc.MaVacxin";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBenh);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vaccines.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccines;
    }

    private Vaccine mapRow(ResultSet rs) throws SQLException {
        return new Vaccine(
                rs.getString("MaVacxin"),
                rs.getString("TenVacxin"),
                rs.getInt("SoMui"),
                rs.getString("MoTa"),
                rs.getInt("GiaVacxin"),
                rs.getString("TenHangSX")
        );
    }

    // Method này không còn được sử dụng, đã thay bằng code trực tiếp trong insert()
    @Deprecated
    private void fillStatement(Vaccine vaccine, PreparedStatement ps) throws SQLException {
        // Không sử dụng nữa
    }
}
