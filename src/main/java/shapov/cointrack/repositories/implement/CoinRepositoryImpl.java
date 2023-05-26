package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Coin;
import shapov.cointrack.repositories.CoinRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class CoinRepositoryImpl extends DatabaseHelper implements CoinRepository {
    private final static String TABLE_NAME = "Coin";
    private final static String PARAMETERS = "denomination=%d, currencyId=%d, countryId=%d, mintId=%d, yearMinting=%d, picturePath='%s' ";

    private String nameDB = "CoinTrackTest";
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }
    @Override
    public List<Coin> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    @Override
    public Optional<Coin> findOneById(int id) throws SQLException {
        List<Coin> coins = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (coins.size() > 0)
            return Optional.of(coins.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Coin> findByDenomination(int denomination) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "denomination=" + denomination)));
    }

    @Override
    public List<Coin> findByCurrencyId(int currencyId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "currencyId=" + currencyId)));
    }

    @Override
    public List<Coin> findByCountryId(int countryId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "countryId=" + countryId)));
    }

    @Override
    public List<Coin> findByMintId(int mintId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "mintId=" + mintId)));
    }

    @Override
    public List<Coin> findByDateMinting(int yearMinting) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "yearMinting=" + yearMinting)));
    }

    @Override
    public int create(Coin coin) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(coin)));
    }

    @Override
    public int edit(Coin coin) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(coin), coin.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    private List<Coin> mapper(ResultSet resultSet) throws SQLException {
        List<Coin> coins = new ArrayList<>();
        Coin coin;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int denomination = resultSet.getInt("denomination");
            int currencyId = resultSet.getInt("currencyId");
            int countryId = resultSet.getInt("countryId");
            int mintId = resultSet.getInt("mintId");
            int yearMinting = resultSet.getInt("yearMinting");
            String picturePath = resultSet.getString("picturePath");
            coin = new Coin(id, denomination, currencyId, countryId, mintId, yearMinting, picturePath);
            coins.add(coin);
        }
        connectionClose();
        return coins;
    }

    private String getParameters(Coin coin){
        return String.format(PARAMETERS,
                coin.getDenomination(),
                coin.getCurrencyId(),
                coin.getCountryId(),
                coin.getMintId(),
                coin.getYearMinting(),
                coin.getPicturePath());
    }
}
