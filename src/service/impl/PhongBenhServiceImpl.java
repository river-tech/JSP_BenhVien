package service.impl;

import dao.PhongBenhDAO;
import dao.impl.PhongBenhDAOImpl;
import model.PhongBenh;
import service.PhongBenhService;

import java.util.List;

public class PhongBenhServiceImpl implements PhongBenhService {

    private final PhongBenhDAO phongBenhDAO = new PhongBenhDAOImpl();

    @Override
    public List<PhongBenh> getAll() {
        return phongBenhDAO.findAll();
    }
}
