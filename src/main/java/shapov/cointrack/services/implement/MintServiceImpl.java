package shapov.cointrack.services.implement;

import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.MintRepository;
import shapov.cointrack.repositories.implement.MintRepositoryImpl;
import shapov.cointrack.services.MintService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MintServiceImpl implements MintService {
    private final MintRepository mintRepository = new MintRepositoryImpl("CoinTrack");
    @Override
    public List<Mint> findAll() throws SQLException {
        return mintRepository.findAll();
    }

    @Override
    public Optional<Mint> findOneById(int id) throws SQLException {
        return mintRepository.findOneById(id);
    }

    @Override
    public List<Mint> findByName(String name) throws SQLException {
        return mintRepository.findByName(name);
    }

    @Override
    public List<Mint> findByCountryId(int countryId) throws SQLException {
        return mintRepository.findByCountryId(countryId);
    }

    @Override
    public int create(String name, int countryId) throws SQLException {
        return mintRepository.create(new Mint(name, countryId));
    }

    @Override
    public int update(int id, String name, int countryId) throws SQLException {
        return mintRepository.edit(new Mint(id, name, countryId));
    }

    @Override
    public int delete(int id) throws SQLException {
        return mintRepository.delete(id);
    }
}
