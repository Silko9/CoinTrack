package shapov.cointrack.repositories;

import shapov.cointrack.models.Coin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CoinRepository {
    List<Coin> findAll() throws SQLException, IOException;
    Optional<Coin> findOneById(int id) throws SQLException, IOException;

    void create(Coin coin) throws SQLException, IOException;

    void edit(Coin coin) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
