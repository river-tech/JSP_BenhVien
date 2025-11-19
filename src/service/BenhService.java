package service;

import model.Benh;

import java.util.List;
import java.util.Optional;

public interface BenhService {
    List<Benh> getAll();

    Optional<Benh> getById(String maBenh);
}
