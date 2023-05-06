package shapov.cointrack.repositories;

import shapov.cointrack.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    List<Country> findAll();

    Optional<Country> findOneById(int id);

    List<Country> findByName(String name);

    int create(Country country);

    int update(Country country);

    int delete(int id);
}
