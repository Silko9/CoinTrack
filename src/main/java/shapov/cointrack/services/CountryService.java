package shapov.cointrack.services;

import shapov.cointrack.models.Country;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll() throws SQLException;

    Optional<Country> findOneById(int id) throws SQLException;

    List<Country> findByName(String name) throws SQLException;

    List<Country> findByCurrencyId(int currencyId) throws SQLException;

    int addRelationCurrency(int countryId, int currencyId) throws SQLException;

    int removeRelationCurrency(int countryId, int currencyId) throws SQLException;

    int create(String name) throws SQLException;

    int update(int id, String name) throws SQLException;

    int delete(int id) throws SQLException;
}
