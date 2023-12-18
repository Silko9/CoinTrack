package shapov.cointrack.services;

import shapov.cointrack.models.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PageService {
    List<Page> findAll() throws SQLException, IOException;

    Optional<Page> findOneById(int id) throws SQLException, IOException;

    List<Page> findByAlbumId(int albumId) throws SQLException, IOException;

    void create(int albumId, int previousPageId, int nextPageId, String title) throws SQLException, IOException;

    void update(int id, int albumId, int previousPageId, int nextPageId, String title) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
