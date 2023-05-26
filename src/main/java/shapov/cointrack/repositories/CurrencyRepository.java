package shapov.cointrack.repositories;

import shapov.cointrack.models.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    List<Currency> findAll() throws SQLException;

    Optional<Currency> findOneById(int id) throws SQLException;

    List<Currency> findByName(String name) throws SQLException;

    List<Currency> findByCountryId(int countryId) throws SQLException;

    int addRelationCountry(int currencyId, int countryId) throws SQLException;

    int removeRelationCountry(int currencyId, int countryId) throws SQLException;

    int create(Currency currency) throws SQLException;

    int edit(Currency currency) throws SQLException;

    int delete(int id) throws SQLException;
}
