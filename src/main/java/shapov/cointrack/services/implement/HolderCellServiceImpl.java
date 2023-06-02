package shapov.cointrack.services.implement;

import shapov.cointrack.models.Coin;
import shapov.cointrack.models.Country;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.CoinRepository;
import shapov.cointrack.repositories.HolderCellRepository;
import shapov.cointrack.repositories.implement.CoinRepositoryImpl;
import shapov.cointrack.repositories.implement.HolderCellRepositoryImpl;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.HolderCellService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HolderCellServiceImpl implements HolderCellService {
    private final HolderCellRepository holderCellRepository = new HolderCellRepositoryImpl("CoinTrack");
    private final CoinRepository coinRepository = new CoinRepositoryImpl("CoinTrack");
    @Override
    public List<HolderCell> findAll() throws SQLException {
        return holderCellRepository.findAll();
    }

    @Override
    public Optional<HolderCell> findOneById(int id) throws SQLException {
        return holderCellRepository.findOneById(id);
    }

    @Override
    public List<HolderCell> findByPageId(int pageId) throws SQLException {
        return holderCellRepository.findByPageId(pageId);
    }

    @Override
    public int create(int coinId, int pageId, int column, int line, String title) throws SQLException {
        return holderCellRepository.create(new HolderCell(coinId, false, pageId, column, line, title));
    }

    @Override
    public int update(int id, boolean available, int coinId, int pageId, int column, int line, String title) throws SQLException {
        return holderCellRepository.edit(new HolderCell(id, coinId, available, pageId, column, line, title));
    }

    @Override
    public int delete(int id) throws SQLException {
        return holderCellRepository.delete(id);
    }

    @Override
    public HolderCell include(HolderCell holderCell) throws SQLException {
        Optional<Coin> coin = coinRepository.findOneById(holderCell.getCoinId());

        if(coin.isEmpty()) return null;

        holderCell.setCoin(coin.get());

        return holderCell;
    }
}
