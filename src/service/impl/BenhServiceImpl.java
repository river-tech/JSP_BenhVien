package service.impl;

import dao.BenhDAO;
import dao.impl.BenhDAOImpl;
import model.Benh;
import service.BenhService;

import java.util.List;
import java.util.Optional;

public class BenhServiceImpl implements BenhService {

    private final BenhDAO benhDAO = new BenhDAOImpl();

    @Override
    public List<Benh> getAll() {
        return benhDAO.findAll();
    }

    @Override
    public Optional<Benh> getById(String maBenh) {
        return benhDAO.findById(maBenh);
    }
}
