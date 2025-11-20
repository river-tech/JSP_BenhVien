package dao.impl;

import dao.PhongBenhDAO;
import model.PhongBenh;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongBenhDAOImpl implements PhongBenhDAO {
    @Override
    public List<PhongBenh> findAll() {
        List<PhongBenh> list = new ArrayList<>();
        String sql = "SELECT MaVacxin, MaBenh, GhiChu FROM PHONGBENH ORDER BY MaVacxin, MaBenh";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PhongBenh(
                        rs.getString("MaVacxin"),
                        rs.getString("MaBenh"),
                        rs.getString("GhiChu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean exists(String maVacxin, String maBenh) {
        String sql = "SELECT 1 FROM PHONGBENH WHERE MaVacxin = ? AND MaBenh = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maVacxin);
            ps.setString(2, maBenh);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(PhongBenh phongBenh) {
        String sql = "INSERT INTO PHONGBENH(MaVacxin, MaBenh, GhiChu) VALUES(?,?,?) ON CONFLICT (MaVacxin, MaBenh) DO NOTHING";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phongBenh.getMaVacxin());
            ps.setString(2, phongBenh.getMaBenh());
            ps.setString(3, phongBenh.getGhiChu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // Nếu PostgreSQL không hỗ trợ ON CONFLICT, thử cách khác
            try {
                String sql2 = "INSERT INTO PHONGBENH(MaVacxin, MaBenh, GhiChu) SELECT ?,?,? WHERE NOT EXISTS (SELECT 1 FROM PHONGBENH WHERE MaVacxin = ? AND MaBenh = ?)";
                try (Connection conn = DBConnect.getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql2)) {
                    ps.setString(1, phongBenh.getMaVacxin());
                    ps.setString(2, phongBenh.getMaBenh());
                    ps.setString(3, phongBenh.getGhiChu());
                    ps.setString(4, phongBenh.getMaVacxin());
                    ps.setString(5, phongBenh.getMaBenh());
                    return ps.executeUpdate() > 0;
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
