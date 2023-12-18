package shapov.cointrack.services.implement;

import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.MintRepository;
import shapov.cointrack.repositories.implement.MintRepositoryImpl;
import shapov.cointrack.services.MintService;

import java.io.IOException;
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
    private final MintRepository mintRepository = new MintRepositoryImpl();

    /**
     Получает список всех монетных дворов.
     @return список всех монетных дворов
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Mint> findAll() throws SQLException, IOException {
        return mintRepository.findAll();
    }

    /**
     Находит монетный двор по указанному идентификатору.
     @param id идентификатор монетного двора
     @return монетный двор, если найден, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Mint> findOneById(int id) throws SQLException, IOException {
        return mintRepository.findOneById(id);
    }

    /**
     * Создает новый монетный двор с указанными параметрами.
     *
     * @param name      имя монетного двора
     * @param countryId идентификатор страны
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void create(String name, int countryId) throws SQLException, IOException {
        mintRepository.create(new Mint(name, countryId));
    }

    /**
     * Обновляет монетный двор с указанным идентификатором новыми параметрами.
     *
     * @param id        идентификатор монетного двора, который нужно обновить
     * @param name      новое имя монетного двора
     * @param countryId новый идентификатор страны
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void update(int id, String name, int countryId) throws SQLException, IOException {
        mintRepository.edit(new Mint(id, name, countryId));
    }

    /**
     * Удаляет монетный двор с указанным идентификатором.
     *
     * @param id идентификатор монетного двора, который нужно удалить
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        mintRepository.delete(id);
    }
}
