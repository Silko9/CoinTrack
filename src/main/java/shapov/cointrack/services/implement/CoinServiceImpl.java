package shapov.cointrack.services.implement;

import shapov.cointrack.models.Coin;
import shapov.cointrack.repositories.CoinRepository;
import shapov.cointrack.repositories.implement.CoinRepositoryImpl;
import shapov.cointrack.services.CoinService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CoinServiceImpl implements CoinService {
    private final CoinRepository coinRepository = new CoinRepositoryImpl("CoinTrack");
    @Override
    public List<Coin> findAll() throws SQLException {
        return coinRepository.findAll();
    }

    @Override
    public Optional<Coin> findOneById(int id) throws SQLException {
        return coinRepository.findOneById(id);
    }

    @Override
    public List<Coin> findByDenomination(int denomination) throws SQLException {
        return coinRepository.findByDenomination(denomination);
    }

    @Override
    public List<Coin> findByCurrencyId(int currencyId) throws SQLException {
        return coinRepository.findByCurrencyId(currencyId);
    }

    @Override
    public List<Coin> findByCountryId(int countryId) throws SQLException {
        return coinRepository.findByCountryId(countryId);
    }

    @Override
    public List<Coin> findByMintId(int mintId) throws SQLException {
        return coinRepository.findByMintId(mintId);
    }

    @Override
    public List<Coin> findByDateMinting(int yearMinting) throws SQLException {
        return coinRepository.findByDateMinting(yearMinting);
    }

    @Override
    public int create(int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException {
        return coinRepository.create(new Coin(denominationId, currencyId, countryId, mintId, yearMinting, picturePath));
    }

    @Override
    public int update(int id, int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException {
        return coinRepository.edit(new Coin(id, denominationId, currencyId, countryId, mintId, yearMinting, picturePath));
    }

    @Override
    public int delete(int id) throws SQLException {
        return coinRepository.delete(id);
    }
}
