package shapov.cointrack.services.implement;

import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.MintRepository;
import shapov.cointrack.repositories.implement.MintRepositoryImpl;
import shapov.cointrack.services.MintService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**

 Класс MintServiceImpl представляет реализацию интерфейса MintService.

 Он предоставляет методы для выполнения операций с монетными дворами, используя слои репозиториев.

 @author ShapovAA

 @version 1.0
 */
public class MintServiceImpl implements MintService {

    /**
     Репозиторий для работы с монетными дворами.
     */
    private final MintRepository mintRepository = new MintRepositoryImpl("CoinTrack");

    /**
     Получает список всех монетных дворов.
     @return список всех монетных дворов
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Mint> findAll() throws SQLException {
        return mintRepository.findAll();
    }

    /**
     Находит монетный двор по указанному идентификатору.
     @param id идентификатор монетного двора
     @return монетный двор, если найден, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Mint> findOneById(int id) throws SQLException {
        return mintRepository.findOneById(id);
    }

    /**
     Находит монетные дворы по указанному имени.
     @param name имя монетного двора
     @return список монетных дворов с указанным именем
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Mint> findByName(String name) throws SQLException {
        return mintRepository.findByName(name);
    }

    /**
     Находит монетные дворы с указанным идентификатором страны.
     @param countryId идентификатор страны
     @return список монетных дворов с указанным идентификатором страны
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Mint> findByCountryId(int countryId) throws SQLException {
        return mintRepository.findByCountryId(countryId);
    }

    /**
     Создает новый монетный двор с указанными параметрами.
     @param name имя монетного двора
     @param countryId идентификатор страны
     @return количество созданных монетных дворов (обычно 1, если монетный двор успешо создалась)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(String name, int countryId) throws SQLException {
        return mintRepository.create(new Mint(name, countryId));
    }

    /**
     Обновляет монетный двор с указанным идентификатором новыми параметрами.
     @param id идентификатор монетного двора, который нужно обновить
     @param name новое имя монетного двора
     @param countryId новый идентификатор страны
     @return количество обновленных монетных дворов (обычно 1, если монетный двор с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, String name, int countryId) throws SQLException {
        return mintRepository.edit(new Mint(id, name, countryId));
    }

    /**
     Удаляет монетный двор с указанным идентификатором.
     @param id идентификатор монетного двора, который нужно удалить
     @return количество удаленных монетных дворов (обычно 1, если монетный двор с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        return mintRepository.delete(id);
    }
}
