package dao;

import model.Admin;

import java.util.Optional;

public interface AdminDAO {
    Optional<Admin> findByUsername(String username);
    boolean validatePassword(String username, String password);
}

