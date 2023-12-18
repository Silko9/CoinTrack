package shapov.cointrack.repositories;

import shapov.cointrack.models.HolderCell;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HolderCellRepository {
    List<HolderCell> findAll() throws SQLException, IOException;

    Optional<HolderCell> findOneById(int id) throws SQLException, IOException;

    List<HolderCell> findByPageId(int pageId) throws SQLException, IOException;

    void create(HolderCell holderCell) throws SQLException, IOException;

    void edit(HolderCell holderCell) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
