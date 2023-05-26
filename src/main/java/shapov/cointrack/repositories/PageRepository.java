package shapov.cointrack.repositories;

import shapov.cointrack.models.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PageRepository {
    List<Page> findAll() throws SQLException;

    Optional<Page> findOneById(int id) throws SQLException;

    List<Page> findByAlbumId(int albumId) throws SQLException;

    int create(Page page) throws SQLException;

    int edit(Page page) throws SQLException;

    int delete(int id) throws SQLException;
}
