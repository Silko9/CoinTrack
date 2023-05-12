package shapov.cointrack.services;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAll();

    Optional<Currency> findOneById(int id);

    List<Currency> findByName(String name);

    List<Currency> findByCountryId(int countryId);

    int create(String name);

    int update(int id, String name);

    int delete(int id);
}
