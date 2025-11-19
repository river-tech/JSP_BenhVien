package service.impl;

import dao.VaccineDAO;
import dao.impl.VaccineDAOImpl;
import model.Vaccine;
import service.VaccineService;

import java.util.List;
import java.util.Optional;

public class VaccineServiceImpl implements VaccineService {

    private final VaccineDAO vaccineDAO = new VaccineDAOImpl();

    @Override
    public List<Vaccine> getAll() {
        return vaccineDAO.findAll();
    }

    @Override
    public Optional<Vaccine> getById(String maVacxin) {
        return vaccineDAO.findById(maVacxin);
    }

    @Override
    public boolean create(Vaccine vaccine) {
        return vaccineDAO.insert(vaccine);
    }

    @Override
    public boolean update(Vaccine vaccine) {
        return vaccineDAO.update(vaccine);
    }

    @Override
    public boolean delete(String maVacxin) {
        return vaccineDAO.delete(maVacxin);
    }

    @Override
    public List<Vaccine> search(String maVacxin, String tenVacxin, String tenHangSX) {
        return vaccineDAO.search(maVacxin, tenVacxin, tenHangSX);
    }
}
