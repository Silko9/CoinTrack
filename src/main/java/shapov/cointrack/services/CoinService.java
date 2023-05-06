package shapov.cointrack.services;

import shapov.cointrack.models.Coin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CoinService {
    public Collection<Coin> findAll();
    public Optional<Coin> findOneById(int id);
    public List<Coin> findByDenomination(int denomination);
    public List<Coin> findByCurrencyId(int currencyId);
    public List<Coin> findByCountryId(int countryId);
    public List<Coin> findByMintId(int mintId);
    public List<Coin> findByDateMinting(LocalDate dateMinting);
    public void create(int denominationId, int currencyId, int countryId, int mintId, LocalDate dateMinting);
    public void update(int id, int denominationId, int currencyId, int countryId, int mintId, LocalDate dateMinting);
    public void delete(int id);
}
