package shapov.cointrack.repositories;

import shapov.cointrack.models.HolderCell;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HolderCellRepository {
    List<HolderCell> findAll() throws SQLException;

    Optional<HolderCell> findOneById(int id) throws SQLException;

    List<HolderCell> findByPageId(int pageId) throws SQLException;

    int create(HolderCell holderCell) throws SQLException;

    int edit(HolderCell holderCell) throws SQLException;

    int delete(int id) throws SQLException;
}
