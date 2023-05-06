package shapov.cointrack.services;

import shapov.cointrack.models.Mint;

import java.util.List;
import java.util.Optional;

public interface MintService {
    List<Mint> findAll();

    Optional<Mint> findOneById(int id);

    List<Mint> findByName(String name);

    List<Mint> findByCountryId(int countryId);

    int create(String name);

    int update(int id, String name);

    int delete(int id);
}

