package shapov.cointrack.repositories.implement;

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


/**

 Класс MintRepositoryImpl представляет реализацию интерфейса MintRepository и наследуется от класса DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с монетными дворами.

 Конструкторы реализованы с помощью lombok.

 Без аргументов будет использоваться база данных по умолчанию: CoinTrackTest.

 С одним аргументом - название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class MintRepositoryImpl extends DatabaseHelper implements MintRepository {

    /** Поле константа с названием таблицы в базе данных */
    private final static String TABLE_NAME = "Mint";

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
     Извлекает все монетные дворы из базы данных.
     @return Список объектов Mint.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Mint> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    /**
     Извлекает монетный двор из базы данных по его ID.
     @param id ID монетного двора для извлечения.
     @return Объект Optional, содержащий Mint, если найден, или пустой Optional, если не найден.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Mint> findOneById(int id) throws SQLException {
        List<Mint> mints = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (mints.size() > 0)
            return Optional.of(mints.get(0));
        else
            return Optional.empty();
    }

    /**
     Извлекает монетные дворы из базы данных по их названию.
     @param name Название монетного двора для извлечения.
     @return Список объектов Mint, соответствующих названию.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Mint> findByName(String name) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(name))));
    }

    /**
     Извлекает монетные дворы из базы данных по ID страны.
     @param countryId ID страны для извлечения.
     @return Список объектов Mint, соответствующих ID страны.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Mint> findByCountryId(int countryId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(countryId))));
    }

    /**
     Создает новый монетный двор в базе данных.
     @param mint Объект Mint для создания.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(Mint mint) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(mint)));
    }

    /**
     Редактирует существующий монетный двор в базе данных.
     @param mint Объект Mint для редактирования.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(Mint mint) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(mint), mint.getId()));
    }

    /**
     Удаляет монетный двор из базы данных по его ID.
     @param id ID монетного двора для удаления.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    /**
     Метод-маппер для преобразования ResultSet в список объектов Mint.
     @param resultSet ResultSet, полученный из базы данных.
     @return Список объектов Mint.
     @throws SQLException если произошла ошибка при работе с ResultSet.
     */
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

    /**
     Генерирует параметры для запроса к базе данных для объекта Mint.
     @param mint Объект Mint.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(Mint mint){
        return "name='" + mint.getName() +
                "', countryId=" + mint.getCountryId();
    }

    /**
     Генерирует параметры для запроса к базе данных по ID страны.
     @param countryId ID страны.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(int countryId){
        return "countryId=" + countryId;
    }

    /**
     Генерирует параметры для запроса к базе данных по названию страны.
     @param name название страны.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(String name){
        return "name='" + name + "'";
    }

    public MintRepositoryImpl(String nameDB) {
        this.nameDB = nameDB;
    }

    public MintRepositoryImpl() {
    }
}
