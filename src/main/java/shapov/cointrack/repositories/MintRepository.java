package shapov.cointrack.repositories;

import shapov.cointrack.models.Mint;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MintRepository {
    List<Mint> findAll() throws SQLException;

    Optional<Mint> findOneById(int id) throws SQLException;

    List<Mint> findByName(String name) throws SQLException;

    List<Mint> findByCountryId(int countryId) throws SQLException;

    int create(Mint mint) throws SQLException;

    int edit(Mint mint) throws SQLException;

    int delete(int id) throws SQLException;
}