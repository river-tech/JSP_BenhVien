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
}
