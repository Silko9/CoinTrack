package shapov.cointrack.services;

import shapov.cointrack.models.Album;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll() throws SQLException;

    Optional<Album> findOneById(int id) throws SQLException;

    List<Album> findByTitle(String title) throws SQLException;

    int create(String title) throws SQLException;

    int update(int id, String title) throws SQLException;

    int delete(int id) throws SQLException;
}
