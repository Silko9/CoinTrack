package shapov.cointrack.services.implement;

import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;
import shapov.cointrack.repositories.implement.CountryRepositoryImpl;
import shapov.cointrack.services.CountryService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Класс CountryServiceImpl представляет реализацию интерфейса CountryService.
 * Он предоставляет методы для выполнения операций с странами, используя слой репозитория.

 @author ShapovAA

 @version 1.0
 */
public class CountryServiceImpl implements CountryService {

    /**
     * Репозиторий для работы со странами
     */
    private final CountryRepository countryRepository = new CountryRepositoryImpl();

    /**
     * Получает список всех стран.
     * @return список всех стран
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Country> findAll() throws SQLException, IOException {
        return countryRepository.findAll();
    }

    /**
     * Находит страну по указанному идентификатору.
     * @param id идентификатор страны
     * @return страна, если найдена, в противном случае пустое значение Optional
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Country> findOneById(int id) throws SQLException, IOException {
        return countryRepository.findOneById(id);
    }

    /**
     * Создает новую страну с указанным названием.
     *
     * @param name название страны
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void create(String name) throws SQLException, IOException {
        countryRepository.create(new Country(name));
    }

    /**
     * Обновляет название страны с указанным идентификатором.
     *
     * @param id   идентификатор страны
     * @param name новое название страны
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void update(int id, String name) throws SQLException, IOException {
        countryRepository.edit(new Country(id, name));
    }

    /**
     * Удаляет страну с указанным идентификатором.
     *
     * @param id идентификатор страны
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        countryRepository.delete(id);
    }
}
