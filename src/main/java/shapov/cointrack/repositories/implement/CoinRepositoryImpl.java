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

/**

 Класс CoinRepositoryImpl представляет реализацию интерфейса CoinRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с монетами.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу даннхы по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class CoinRepositoryImpl extends DatabaseHelper implements CoinRepository {

    /** Поле константа название таблицы в базе данных */
    private final static String TABLE_NAME = "Coin";

    /** Поле константа параметры для форматирования запроса */
    private final static String PARAMETERS = "denomination=%d, currencyId=%d, countryId=%d, mintId=%d, yearMinting=%d, picturePath='%s' ";

    /** Поле название базы данных */
    private String nameDB = "CoinTrackTest";

    /**
     Получает полное имя таблицы, объединяя имя базы данных и имя таблицы.
     @return Полное имя таблицы.
     */
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }

    /**
     Извлекает все монеты из базы данных.
     @return Список объектов Coin.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    /**
     Извлекает монету из базы данных по ее ID.
     @param id ID монеты для извлечения.
     @return Объект Optional, содержащий Coin, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Coin> findOneById(int id) throws SQLException {
        List<Coin> coins = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (coins.size() > 0)
            return Optional.of(coins.get(0));
        else
            return Optional.empty();
    }

    /**
     Извлекает монеты из базы данных по их номиналу.
     @param denomination Номинал монеты для извлечения.
     @return Список объектов Coin, соответствующих номиналу.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findByDenomination(int denomination) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "denomination=" + denomination)));
    }

    /**
     Извлекает монеты из базы данных по ID валюты.
     @param currencyId ID валюты для извлечения.
     @return Список объектов Coin, соответствующих ID валюты.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findByCurrencyId(int currencyId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "currencyId=" + currencyId)));
    }

    /**
     Извлекает монеты из базы данных по ID страны.
     @param countryId ID страны для извлечения.
     @return Список объектов Coin, соответствующих ID страны.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findByCountryId(int countryId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "countryId=" + countryId)));
    }

    /**
     Извлекает монеты из базы данных по ID монетного двора.
     @param mintId ID монетного двора для извлечения.
     @return Список объектов Coin, соответствующих ID монетного двора.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findByMintId(int mintId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "mintId=" + mintId)));
    }

    /**
     Извлекает монеты из базы данных по году выпуска.
     @param yearMinting Год выпуска монеты для извлечения.
     @return Список объектов Coin, соответствующих году выпуска.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Coin> findByDateMinting(int yearMinting) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "yearMinting=" + yearMinting)));
    }

    /**
     Создает новую монету в базе данных.
     @param coin Объект Coin для создания.
     @return Количество затронутых строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(Coin coin) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(coin)));
    }

    /**
     Обновляет существующую монету в базе данных.
     @param coin Объект Coin для обновления.
     @return Количество затронутых строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(Coin coin) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(coin), coin.getId()));
    }

    /**
     Удаляет монету из базы данных по ее ID.
     @param id ID монеты для удаления.
     @return Количество затронутых строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    /**
     Отображает объект ResultSet в список объектов Coin.
     @param resultSet Объект ResultSet для отображения.
     @return Список объектов Coin.
     @throws SQLException если произошла ошибка при отображении ResultSet.
     */
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

    /**
     Возвращает параметры монеты в виде строки.
     @param coin Объект Coin.
     @return Строка параметров монеты.
     */
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
