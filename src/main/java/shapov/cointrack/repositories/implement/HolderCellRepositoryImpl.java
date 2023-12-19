package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.HolderCellRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**

 Класс HolderCellRepositoryImpl представляет реализацию интерфейса HolderCellRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с ячейками хранения HolderCell.

 Конструкторы реализованы с помощью lombok. Без аргументов, будет использовать базу данных по умолчанию: CoinTrackTest.

 С одним аргументом, указывается название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class HolderCellRepositoryImpl implements HolderCellRepository {

    /** Поле константа с названием таблицы */
    private final static String nameTable = "holderCells";

    public HolderCellRepositoryImpl() {
    }


    /**
     Извлекает все ячейки хранения HolderCell из базы данных.
     @return Список объектов HolderCell.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<HolderCell> findAll() throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll("holderCells"), new TypeReference<>(){});
    }

    /**
     Извлекает ячейку хранения HolderCell из базы данных по ее ID.
     @param id ID ячейки хранения для извлечения.
     @return Объект Optional, содержащий HolderCell, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<HolderCell> findOneById(int id) throws SQLException, IOException {
        HolderCell holderCell = new ObjectMapper().readValue(DatabaseHelper.getById(nameTable + "/get/" + id), HolderCell.class);
        if(holderCell == null)
            return Optional.empty();
        else
            return Optional.of(holderCell);
    }

    /**
     Извлекает ячейки хранения HolderCell из базы данных по ID страницы.
     @param pageId ID страницы для извлечения.
     @return Список объектов HolderCell, соответствующих ID страницы.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<HolderCell> findByPageId(int pageId) throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getById("holderCells/get-by-page-id/" + pageId), new TypeReference<>(){});
    }

    /**
     * Создает новую ячейку хранения HolderCell в базе данных.
     *
     * @param holderCell Объект HolderCell для создания.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void create(HolderCell holderCell) throws SQLException, IOException {
        DatabaseHelper.create("holderCells", holderCell);
    }

    /**
     * Редактирует существующую ячейку хранения HolderCell в базе данных.
     *
     * @param holderCell Объект HolderCell для редактирования.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void edit(HolderCell holderCell) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, holderCell, holderCell.getId());
    }

    /**
     * Удаляет ячейку хранения HolderCell из базы данных по ее ID.
     *
     * @param id ID ячейки хранения для удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
