package shapov.cointrack.services.implement;

import shapov.cointrack.models.Album;
import shapov.cointrack.repositories.AlbumRepository;
import shapov.cointrack.repositories.implement.AlbumRepositoryImpl;
import shapov.cointrack.services.AlbumService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    private final AlbumRepository albumRepository = new AlbumRepositoryImpl("CoinTrack");

    /**
     * Получает список всех альбомов.
     * @return список всех альбомов
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Album> findAll() throws SQLException {
        return albumRepository.findAll();
    }

    /**
     * Находит альбом по указанному идентификатору.
     * @param id идентификатор альбома
     * @return альбом, если найден, в противном случае пустое значение Optional
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Album> findOneById(int id) throws SQLException {
        return albumRepository.findOneById(id);
    }

    /**
     * Находит альбомы по указанному заголовку.
     * @param title заголовок альбома
     * @return список альбомов с указанным заголовком
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Album> findByTitle(String title) throws SQLException {
        return albumRepository.findByTitle(title);
    }

    /**
     * Создает новый альбом с указанным заголовком.
     * @param title заголовок нового альбома
     * @return количество созданных монет (обычно 1, если монета успешно создалась)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(String title) throws SQLException {
        return albumRepository.create(new Album(title));
    }

    /**
     * Обновляет альбом с указанным идентификатором новым заголовком.
     * @param id    идентификатор альбома, который нужно обновить
     * @param title новый заголовок альбома
     * @return количество обновленных альбомов (обычно 1, если альбом с указанным идентификатором существует)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, String title) throws SQLException {
        return albumRepository.edit(new Album(id, title));
    }

    /**
     * Удаляет альбом с указанным идентификатором.
     * @param id идентификатор альбома, который нужно удалить
     * @return количество удаленных альбомов (обычно 1, если альбом с указанным идентификатором существует)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        return albumRepository.delete(id);
    }
}
