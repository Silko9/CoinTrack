package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Coin;
import shapov.cointrack.repositories.CoinRepository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 Класс CoinRepositoryImpl представляет реализацию интерфейса CoinRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с монетами.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу даннхы по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class CoinRepositoryImpl implements CoinRepository {

    /** Поле константа название таблицы в базе данных */
    private final static String nameTable = "coins";

    public CoinRepositoryImpl() {
    }

    /**
     Извлекает все монеты из базы данных.
     @return Список объектов Coin.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findAll() throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll("coins"), new TypeReference<>(){});
    }

    /**
     Извлекает монету из базы данных по ее ID.
     @param id ID монеты для извлечения.
     @return Объект Optional, содержащий Coin, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Coin> findOneById(int id) throws SQLException, IOException {
        Coin coin = new ObjectMapper().readValue(DatabaseHelper.getById("coins", id), Coin.class);
        if(coin == null)
            return Optional.empty();
        else
            return Optional.of(coin);
    }

    /**
     * Создает новую монету в базе данных.
     *
     * @param coin Объект Coin для создания.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void create(Coin coin) throws SQLException, IOException {
        DatabaseHelper.create(nameTable, coin);
    }

    /**
     * Обновляет существующую монету в базе данных.
     *
     * @param coin Объект Coin для обновления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void edit(Coin coin) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, coin, coin.getId());
    }

    /**
     * Удаляет монету из базы данных по ее ID.
     *
     * @param id ID монеты для удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
