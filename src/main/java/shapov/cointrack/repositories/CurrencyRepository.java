package shapov.cointrack.repositories;

import shapov.cointrack.models.Currency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    List<Currency> findAll() throws SQLException, IOException;

    Optional<Currency> findOneById(int id) throws SQLException, IOException;

    void create(Currency currency) throws SQLException, IOException;

    void edit(Currency currency) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
