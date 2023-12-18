package shapov.cointrack.repositories;

import shapov.cointrack.models.Album;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AlbumRepository {
    List<Album> findAll() throws SQLException, IOException;

    void create(Album album) throws SQLException, IOException;

    void edit(Album album) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}