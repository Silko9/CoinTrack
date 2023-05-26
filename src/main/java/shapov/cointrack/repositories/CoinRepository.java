package shapov.cointrack.repositories;

import shapov.cointrack.models.Album;
import shapov.cointrack.models.Coin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CoinRepository {
    List<Coin> findAll() throws SQLException;
    Optional<Coin> findOneById(int id) throws SQLException;
    List<Coin> findByDenomination(int denomination) throws SQLException;

    List<Coin> findByCurrencyId(int currencyId) throws SQLException;

    List<Coin> findByCountryId(int countryId) throws SQLException;

    List<Coin> findByMintId(int mintId) throws SQLException;

    List<Coin> findByDateMinting(int yearMinting) throws SQLException;

    int create(Coin coin) throws SQLException;

    int edit(Coin coin) throws SQLException;

    int delete(int id) throws SQLException;
}
