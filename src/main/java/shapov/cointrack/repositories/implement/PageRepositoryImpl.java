package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Mint;
import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.PageRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Класс PageRepositoryImpl представляет реализацию интерфейса PageRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с страницами.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу данных по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class PageRepositoryImpl extends DatabaseHelper implements PageRepository {

    /** Поле константа название таблицы в базе данных */
    private final static String TABLE_NAME = "Page";

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
     Извлекает все страницы из базы данных.
     @return Список объектов Page.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Page> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    /**
     Извлекает страницу из базы данных по ее ID.
     @param id ID страницы для извлечения.
     @return Объект Optional, содержащий Page, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Page> findOneById(int id) throws SQLException {
        List<Page> pages = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (pages.size() > 0)
            return Optional.of(pages.get(0));
        else
            return Optional.empty();    }

    /**
     Извлекает страницы из базы данных по ID альбома.
     @param albumId ID альбома для извлечения.
     @return Список объектов Page, соответствующих ID альбома.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Page> findByAlbumId(int albumId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(albumId))));
    }

    /**
     Создает новую страницу в базе данных.
     @param page Объект Page для создания.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(Page page) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(page)));
    }

    /**
     Редактирует существующую страницу в базе данных.
     @param page Объект Page для редактирования.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(Page page) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(page), page.getId()));
    }

    /**
     Удаляет страницу из базы данных по ее ID.
     @param id ID страницы для удаления.
     @return Количество измененных строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    /**
     Метод-маппер для преобразования ResultSet в список объектов Page.
     @param resultSet ResultSet, полученный из базы данных.
     @return Список объектов Page.
     @throws SQLException если произошла ошибка при работе с ResultSet.
     */
    private List<Page> mapper(ResultSet resultSet) throws SQLException {
        List<Page> pages = new ArrayList<>();
        Page page;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int albumId = resultSet.getInt("albumId");
            int number = resultSet.getInt("number");
            String title = resultSet.getString("title");
            page = new Page(id, albumId, number, title);
            pages.add(page);
        }
        connectionClose();
        return pages;
    }

    /**
     Генерирует параметры для запроса к базе данных для объекта Page.
     @param page Объект Page.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(Page page){
        return "albumId=" + page.getAlbumId() +
                ", number=" + page.getNumber() +
                ", title='" + page.getTitle() + "' ";
    }

    /**
     Генерирует параметры для запроса к базе данных по ID альбома.
     @param albumId ID альбома.
     @return Строка с параметрами для запроса.
     */
    private String getParameters(int albumId){
        return "albumId=" + albumId;
    }
}
