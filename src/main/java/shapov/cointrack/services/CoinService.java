package shapov.cointrack.services;

import shapov.cointrack.models.Coin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CoinService {
    List<Coin> findAll() throws SQLException, IOException;

    Optional<Coin> findOneById(int id) throws SQLException, IOException;

    void create(int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException, IOException;

    void update(int id, int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;

    Coin include(Coin coin) throws SQLException, IOException;
}
