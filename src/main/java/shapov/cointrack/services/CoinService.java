package shapov.cointrack.services;

import shapov.cointrack.models.Coin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CoinService {
    List<Coin> findAll();

    Optional<Coin> findOneById(int id);

    List<Coin> findByDenomination(int denomination);

    List<Coin> findByCurrencyId(int currencyId);

    List<Coin> findByCountryId(int countryId);

    List<Coin> findByMintId(int mintId);

    List<Coin> findByDateMinting(LocalDate dateMinting);

    int create(int denominationId, int currencyId, int countryId, int mintId, LocalDate dateMinting, String picturePath);

    int update(int id, int denominationId, int currencyId, int countryId, int mintId, LocalDate dateMinting, String picturePath);

    int delete(int id);
}
