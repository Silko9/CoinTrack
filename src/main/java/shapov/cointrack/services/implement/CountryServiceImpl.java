package shapov.cointrack.services.implement;

import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;
import shapov.cointrack.repositories.implement.CountryRepositoryImpl;
import shapov.cointrack.services.CountryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository = new CountryRepositoryImpl("CoinTrack");
    @Override
    public List<Country> findAll() throws SQLException {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findOneById(int id) throws SQLException {
        return countryRepository.findOneById(id);
    }

    @Override
    public List<Country> findByName(String name) throws SQLException {
        return countryRepository.findByName(name);
    }

    @Override
    public List<Country> findByCurrencyId(int currencyId) throws SQLException {
        return countryRepository.findByCurrencyId(currencyId);
    }

    @Override
    public int addRelationCurrency(int countryId, int currencyId) throws SQLException {
        return countryRepository.addRelationCurrency(countryId, currencyId);
    }

    @Override
    public int removeRelationCurrency(int countryId, int currencyId) throws SQLException {
        return countryRepository.removeRelationCurrency(countryId, currencyId);
    }

    @Override
    public int create(String name) throws SQLException {
        return countryRepository.create(new Country(name));
    }

    @Override
    public int update(int id, String name) throws SQLException {
        return countryRepository.edit(new Country(id, name));
    }

    @Override
    public int delete(int id) throws SQLException {
        return countryRepository.delete(id);
    }
}
