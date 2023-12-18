package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.MintRepository;

import java.io.IOException;
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
public class MintRepositoryImpl implements MintRepository {

    /** Поле константа с названием таблицы в базе данных */
    private final static String nameTable = "mints";

    public MintRepositoryImpl() {
    }

    /**
     Извлекает все монетные дворы из базы данных.
     @return Список объектов Mint.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Mint> findAll() throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll("mints"), new TypeReference<>(){});
    }

    /**
     Извлекает монетный двор из базы данных по его ID.
     @param id ID монетного двора для извлечения.
     @return Объект Optional, содержащий Mint, если найден, или пустой Optional, если не найден.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Mint> findOneById(int id) throws SQLException, IOException {
        Mint mint = new ObjectMapper().readValue(DatabaseHelper.getById(nameTable + "/get/" + id), Mint.class);
        if(mint == null)
            return Optional.empty();
        else
            return Optional.of(mint);
    }

    /**
     * Создает новый монетный двор в базе данных.
     *
     * @param mint Объект Mint для создания.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void create(Mint mint) throws SQLException, IOException {
        DatabaseHelper.create(nameTable, mint);
    }

    /**
     * Редактирует существующий монетный двор в базе данных.
     *
     * @param mint Объект Mint для редактирования.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void edit(Mint mint) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, mint, mint.getId());
    }

    /**
     * Удаляет монетный двор из базы данных по его ID.
     *
     * @param id ID монетного двора для удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
