package shapov.cointrack.repositories;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    List<Currency> findAll();

    Optional<Currency> findOneById(int id);

    List<Currency> findByName(String name);

    List<Currency> findByCountryId(int countryId);

    int create(Currency currency);

    int update(Currency currency);

    int delete(int id);
}
