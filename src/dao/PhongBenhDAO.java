package dao;

import model.PhongBenh;

import java.util.List;

public interface PhongBenhDAO {
    List<PhongBenh> findAll();
    
    boolean exists(String maVacxin, String maBenh);
    
    boolean insert(PhongBenh phongBenh);
}
