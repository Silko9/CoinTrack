package shapov.cointrack.services;

import shapov.cointrack.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findOneById(int id);

    List<Country> findByName(String name);

    int create(String name);

    int update(int id, String name);

    int delete(int id);
}
