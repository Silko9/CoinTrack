package shapov.cointrack.services.implement;

import shapov.cointrack.repositories.CurrencyRepository;
import shapov.cointrack.repositories.implement.CurrencyRepositoryImpl;
import shapov.cointrack.services.CurrencyService;
import shapov.cointrack.models.Currency;


import java.io.IOException;
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
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    /**
     Получает список всех валют.
     @return список всех валют
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Currency> findAll() throws SQLException, IOException {
        return currencyRepository.findAll();
    }

    /**
     Находит валюту по указанному идентификатору.
     @param id идентификатор валюты
     @return валюта, если найдена, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Currency> findOneById(int id) throws SQLException, IOException {
        return currencyRepository.findOneById(id);
    }

    /**
     * Создает новую валюту с указанным названием.
     *
     * @param name название валюты
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void create(String name) throws SQLException, IOException {
        currencyRepository.create(new Currency(name));
    }

    /**
     * Обновляет валюту с указанным идентификатором новым названием.
     *
     * @param id   идентификатор валюты, которую нужно обновить
     * @param name новое название валюты
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void update(int id, String name) throws SQLException, IOException {
        currencyRepository.edit(new Currency(id, name));
    }

    /**
     * Удаляет валюту с указанным идентификатором.
     *
     * @param id идентификатор валюты, которую нужно удалить
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        currencyRepository.delete(id);
    }
}
