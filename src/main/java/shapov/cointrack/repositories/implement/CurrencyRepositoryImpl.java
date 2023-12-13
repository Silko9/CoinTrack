package shapov.cointrack.repositories.implement;

import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Currency;
import shapov.cointrack.repositories.CurrencyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 Реализация интерфейса CurrencyRepository для работы с базой данных.

 Класс CurrencyRepositoryImpl наследуется от класса DatabaseHelper.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу данных по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class CurrencyRepositoryImpl extends DatabaseHelper implements CurrencyRepository {

    /** Поле с константой, содержащей название таблицы в базе данных */
    private final static String TABLE_NAME = "Currency";

    /** Поле с константой, содержащей название таблицы связей между странами и валютами */
    private final static String TABLE_RELATION_NAME = "RelationCountryCurrency";

    /** Поле, содержащее название базы данных */
    private String nameDB = "CoinTrackTest";

    public CurrencyRepositoryImpl() {
    }

    public CurrencyRepositoryImpl(String nameDB) {
        this.nameDB = nameDB;
    }

    /**
     * Метод получения полного имени таблицы, объединяя имя базы данных и имя таблицы.
     * @param nameTable Имя таблицы.
     * @return Полное имя таблицы.
     */
    private String getFullTableName(String nameTable){
        return nameDB + "." + nameTable;
    }

    /**
     Извлекает все валюты из базы данных.
     @return Список объектов Currency.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Currency> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName(TABLE_NAME))));
    }

    /**
     Извлекает валюту из базы данных по её ID.
     @param id ID валюты для извлечения.
     @return Объект Optional, содержащий Currency, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Currency> findOneById(int id) throws SQLException {
        List<Currency> currencies = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id=" + id)));
        if (currencies.size() > 0)
            return Optional.of(currencies.get(0));
        else
            return Optional.empty();
    }

    /**
     Извлекает валюты из базы данных по их названию.
     @param name Название валюты для извлечения.
     @return Список объектов Currency, соответствующих названию.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Currency> findByName(String name) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), getParameters(name))));
    }

    /**
     * Извлекает валюты из базы данных, связанные с указанным идентификатором страны.
     *
     * @param countryId Идентификатор страны для извлечения связанных валют.
     * @return Список объектов Currency, связанных с указанной страной.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Currency> findByCountryId(int countryId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(TABLE_NAME), "id IN (" + String.format(DatabaseQueryConst.SELECT_NO_ALL, "currencyId", getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId)) + ")")));
    }

    /**
     * Добавляет связь между указанным идентификатором валюты и идентификатором страны в базу данных.
     *
     * @param currencyId Идентификатор валюты.
     * @param countryId  Идентификатор страны.
     * @return Количество добавленных записей в базу данных.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int addRelationCountry(int currencyId, int countryId) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, ",")));
    }

    /**
     * Удаляет связь между указанным идентификатором валюты и идентификатором страны из базы данных.
     *
     * @param currencyId Идентификатор валюты.
     * @param countryId  Идентификатор страны.
     * @return Количество удаленных записей из базы данных.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int removeRelationCountry(int currencyId, int countryId) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE_RELATION, getFullTableName(TABLE_RELATION_NAME), getParametersRelation(countryId, currencyId, " AND")));
    }

    /**
     * Создает новую запись в базе данных с информацией о валюте.
     *
     * @param currency Объект Currency, представляющий информацию о валюте.
     * @return Количество добавленных записей в базу данных.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(Currency currency) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(TABLE_NAME), getParameters(currency)));
    }

    /**
     * Обновляет информацию о валюте в базе данных.
     *
     * @param currency Объект Currency, представляющий информацию о валюте.
     * @return Количество обновленных записей в базе данных.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(Currency currency) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(TABLE_NAME), getParameters(currency), currency.getId()));
    }

    /**
     * Удаляет запись с указанным идентификатором валюты из базы данных.
     *
     * @param id Идентификатор валюты для удаления.
     * @return Количество удаленных записей из базы данных.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(TABLE_NAME), id));
    }

    /**
     * Преобразует результаты запроса ResultSet в список объектов Currency.
     *
     * @param resultSet Результаты запроса ResultSet.
     * @return Список объектов Currency, соответствующих результатам запроса.
     * @throws SQLException если произошла ошибка при доступе к данным из ResultSet.
     */
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

    /**
     * Возвращает параметры для запроса на добавление/обновление валюты в базе данных.
     *
     * @param currency Объект Currency, представляющий информацию о валюте.
     * @return Строка, содержащая параметры для запроса.
     */
    private String getParameters(Currency currency){
        return "name='" + currency.getName() + "' ";
    }

    /**
     * Возвращает параметры для запроса на извлечение валюты из базы данных по имени.
     *
     * @param name Имя валюты.
     * @return Строка, содержащая параметры для запроса.
     */
    private String getParameters(String name){
        return "name='" + name + "' ";
    }

    /**
     * Возвращает параметры для запроса связи между идентификатором страны и идентификатором валюты.
     *
     * @param countryId  Идентификатор страны.
     * @param currencyId Идентификатор валюты.
     * @param separator  Разделитель для параметров в запросе.
     * @return Строка, содержащая параметры для запроса связи.
     */
    private String getParametersRelation(int countryId, int currencyId , String separator){
        return "countryId=" + countryId +  separator + " currencyId=" + currencyId;
    }

    /**
     * Возвращает параметры для запроса связи между идентификатором страны и валюты.
     *
     * @param countryId Идентификатор страны.
     * @return Строка, содержащая параметры для запроса связи.
     */
    private String getParametersRelation(int countryId ){
        return "countryId=" + countryId;
    }
}
