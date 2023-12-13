package shapov.cointrack.repositories.implement;

import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Album;
import shapov.cointrack.repositories.AlbumRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**

 Класс AlbumRepositoryImpl представляет реализацию интерфейса AlbumRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных связанных с альбомами.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу даннхы по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class AlbumRepositoryImpl extends DatabaseHelper implements AlbumRepository {

    /** Поле константа название таблицы в базе данных */
    private final static String TABLE_NAME = "Album";

    /** Поле название базы данных */
    private String nameDB = "CoinTrackTest";

    public AlbumRepositoryImpl() {
    }

    public AlbumRepositoryImpl(String nameDB) {
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
     Извлекает все альбомы из базы данных.
     @return Список объектов Album.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Album> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    /**
     Извлекает альбом из базы данных по его ID.
     @param id ID альбома для извлечения.
     @return Объект Optional, содержащий Album, если найден, или пустой Optional, если не найден.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Album> findOneById(int id) throws SQLException {
        List<Album> albums = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (albums.size() > 0)
            return Optional.of(albums.get(0));
        else
            return Optional.empty();
    }

    /**
     Извлекает альбомы из базы данных по их названию.
     @param title Название альбомов для извлечения.
     @return Список объектов Album, соответствующих названию.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Album> findByTitle(String title) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(title))));
    }

    /**
     Создает новый альбом в базе данных.
     @param album Объект Album для создания.
     @return Количество затронутых строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int create(Album album) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(album)));
    }

    /**
     Обновляет существующий альбом в базе данных.
     @param album Объект Album для обновления.
     @return Количество затронутых строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int edit(Album album) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(album), album.getId()));
    }

    /**
     Удаляет альбом из базы данных по его ID.
     @param id ID альбома для удаления.
     @return Количество затронутых строк в базе данных.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    /**
     Отображает объект ResultSet в список объектов Album.
     @param resultSet Объект ResultSet для отображения.
     @return Список объектов Album.
     @throws SQLException если произошла ошибка при отображении ResultSet.
     */
    private List<Album> mapper(ResultSet resultSet) throws SQLException {
        List<Album> albums = new ArrayList<>();
        Album album;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            album = new Album(id, title);
            albums.add(album);
        }
        connectionClose();
        return albums;
    }

    /**
     Возвращает параметры альбома в виде строки.
     @param album Объект Album.
     @return Строка параметров альбома.
     */
    private String getParameters(Album album){
        return "title='" + album.getTitle() + "' ";
    }

    /**
     Возвращает параметры названия альбома в виде строки.
     @param title Название альбома.
     @return Строка параметров названия альбома.
     */
    private String getParameters(String title){
        return "title='" + title + "' ";
    }
}
