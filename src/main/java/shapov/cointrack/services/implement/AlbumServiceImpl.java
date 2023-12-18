package shapov.cointrack.services.implement;

import shapov.cointrack.models.Album;
import shapov.cointrack.repositories.AlbumRepository;
import shapov.cointrack.repositories.implement.AlbumRepositoryImpl;
import shapov.cointrack.services.AlbumService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import shapov.cointrack.models.HolderCell;
import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.HolderCellRepository;
import shapov.cointrack.repositories.PageRepository;
import shapov.cointrack.repositories.implement.HolderCellRepositoryImpl;
import shapov.cointrack.repositories.implement.PageRepositoryImpl;

/**
 Класс AlbumServiceImpl представляет реализацию интерфейса AlbumService.

 Он предоставляет методы для выполнения операций с слоем репозитория, связанных с альбомами.

 @author ShapovAA

 @version 1.0
 */
public class AlbumServiceImpl implements AlbumService {
    
    /**
     * Поле репозиторя альбомов. В качестве параметра для конструктора наименование базы данных.
     */
    private final AlbumRepository albumRepository = new AlbumRepositoryImpl();
    
    /**
     * Поле репозиторя страниц. В качестве параметра для конструктора наименование базы данных.
     */
    private final PageRepository pageRepository = new PageRepositoryImpl();
    
    /**
     * Поле репозиторя ячеек хранений. В качестве параметра для конструктора наименование базы данных.
     */
    private final HolderCellRepository holderCellRepository = new HolderCellRepositoryImpl();

    /**
     * Получает список всех альбомов.
     * @return список всех альбомов
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Album> findAll() throws SQLException, IOException {
        return albumRepository.findAll();
    }

    /**
     * Создает новый альбом с указанным заголовком.
     *
     * @param title заголовок нового альбома
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void create(String title) throws SQLException, IOException {
        albumRepository.create(new Album(title));
    }

    /**
     * Обновляет альбом с указанным идентификатором новым заголовком.
     *
     * @param id    идентификатор альбома, который нужно обновить
     * @param title новый заголовок альбома
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void update(int id, String title) throws SQLException, IOException {
        albumRepository.edit(new Album(id, title));
    }

    /**
     * Удаляет альбом с указанным идентификатором.
     *
     * @param id идентификатор альбома, который нужно удалить
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        List<Page> pages = pageRepository.findByAlbumId(id);
        for(Page page : pages) {
            for (HolderCell holderCell : holderCellRepository.findByPageId(page.getId()))
                holderCellRepository.delete(holderCell.id);
            pageRepository.delete(page.getId());
        }
        
        albumRepository.delete(id);
    }
}
