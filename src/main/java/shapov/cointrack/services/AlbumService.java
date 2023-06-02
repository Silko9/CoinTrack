package shapov.cointrack.services;

import shapov.cointrack.models.Album;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**

 Интерфейс AlbumService предоставляет операции для работы с альбомами.
 */
 public interface AlbumService {
 /**

 Получает все альбомы.
 @return список всех альбомов
 @throws SQLException если произошла ошибка при выполнении запроса к базе данных
 */
List<Album> findAll() throws SQLException;
/**

 Находит альбом по указанному идентификатору.
 @param id идентификатор альбома
 @return Optional с найденным альбомом или пустым значением, если альбом не найден
 @throws SQLException если произошла ошибка при выполнении запроса к базе данных
 */
        Optional<Album> findOneById(int id) throws SQLException;
/**

 Находит альбомы по указанному заголовку.
 @param title заголовок альбома
 @return список альбомов с указанным заголовком
 @throws SQLException если произошла ошибка при выполнении запроса к базе данных
 */
        List<Album> findByTitle(String title) throws SQLException;
/**

 Создает новый альбом с указанным заголовком.
 @param title заголовок альбома
 @return идентификатор созданного альбома
 @throws SQLException если произошла ошибка при выполнении запроса к базе данных
 */
        int create(String title) throws SQLException;
/**

 Обновляет заголовок альбома с указанным идентификатором.
 @param id идентификатор альбома
 @param title новый заголовок альбома
 @return количество обновленных альбомов (должно быть 0 или 1)
 @throws SQLException если произошла ошибка при выполнении запроса к базе данных
 */
        int update(int id, String title) throws SQLException;
/**

 Удаляет альбом с указанным идентификатором.
 @param id идентификатор альбома
 @return количество удаленных альбомов (должно быть 0 или 1)
 @throws SQLException если произошла ошибка при выполнении запроса к базе данных
 */
        int delete(int id) throws SQLException;
        }
