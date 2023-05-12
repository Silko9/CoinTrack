package shapov.cointrack.repositories;

import shapov.cointrack.models.Mint;

import java.util.List;
import java.util.Optional;

public interface MintRepository {
    List<Mint> findAll();

    Optional<Mint> findOneById(int id);

    List<Mint> findByName(String name);

    List<Mint> findByCountryId(int countryId);

    int create(Mint mint);

    int update(Mint mint);

    int delete(int id);
}