package shapov.cointrack.repositories;

import shapov.cointrack.models.Album;
import shapov.cointrack.models.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    List<Country> findAll() throws SQLException;

    Optional<Country> findOneById(int id) throws SQLException;

    List<Country> findByName(String name) throws SQLException;

    List<Country> findByCurrencyId(int currencyId) throws SQLException;

    int create(Country country) throws SQLException;

    int edit(Country country) throws SQLException;

    int delete(int id) throws SQLException;

    int addRelationCurrency(int countryId, int currencyId) throws SQLException;

    int removeRelationCurrency(int countryId, int currencyId) throws SQLException;
}
