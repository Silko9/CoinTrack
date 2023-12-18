package shapov.cointrack.services;

import shapov.cointrack.models.Country;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll() throws SQLException, IOException;

    Optional<Country> findOneById(int id) throws SQLException, IOException;

    void create(String name) throws SQLException, IOException;

    void update(int id, String name) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
