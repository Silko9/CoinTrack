package shapov.cointrack.repositories;

import shapov.cointrack.models.Coin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CoinRepository {
    List<Coin> findAll();
    Optional<Coin> findOneById(int id);
    List<Coin> findByDenomination(int denomination);

    List<Coin> findByCurrencyId(int currencyId);

    List<Coin> findByCountryId(int countryId);

    List<Coin> findByMintId(int mintId);

    List<Coin> findByDateMinting(LocalDate dateMinting);

    int create(Coin coin);

    int update(Coin coin);

    int delete(int id);
}
