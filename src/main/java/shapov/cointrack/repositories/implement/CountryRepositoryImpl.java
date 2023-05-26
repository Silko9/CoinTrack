package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class CountryRepositoryImpl extends DatabaseHelper implements CountryRepository {
    private final static String TABLE_NAME = "Country";
    private final static String TABLE_RELATION_NAME = "RelationCountryCurrency";
    private String nameDB = "CoinTrackTest";
    private String getFullTableName(String nameTable){
        return nameDB + "." + nameTable;
    }
    @Override
    public List<Country> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName(TABLE_NAME))));
    }

    @Override
    public Optional<Country> findOneById(int id) throws SQLException {
        List<Country> countries = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id=" + id)));
        if (countries.size() > 0)
            return Optional.of(countries.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<Country> findByName(String name) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), getParameters(name))));
    }

    @Override
    public List<Country> findByCurrencyId(int currencyId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id IN (" + String.format(DatabaseQueryConst.SELECT_NO_ALL, "countryId", getFullTableName(TABLE_RELATION_NAME), getParametersRelation(currencyId)) + ")")));
    }

    @Override
    public int addRelationCurrency(int countryId, int currencyId) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, ",")));
    }

    @Override
    public int removeRelationCurrency(int countryId, int currencyId) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE_RELATION, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, " AND")));
    }

    @Override
    public int create(Country country) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_NAME), getParameters(country)));
    }

    @Override
    public int edit(Country country) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(TABLE_NAME), getParameters(country), country.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(TABLE_NAME), id));
    }

    private List<Country> mapper(ResultSet resultSet) throws SQLException {
        List<Country> countries = new ArrayList<>();
        Country country;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            country = new Country(id, name);
            countries.add(country);
        }
        connectionClose();
        return countries;
    }

    private String getParameters(Country country){
        return "name='" + country.getName() + "' ";
    }
    private String getParameters(String name){
        return "name='" + name + "' ";
    }
    private String getParametersRelation(int countryId, int currencyId , String separator){
        return "countryId=" + countryId +  separator + " currencyId=" + currencyId;
    }
    private String getParametersRelation(int currencyId ){
        return "currencyId=" + currencyId;
    }
}
