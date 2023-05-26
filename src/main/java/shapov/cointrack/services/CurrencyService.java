package shapov.cointrack.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import shapov.cointrack.models.Currency;


public interface CurrencyService {
    List<Currency> findAll() throws SQLException;

    Optional<Currency> findOneById(int id) throws SQLException;

    List<Currency> findByName(String name) throws SQLException;

    List<Currency> findByCountryId(int countryId) throws SQLException;

    int addRelationCountry(int currencyId, int countryId) throws SQLException;

    int removeRelationCountry(int currencyId, int countryId) throws SQLException;

    int create(String name) throws SQLException;

    int update(int id, String name) throws SQLException;

    int delete(int id) throws SQLException;
}
