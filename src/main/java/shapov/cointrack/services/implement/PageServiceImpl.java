package shapov.cointrack.services.implement;

import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.PageRepository;
import shapov.cointrack.repositories.implement.PageRepositoryImpl;
import shapov.cointrack.services.PageService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.HolderCellRepository;
import shapov.cointrack.repositories.implement.HolderCellRepositoryImpl;

/**

 Класс PageServiceImpl представляет реализацию интерфейса PageService.

 Он предоставляет методы для выполнения операций с страницами, используя слои репозиториев.

 @author ShapovAA

 @version 1.0
 */
public class PageServiceImpl implements PageService {
    
    /**
     Репозиторий для работы с страницами.
     */
    private final PageRepository pageRepository = new PageRepositoryImpl();
    
    /**
     Репозиторий для работы с ячейками хранения.
     */
    private final HolderCellRepository holderCellRepository = new HolderCellRepositoryImpl();

    /**
     Получает список всех страниц.
     @return список всех страниц
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Page> findAll() throws SQLException, IOException {
        return pageRepository.findAll();
    }

    /**
     Находит страницу по указанному идентификатору.
     @param id идентификатор страницы
     @return страница, если найдена, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Page> findOneById(int id) throws SQLException, IOException {
        return id == 0 ? Optional.empty() :  pageRepository.findOneById(id);
    }

    /**
     Находит страницы с указанным идентификатором альбома.
     @param albumId идентификатор альбома
     @return список страниц с указанным идентификатором альбома
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Page> findByAlbumId(int albumId) throws SQLException, IOException {
        return pageRepository.findByAlbumId(albumId);
    }

    /**
     * Создает новую страницу с указанными параметрами.
     *
     * @param albumId        идентификатор альбома
     * @param previousPageId id предыдущей страницы
     * @param nextPageId     id следующей страницы
     * @param title          название страницы
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void create(int albumId, int previousPageId, int nextPageId, String title) throws SQLException, IOException {
        pageRepository.create(new Page(albumId, previousPageId, nextPageId, title));
    }

    /**
     * Обновляет страницу с указанным идентификатором новыми параметрами.
     *
     * @param id             идентификатор страницы, которую нужно обновить
     * @param albumId        идентификатор альбома
     * @param previousPageId id предыдущей страницы
     * @param nextPageId     id следующей страницы
     * @param title          новое название страницы
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void update(int id, int albumId, int previousPageId, int nextPageId, String title) throws SQLException, IOException {
        pageRepository.edit(new Page(id, albumId, previousPageId, nextPageId, title));
    }

    /**
     * Удаляет страницу с указанным идентификатором.
     *
     * @param id идентификатор страницы, которую нужно удалить
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        for(HolderCell holderCell : holderCellRepository.findByPageId(id))
            holderCellRepository.delete(holderCell.id);
        pageRepository.delete(id);
    }
}
