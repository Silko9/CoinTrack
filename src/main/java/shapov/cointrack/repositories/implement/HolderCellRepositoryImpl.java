package shapov.cointrack.repositories.implement;

import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.HolderCellRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class HolderCellRepositoryImpl extends DatabaseHelper implements HolderCellRepository {

    /** Поле константа с названием таблицы */
    private final static String TABLE_NAME = "HolderCell";

    /** Поле с названием базы данных */
    private String nameDB = "CoinTrackTest";

    public HolderCellRepositoryImpl() {
    }

    public HolderCellRepositoryImpl(String nameDB) {
        this.nameDB = nameDB;
    }

    /**
     Получает полное имя таблицы, объединяя имя базы данных и имя таблицы.
     @return Полное имя таблицы.
     */
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }

    /**
     Извлекает все ячейки хранения HolderCell из базы данных.
     @return Список объектов HolderCell.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<HolderCell> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    /**
     Извлекает ячейку хранения HolderCell из базы данных по ее ID.
     @param id ID ячейки хранения для извлечения.
     @return Объект Optional, содержащий HolderCell, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<HolderCell> findOneById(int id) throws SQLException {
        List<HolderCell> holderCells = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (!holderCells.isEmpty())
            return Optional.of(holderCells.get(0));
        else
            return Optional.empty();
    }

    /**
     Извлекает ячейки хранения HolderCell из базы данных по ID страницы.
     @param pageId ID страницы для извлечения.
     @return Список объектов HolderCell, соответствующих ID страницы.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<HolderCell> findByPageId(int pageId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(pageId))));
    }

    /**
     * Создает новую ячейку хранения HolderCell в базе данных.
     * @param holderCell Объект HolderCell для создания.
     * @return Количество строк, затронутых операцией создания.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(HolderCell holderCell) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(holderCell)));
    }

    /**
     * Редактирует существующую ячейку хранения HolderCell в базе данных.
     * @param holderCell Объект HolderCell для редактирования.
     * @return Количество строк, затронутых операцией редактирования.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(HolderCell holderCell) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(holderCell), holderCell.getId()));
    }

    /**
     * Удаляет ячейку хранения HolderCell из базы данных по ее ID.
     * @param id ID ячейки хранения для удаления.
     * @return Количество строк, затронутых операцией удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    /**
     * Преобразует ResultSet в список объектов HolderCell.
     * @param resultSet Результат выполнения SQL-запроса.
     * @return Список объектов HolderCell, полученных из ResultSet.
     * @throws SQLException если произошла ошибка при обработке ResultSet.
     */
    private List<HolderCell> mapper(ResultSet resultSet) throws SQLException {
        List<HolderCell> holderCells = new ArrayList<>();
        HolderCell holderCell;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int coinId = resultSet.getInt("coinId");
            boolean available = resultSet.getBoolean("available");
            int pageId = resultSet.getInt("pageId");
            int column = resultSet.getInt("columnHolder");
            int line = resultSet.getInt("lineHolder");
            String title = resultSet.getString("title");
            holderCell = new HolderCell(id, coinId, available, pageId, column, line, title);
            holderCells.add(holderCell);
        }
        connectionClose();
        return holderCells;
    }

    /**
     * Возвращает строку параметров для использования в SQL-запросе для объекта HolderCell.
     * @param holderCell Объект HolderCell, для которого нужно сформировать строку параметров.
     * @return Строка параметров для использования в SQL-запросе.
     */
    private String getParameters(HolderCell holderCell){
        int available = 0;
        if (holderCell.isAvailable()) available = 1;
        return "coinId=" + holderCell.getCoinId() +
                ", available=" + available +
                ", pageId=" + holderCell.getPageId() +
                ", columnHolder=" + holderCell.getColumn() +
                ", lineHolder=" + holderCell.getLine() +
                ", title='" + holderCell.getTitle() + "' ";
    }

    /**
     * Возвращает строку параметров для использования в SQL-запросе для заданного ID страницы.
     * @param pageId ID страницы, для которой нужно сформировать строку параметров.
     * @return Строка параметров для использования в SQL-запросе.
     */
    private String getParameters(int pageId){
        return "pageId=" + pageId;
    }
}
