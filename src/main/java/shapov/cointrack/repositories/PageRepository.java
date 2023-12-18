package shapov.cointrack.repositories;

import shapov.cointrack.models.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PageRepository {
    List<Page> findAll() throws SQLException, IOException;

    Optional<Page> findOneById(int id) throws SQLException, IOException;

    List<Page> findByAlbumId(int albumId) throws SQLException, IOException;

    void create(Page page) throws SQLException, IOException;

    void edit(Page page) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}
