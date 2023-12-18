package shapov.cointrack.services;

import shapov.cointrack.models.HolderCell;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HolderCellService {
    List<HolderCell> findAll() throws SQLException, IOException;

    Optional<HolderCell> findOneById(int id) throws SQLException, IOException;

    List<HolderCell> findByPageId(int pageId) throws SQLException, IOException;

    void create(int coinId, int pageId, int column, int line, String title) throws SQLException, IOException;

    void update(int id, boolean available, int coinId, int pageId, int column, int line, String title) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;

    HolderCell include(HolderCell holderCell) throws SQLException, IOException;
}
