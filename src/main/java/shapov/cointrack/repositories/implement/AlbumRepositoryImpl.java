package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.AppConfig;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Album;
import shapov.cointrack.repositories.AlbumRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AlbumRepositoryImpl implements AlbumRepository {

    public AlbumRepositoryImpl() {}

    private static final String nameTable = "albums";

    @Override
    public List<Album> findAll() throws IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll(nameTable), new TypeReference<>(){});
    }


    @Override
    public void create(Album album) throws SQLException, IOException {
        DatabaseHelper.create(nameTable, album);
    }

    @Override
    public void edit(Album album) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, album, album.getId());
    }

    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
