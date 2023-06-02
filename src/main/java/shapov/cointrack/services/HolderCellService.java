package shapov.cointrack.services;

import shapov.cointrack.models.Coin;
import shapov.cointrack.models.HolderCell;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HolderCellService {
    List<HolderCell> findAll() throws SQLException;

    Optional<HolderCell> findOneById(int id) throws SQLException;

    List<HolderCell> findByPageId(int pageId) throws SQLException;

    int create(int coinId, int pageId, int column, int line, String title) throws SQLException;

    int update(int id, boolean available, int coinId, int pageId, int column, int line, String title) throws SQLException;

    int delete(int id) throws SQLException;

    HolderCell include(HolderCell holderCell) throws SQLException;
}
