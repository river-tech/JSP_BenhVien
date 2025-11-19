package dao.impl;

import dao.BenhDAO;
import model.Benh;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BenhDAOImpl implements BenhDAO {

    @Override
    public List<Benh> findAll() {
        List<Benh> list = new ArrayList<>();
        String sql = "SELECT MaBenh, TenBenh, MoTa FROM BENH ORDER BY MaBenh";
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
    public Optional<Benh> findById(String maBenh) {
        String sql = "SELECT MaBenh, TenBenh, MoTa FROM BENH WHERE MaBenh=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBenh);
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
    public boolean insert(Benh benh) {
        String sql = "INSERT INTO BENH(MaBenh, TenBenh, MoTa) VALUES(?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, benh.getMaBenh());
            ps.setString(2, benh.getTenBenh());
            ps.setString(3, benh.getMoTa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Benh benh) {
        String sql = "UPDATE BENH SET TenBenh=?, MoTa=? WHERE MaBenh=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, benh.getTenBenh());
            ps.setString(2, benh.getMoTa());
            ps.setString(3, benh.getMaBenh());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String maBenh) {
        String sql = "DELETE FROM BENH WHERE MaBenh=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBenh);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Benh mapRow(ResultSet rs) throws SQLException {
        return new Benh(
                rs.getString("MaBenh"),
                rs.getString("TenBenh"),
                rs.getString("MoTa")
        );
    }
}
