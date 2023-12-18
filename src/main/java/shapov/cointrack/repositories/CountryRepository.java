package shapov.cointrack.repositories;

import shapov.cointrack.models.Country;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    List<Country> findAll() throws SQLException, IOException;

    Optional<Country> findOneById(int id) throws SQLException, IOException;

    void create(Country country) throws SQLException, IOException;

    void edit(Country country) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
