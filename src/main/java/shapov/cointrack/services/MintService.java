package shapov.cointrack.services;

import shapov.cointrack.models.Mint;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MintService {
    List<Mint> findAll() throws SQLException;

    Optional<Mint> findOneById(int id) throws SQLException;

    List<Mint> findByName(String name) throws SQLException;

    List<Mint> findByCountryId(int countryId) throws SQLException;

    int create(String name, int countryId) throws SQLException;

    int update(int id, String name, int countryId) throws SQLException;

    int delete(int id) throws SQLException;
}

