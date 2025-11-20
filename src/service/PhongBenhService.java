package service;

import model.PhongBenh;

import java.util.List;

public interface PhongBenhService {
    List<PhongBenh> getAll();
    
    boolean create(PhongBenh phongBenh);
}
