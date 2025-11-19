package dao;

import model.Benh;

import java.util.List;
import java.util.Optional;

public interface BenhDAO {
    List<Benh> findAll();

    Optional<Benh> findById(String maBenh);

    boolean insert(Benh benh);

    boolean update(Benh benh);

    boolean delete(String maBenh);
}
