package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Currency;
import shapov.cointrack.repositories.CurrencyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRepositoryImpl extends DatabaseHelper implements CurrencyRepository {
    private final static String TABLE_NAME = "Currency";
    private final static String TABLE_RELATION_NAME = "RelationCountryCurrency";
    private String nameDB = "CoinTrackTest";

    private String getFullTableName(String nameTable){
        return nameDB + "." + nameTable;
    }
    @Override
    public List<Currency> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName(TABLE_NAME))));
    }

    @Override
    public Optional<Currency> findOneById(int id) throws SQLException {
        List<Currency> currencies = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id=" + id)));
        if (currencies.size() > 0)
            return Optional.of(currencies.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Currency> findByName(String name) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), getParameters(name))));
    }

    @Override
    public List<Currency> findByCountryId(int countryId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id IN (" + String.format(DatabaseQueryConst.SELECT_NO_ALL, "currencyId", getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId)) + ")")));
    }

    @Override
    public int addRelationCountry(int currencyId, int countryId) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, ",")));
    }

    @Override
    public int removeRelationCountry(int currencyId, int countryId) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE_RELATION, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, " AND")));
    }

    @Override
    public int create(Currency currency) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_NAME), getParameters(currency)));
    }

    @Override
    public int edit(Currency currency) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(TABLE_NAME), getParameters(currency), currency.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(TABLE_NAME), id));
    }

    private List<Currency> mapper(ResultSet resultSet) throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        Currency currency;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            currency = new Currency(id, name);
            currencies.add(currency);
        }
        connectionClose();
        return currencies;
    }

    private String getParameters(Currency currency){
        return "name='" + currency.getName() + "' ";
    }
    private String getParameters(String name){
        return "name='" + name + "' ";
    }
    private String getParametersRelation(int countryId, int currencyId , String separator){
        return "countryId=" + countryId +  separator + " currencyId=" + currencyId;
    }
    private String getParametersRelation(int countryId ){
        return "countryId=" + countryId;
    }
}
