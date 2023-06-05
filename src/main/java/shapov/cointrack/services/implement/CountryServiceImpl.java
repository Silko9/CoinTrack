package shapov.cointrack.services.implement;

import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;
import shapov.cointrack.repositories.implement.CountryRepositoryImpl;
import shapov.cointrack.services.CountryService;

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
    private final CountryRepository countryRepository = new CountryRepositoryImpl("CoinTrack");

    /**
     * Получает список всех стран.
     * @return список всех стран
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Country> findAll() throws SQLException {
        return countryRepository.findAll();
    }

    /**
     * Находит страну по указанному идентификатору.
     * @param id идентификатор страны
     * @return страна, если найдена, в противном случае пустое значение Optional
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Country> findOneById(int id) throws SQLException {
        return countryRepository.findOneById(id);
    }

    /**
     * Находит страны с указанным названием.
     * @param name название страны
     * @return список стран с указанным названием
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Country> findByName(String name) throws SQLException {
        return countryRepository.findByName(name);
    }

    /**
     * Находит страны с указанным идентификатором валюты.
     * @param currencyId идентификатор валюты
     * @return список стран с указанным идентификатором валюты
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Country> findByCurrencyId(int currencyId) throws SQLException {
        return countryRepository.findByCurrencyId(currencyId);
    }

    /**
     * Добавляет связь между страной и валютой.
     * @param countryId  идентификатор страны
     * @param currencyId идентификатор валюты
     * @return количество добавленных связей (обычно 1, если связь успешно добавлена)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int addRelationCurrency(int countryId, int currencyId) throws SQLException {
        return countryRepository.addRelationCurrency(countryId, currencyId);
    }

    /**
     * Удаляет связь между страной и валютой.
     * @param countryId  идентификатор страны
     * @param currencyId идентификатор валюты
     * @return количество удаленных связей (обычно 1, если связь успешно удалена)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int removeRelationCurrency(int countryId, int currencyId) throws SQLException {
        return countryRepository.removeRelationCurrency(countryId, currencyId);
    }

    /**
     * Создает новую страну с указанным названием.
     * @param name название страны
     * @return количество созданных монет (обычно 1, если монета успешно создалась)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(String name) throws SQLException {
        return countryRepository.create(new Country(name));
    }

    /**
     * Обновляет название страны с указанным идентификатором.
     * @param id   идентификатор страны
     * @param name новое название страны
     * @return количество измененных записей (обычно 1, если обновление прошло успешно)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, String name) throws SQLException {
        return countryRepository.edit(new Country(id, name));
    }

    /**
     * Удаляет страну с указанным идентификатором.
     * @param id идентификатор страны
     * @return количество удаленных записей (обычно 1, если удаление прошло успешно)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        return countryRepository.delete(id);
    }
}
