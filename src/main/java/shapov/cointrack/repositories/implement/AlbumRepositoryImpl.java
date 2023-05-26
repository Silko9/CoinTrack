package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Album;
import shapov.cointrack.repositories.AlbumRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class AlbumRepositoryImpl extends DatabaseHelper implements AlbumRepository {
    private final static String TABLE_NAME = "Album";
    private String nameDB = "CoinTrackTest";
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }
    @Override
    public List<Album> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    @Override
    public Optional<Album> findOneById(int id) throws SQLException {
        List<Album> albums = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (albums.size() > 0)
            return Optional.of(albums.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Album> findByTitle(String title) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(title))));
    }

    @Override
    public int create(Album album) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(album)));
    }

    @Override
    public int edit(Album album) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(album), album.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    private List<Album> mapper(ResultSet resultSet) throws SQLException {
        List<Album> albums = new ArrayList<>();
        Album album;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            album = new Album(id, title);
            albums.add(album);
        }
        connectionClose();
        return albums;
    }

    private String getParameters(Album album){
        return "title='" + album.getTitle() + "' ";
    }
    private String getParameters(String title){
        return "title='" + title + "' ";
    }
}
