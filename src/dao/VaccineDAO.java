package dao;

import model.Vaccine;

import java.util.List;
import java.util.Optional;

public interface VaccineDAO {
    List<Vaccine> findAll();

    Optional<Vaccine> findById(String maVacxin);

    boolean insert(Vaccine vaccine);

    boolean update(Vaccine vaccine);

    boolean delete(String maVacxin);

    List<Vaccine> search(String maVacxin, String tenVacxin, String tenHangSX);
}
