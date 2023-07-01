package shapov.cointrack.services;

import shapov.cointrack.models.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PageService {
    List<Page> findAll() throws SQLException;

    Optional<Page> findOneById(int id) throws SQLException;

    List<Page> findByAlbumId(int albumId) throws SQLException;

    int create(int albumId, int previousPageId, int nextPageId, String title) throws SQLException;

    int update(int id, int albumId, int previousPageId, int nextPageId, String title) throws SQLException;

    int delete(int id) throws SQLException;
}
