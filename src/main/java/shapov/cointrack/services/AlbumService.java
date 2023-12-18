package shapov.cointrack.services;

import shapov.cointrack.models.Album;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AlbumService {

    List<Album> findAll() throws SQLException, IOException;

    void create(String title) throws SQLException, IOException;

    void update(int id, String title) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
 }