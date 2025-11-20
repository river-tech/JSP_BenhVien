package dao.impl;

import dao.AdminDAO;
import model.Admin;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public Optional<Admin> findByUsername(String username) {
        String sql = "SELECT Username, Password, HoTen, Email FROM ADMIN WHERE Username = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Admin(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("HoTen"),
                        rs.getString("Email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean validatePassword(String username, String password) {
        String sql = "SELECT Password FROM ADMIN WHERE Username = ?";
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
}

