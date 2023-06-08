package shapov.cointrack.services.implement;

import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.PageRepository;
import shapov.cointrack.repositories.implement.PageRepositoryImpl;
import shapov.cointrack.services.PageService;

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
    private final PageRepository pageRepository = new PageRepositoryImpl("CoinTrack");
    
    /**
     Репозиторий для работы с ячейками хранения.
     */
    private final HolderCellRepository holderCellRepository = new HolderCellRepositoryImpl("CoinTrack");

    /**
     Получает список всех страниц.
     @return список всех страниц
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Page> findAll() throws SQLException {
        return pageRepository.findAll();
    }

    /**
     Находит страницу по указанному идентификатору.
     @param id идентификатор страницы
     @return страница, если найдена, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Page> findOneById(int id) throws SQLException {
        return pageRepository.findOneById(id);
    }

    /**
     Находит страницы с указанным идентификатором альбома.
     @param albumId идентификатор альбома
     @return список страниц с указанным идентификатором альбома
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Page> findByAlbumId(int albumId) throws SQLException {
        return pageRepository.findByAlbumId(albumId);
    }

    /**
     Создает новую страницу с указанными параметрами.
     @param albumId идентификатор альбома
     @param number номер страницы
     @param title название страницы
     @return количество созданных страниц (обычно 1, если страница успешо создалась)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(int albumId, int number, String title) throws SQLException {
        return pageRepository.create(new Page(albumId, number, title));
    }

    /**
     Обновляет страницу с указанным идентификатором новыми параметрами.
     @param id идентификатор страницы, которую нужно обновить
     @param albumId идентификатор альбома
     @param number новый номер страницы
     @param title новое название страницы
     @return количество обновленных страниц (обычно 1, если страница с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, int albumId, int number, String title) throws SQLException {
        return pageRepository.edit(new Page(id, albumId, number, title));
    }

    /**
     Удаляет страницу с указанным идентификатором.
     @param id идентификатор страницы, которую нужно удалить
     @return количество удаленных страниц (обычно 1, если страница с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        for(HolderCell holderCell : holderCellRepository.findByPageId(id))
            holderCellRepository.delete(holderCell.id);
        return pageRepository.delete(id);
    }
}
