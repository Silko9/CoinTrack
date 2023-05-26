package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.MintRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class MintRepositoryImpl extends DatabaseHelper implements MintRepository {
    private final static String TABLE_NAME = "Mint";
    private String nameDB = "CoinTrackTest";
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }
    @Override
    public List<Mint> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    @Override
    public Optional<Mint> findOneById(int id) throws SQLException {
        List<Mint> mints = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (mints.size() > 0)
            return Optional.of(mints.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Mint> findByName(String name) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(name))));
    }

    @Override
    public List<Mint> findByCountryId(int countryId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(countryId))));
    }

    @Override
    public int create(Mint mint) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(mint)));
    }

    @Override
    public int edit(Mint mint) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(mint), mint.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    private List<Mint> mapper(ResultSet resultSet) throws SQLException {
        List<Mint> mints = new ArrayList<>();
        Mint mint;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int countryId = resultSet.getInt("countryId");
            String name = resultSet.getString("name");
            mint = new Mint(id, name, countryId);
            mints.add(mint);
        }
        connectionClose();
        return mints;
    }

    private String getParameters(Mint mint){
        return "name='" + mint.getName() +
                "', countryId=" + mint.getCountryId();
    }

    private String getParameters(int countryId){
        return "countryId=" + countryId;
    }
    private String getParameters(String name){
        return "name='" + name + "'";
    }
}
