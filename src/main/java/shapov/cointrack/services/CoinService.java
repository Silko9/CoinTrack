package shapov.cointrack.services;

import shapov.cointrack.models.Coin;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CoinService {
    List<Coin> findAll() throws SQLException;

    Optional<Coin> findOneById(int id) throws SQLException;

    List<Coin> findByDenomination(int denomination) throws SQLException;

    List<Coin> findByCurrencyId(int currencyId) throws SQLException;

    List<Coin> findByCountryId(int countryId) throws SQLException;

    List<Coin> findByMintId(int mintId) throws SQLException;

    List<Coin> findByDateMinting(int yearMinting) throws SQLException;

    int create(int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException;

    int update(int id, int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException;

    int delete(int id) throws SQLException;
}
