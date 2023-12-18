package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.PageRepository;

import java.io.IOException;
import java.sql.SQLException;
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
public class PageRepositoryImpl implements PageRepository {

    private static final String nameTable = "pages";

    public PageRepositoryImpl() {
    }

    /**
     Извлекает все страницы из базы данных.
     @return Список объектов Page.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Page> findAll() throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll(nameTable), new TypeReference<>(){});
    }

    /**
     Извлекает страницу из базы данных по ее ID.
     @param id ID страницы для извлечения.
     @return Объект Optional, содержащий Page, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Page> findOneById(int id) throws SQLException, IOException {
        Page page = new ObjectMapper().readValue(DatabaseHelper.getById(nameTable + "/get/" + id), Page.class);
        if(page == null)
            return Optional.empty();
        else
            return Optional.of(page);
    }

    /**
     Извлекает страницы из базы данных по ID альбома.
     @param albumId ID альбома для извлечения.
     @return Список объектов Page, соответствующих ID альбома.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Page> findByAlbumId(int albumId) throws SQLException, IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getById(nameTable + "/get-by-album-id/" + albumId), new TypeReference<>(){});
    }

    /**
     * Создает новую страницу в базе данных.
     *
     * @param page Объект Page для создания.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void create(Page page) throws SQLException, IOException {
        DatabaseHelper.create(nameTable, page);
    }

    /**
     * Редактирует существующую страницу в базе данных.
     *
     * @param page Объект Page для редактирования.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void edit(Page page) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, page, page.getId());
    }

    /**
     * Удаляет страницу из базы данных по ее ID.
     *
     * @param id ID страницы для удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
