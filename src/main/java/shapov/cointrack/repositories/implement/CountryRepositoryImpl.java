package shapov.cointrack.repositories.implement;

import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Класс CountryRepositoryImpl представляет реализацию интерфейса CountryRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с странами.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу данных по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class CountryRepositoryImpl extends DatabaseHelper implements CountryRepository {

    /** Поле константа название таблицы в базе данных */
    private final static String TABLE_NAME = "Country";

    /** Поле константа название таблицы связей между странами и валютами */
    private final static String TABLE_RELATION_NAME = "RelationCountryCurrency";

    /** Поле название базы данных */
    private String nameDB = "CoinTrackTest";

    public CountryRepositoryImpl() {
    }

    public CountryRepositoryImpl(String nameDB) {
        this.nameDB = nameDB;
    }

    /**
     Получает полное имя таблицы, объединяя имя базы данных и имя таблицы.
     @param nameTable Имя таблицы.
     @return Полное имя таблицы.
     */
    private String getFullTableName(String nameTable){
        return nameDB + "." + nameTable;
    }

    /**
     Извлекает все страны из базы данных.
     @return Список объектов Country.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Country> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName(TABLE_NAME))));
    }

    /**
     Извлекает страну из базы данных по ее ID.
     @param id ID страны для извлечения.
     @return Объект Optional, содержащий Country, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Country> findOneById(int id) throws SQLException {
        List<Country> countries = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id=" + id)));
        if (countries.size() > 0)
            return Optional.of(countries.get(0));
        else
            return Optional.empty();
    }

    /**
     Извлекает страны из базы данных по их названию.
     @param name Название страны для извлечения.
     @return Список объектов Country, соответствующих названию.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Country> findByName(String name) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), getParameters(name))));
    }

    /**
     Извлекает страны из базы данных по ID валюты.
     @param currencyId ID валюты для извлечения.
     @return Список объектов Country, соответствующих ID валюты.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Country> findByCurrencyId(int currencyId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id IN (" + String.format(DatabaseQueryConst.SELECT_NO_ALL, "countryId", getFullTableName(TABLE_RELATION_NAME), getParametersRelation(currencyId)) + ")")));
    }

    /**
     Добавляет связь между страной и валютой в базу данных.
     @param countryId ID страны.
     @param currencyId ID валюты.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int addRelationCurrency(int countryId, int currencyId) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, ",")));
    }

    /**
     Удаляет связь между страной и валютой из базы данных.
     @param countryId ID страны.
     @param currencyId ID валюты.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int removeRelationCurrency(int countryId, int currencyId) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE_RELATION, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, " AND")));
    }

    /**
     Создает новую страну в базе данных.
     @param country Объект Country для создания.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(Country country) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_NAME), getParameters(country)));
    }

    /**
     Редактирует существующую страну в базе данных.
     @param country Объект Country для редактирования.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(Country country) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(TABLE_NAME), getParameters(country), country.getId()));
    }

    /**
     Удаляет страну из базы данных по ее ID.
     @param id ID страны для удаления.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(TABLE_NAME), id));
    }

    /**
     Метод-маппер для преобразования ResultSet в список объектов Country.
     @param resultSet ResultSet, полученный из базы данных.
     @return Список объектов Country.
     @throws SQLException если произошла ошибка при работе с ResultSet.
     */
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

    /**
     Генерирует параметры для запроса к базе данных для объекта Country.
     @param country Объект Country.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(Country country){
        return "name='" + country.getName() + "' ";
    }

    /**
     Генерирует параметры для запроса к базе данных по имени страны.
     @param name Имя страны.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(String name){
        return "name='" + name + "' ";
    }

    /**
     Генерирует параметры для запроса к базе данных для связи между страной и валютой.
     @param countryId ID страны.
     @param currencyId ID валюты.
     @param separator Разделитель параметров.
     @return Строка с параметрами для запроса.
     */
    private String getParametersRelation(int countryId, int currencyId , String separator){
        return "countryId=" + countryId +  separator + " currencyId=" + currencyId;
    }

    /**
     Генерирует параметры для запроса к базе данных по ID валюты.
     @param currencyId ID валюты.
     @return Строка с параметрами для запроса.
     */
    private String getParametersRelation(int currencyId ){
        return "currencyId=" + currencyId;
    }
}
