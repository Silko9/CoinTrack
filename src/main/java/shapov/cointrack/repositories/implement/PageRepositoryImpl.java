package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Mint;
import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.PageRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class PageRepositoryImpl extends DatabaseHelper implements PageRepository {
    private final static String TABLE_NAME = "Page";
    private String nameDB = "CoinTrackTest";
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }
    @Override
    public List<Page> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    @Override
    public Optional<Page> findOneById(int id) throws SQLException {
        List<Page> pages = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (pages.size() > 0)
            return Optional.of(pages.get(0));
        else
            return Optional.empty();    }

    @Override
    public List<Page> findByAlbumId(int albumId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(albumId))));
    }

    @Override
    public int create(Page page) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(page)));
    }

    @Override
    public int edit(Page page) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(page), page.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    private List<Page> mapper(ResultSet resultSet) throws SQLException {
        List<Page> pages = new ArrayList<>();
        Page page;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int albumId = resultSet.getInt("albumId");
            int number = resultSet.getInt("number");
            String title = resultSet.getString("title");
            page = new Page(id, albumId, number, title);
            pages.add(page);
        }
        connectionClose();
        return pages;
    }

    private String getParameters(Page page){
        return "albumId=" + page.getAlbumId() +
                ", number=" + page.getNumber() +
                ", title='" + page.getTitle() + "' ";
    }

    private String getParameters(int albumId){
        return "albumId=" + albumId;
    }
}
