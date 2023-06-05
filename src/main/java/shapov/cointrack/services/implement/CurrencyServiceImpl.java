package shapov.cointrack.services.implement;

import shapov.cointrack.repositories.CurrencyRepository;
import shapov.cointrack.repositories.implement.CurrencyRepositoryImpl;
import shapov.cointrack.services.CurrencyService;
import shapov.cointrack.models.Currency;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**

 Класс CurrencyServiceImpl представляет реализацию интерфейса CurrencyService.

 Он предоставляет методы для выполнения операций с валютами, используя слой репозитория.

 @author ShapovAA

 @version 1.0
 */
public class CurrencyServiceImpl implements CurrencyService {

    /**
     Репозиторий для работы с валютами
     */
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl("CoinTrack");

    /**
     Получает список всех валют.
     @return список всех валют
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Currency> findAll() throws SQLException {
        return currencyRepository.findAll();
    }

    /**
     Находит валюту по указанному идентификатору.
     @param id идентификатор валюты
     @return валюта, если найдена, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Currency> findOneById(int id) throws SQLException {
        return currencyRepository.findOneById(id);
    }

    /**
     Находит валюты с указанным названием.
     @param name название валюты
     @return список валют с указанным названием
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Currency> findByName(String name) throws SQLException {
        return currencyRepository.findByName(name);
    }

    /**
     Находит валюты с указанным идентификатором страны.
     @param countryId идентификатор страны
     @return список валют с указанным идентификатором страны
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Currency> findByCountryId(int countryId) throws SQLException {
        return currencyRepository.findByCountryId(countryId);
    }

    /**
     Добавляет связь между валютой и страной.
     @param currencyId идентификатор валюты
     @param countryId идентификатор страны
     @return количество добавленных связей (обычно 1)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int addRelationCountry(int currencyId, int countryId) throws SQLException {
        return currencyRepository.addRelationCountry(currencyId, countryId);
    }

    /**
     Удаляет связь между валютой и страной.
     @param currencyId идентификатор валюты
     @param countryId идентификатор страны
     @return количество удаленных связей (обычно 1)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int removeRelationCountry(int currencyId, int countryId) throws SQLException {
        return currencyRepository.removeRelationCountry(currencyId, countryId);
    }


    /**
     Создает новую валюту с указанным названием.
     @param name название валюты
     @return количество созданных валют (обычно 1, если валюта успешо создалась)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(String name) throws SQLException {
        return currencyRepository.create(new Currency(name));
    }

    /**
     Обновляет валюту с указанным идентификатором новым названием.
     @param id идентификатор валюты, которую нужно обновить
     @param name новое название валюты
     @return количество обновленных валют (обычно 1, если валюта с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, String name) throws SQLException {
        return currencyRepository.edit(new Currency(id, name));
    }

    /**
     Удаляет валюту с указанным идентификатором.
     @param id идентификатор валюты, которую нужно удалить
     @return количество удаленных валют (обычно 1, если валюта с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        return currencyRepository.delete(id);
    }
}
