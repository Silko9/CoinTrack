package shapov.cointrack.repositories;

import shapov.cointrack.models.Album;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository {
    List<Album> findAll() throws SQLException;

    Optional<Album> findOneById(int id) throws SQLException;

    List<Album> findByTitle(String title) throws SQLException;

    int create(Album album) throws SQLException;

    int edit(Album album) throws SQLException;

    int delete(int id) throws SQLException;
}
