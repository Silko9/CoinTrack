package shapov.cointrack.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import shapov.cointrack.models.Currency;


public interface CurrencyService {
    List<Currency> findAll() throws SQLException, IOException;

    Optional<Currency> findOneById(int id) throws SQLException, IOException;

    void create(String name) throws SQLException, IOException;

    void update(int id, String name) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
