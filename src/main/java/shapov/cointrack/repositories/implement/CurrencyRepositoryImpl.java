package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Currency;
import shapov.cointrack.repositories.CurrencyRepository;

import java.io.IOException;
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
public class CurrencyRepositoryImpl implements CurrencyRepository {

    /** Поле с константой, содержащей название таблицы в базе данных */
    private final static String nameTable = "currencies";

    public CurrencyRepositoryImpl() {
    }

    /**
     Извлекает все валюты из базы данных.
     @return Список объектов Currency.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Currency> findAll() throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll("currencies"), new TypeReference<>(){});
    }

    /**
     Извлекает валюту из базы данных по её ID.
     @param id ID валюты для извлечения.
     @return Объект Optional, содержащий Currency, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Currency> findOneById(int id) throws SQLException, IOException {
        Currency currency = new ObjectMapper().readValue(DatabaseHelper.getById(nameTable + "/get/" + id), Currency.class);
        if(currency == null)
            return Optional.empty();
        else
            return Optional.of(currency);
    }

    /**
     * Создает новую запись в базе данных с информацией о валюте.
     *
     * @param currency Объект Currency, представляющий информацию о валюте.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void create(Currency currency) throws SQLException, IOException {
        DatabaseHelper.create(nameTable, currency);
    }

    /**
     * Обновляет информацию о валюте в базе данных.
     *
     * @param currency Объект Currency, представляющий информацию о валюте.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void edit(Currency currency) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, currency, currency.getId());
    }

    /**
     * Удаляет запись с указанным идентификатором валюты из базы данных.
     *
     * @param id Идентификатор валюты для удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
