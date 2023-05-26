package shapov.cointrack.services.implement;

import shapov.cointrack.repositories.CurrencyRepository;
import shapov.cointrack.repositories.implement.CurrencyRepositoryImpl;
import shapov.cointrack.services.CurrencyService;
import shapov.cointrack.models.Currency;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl("CoinTrack");
    @Override
    public List<Currency> findAll() throws SQLException {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> findOneById(int id) throws SQLException {
        return currencyRepository.findOneById(id);
    }

    @Override
    public List<Currency> findByName(String name) throws SQLException {
        return currencyRepository.findByName(name);
    }

    @Override
    public List<Currency> findByCountryId(int countryId) throws SQLException {
        return currencyRepository.findByCountryId(countryId);
    }

    @Override
    public int addRelationCountry(int currencyId, int countryId) throws SQLException {
        return currencyRepository.addRelationCountry(currencyId, countryId);
    }

    @Override
    public int removeRelationCountry(int currencyId, int countryId) throws SQLException {
        return currencyRepository.removeRelationCountry(currencyId, countryId);
    }

    @Override
    public int create(String name) throws SQLException {
        return currencyRepository.create(new Currency(name));
    }

    @Override
    public int update(int id, String name) throws SQLException {
        return currencyRepository.edit(new Currency(id, name));
    }

    @Override
    public int delete(int id) throws SQLException {
        return currencyRepository.delete(id);
    }
}
