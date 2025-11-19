package service;

import model.Vaccine;

import java.util.List;
import java.util.Optional;

public interface VaccineService {
    List<Vaccine> getAll();

    Optional<Vaccine> getById(String maVacxin);

    boolean create(Vaccine vaccine);

    boolean update(Vaccine vaccine);

    boolean delete(String maVacxin);

    List<Vaccine> search(String maVacxin, String tenVacxin, String tenHangSX);
}
