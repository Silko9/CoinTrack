package shapov.cointrack.services.implement;

import shapov.cointrack.models.Coin;
import shapov.cointrack.models.Country;
import shapov.cointrack.models.Currency;
import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.CoinRepository;
import shapov.cointrack.repositories.CountryRepository;
import shapov.cointrack.repositories.CurrencyRepository;
import shapov.cointrack.repositories.MintRepository;
import shapov.cointrack.repositories.implement.CoinRepositoryImpl;
import shapov.cointrack.repositories.implement.CountryRepositoryImpl;
import shapov.cointrack.repositories.implement.CurrencyRepositoryImpl;
import shapov.cointrack.repositories.implement.MintRepositoryImpl;
import shapov.cointrack.services.CoinService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**

 Класс CoinServiceImpl представляет реализацию интерфейса CoinService.

 Он предоставляет методы для выполнения операций с монетами, используя слой репозитория.

 @author ShapovAA

 @version 1.0
 */
public class CoinServiceImpl implements CoinService {

    /**
     * Репозиторий для работы с монетами
     */
    private final CoinRepository coinRepository = new CoinRepositoryImpl();

    /**
     * Репозиторий для работы с странами
     */
    private final CountryRepository countryRepository = new CountryRepositoryImpl();

    /**
     * Репозиторий для работы с валютами
     */
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    /**
     * Репозиторий для работы с монетными дворами
     */
    private final MintRepository mintRepository = new MintRepositoryImpl();

    /**
     * Получает список всех монет.
     *
     * @return список всех монет
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findAll() throws SQLException, IOException {
        return coinRepository.findAll();
    }

    /**
     * Находит монету по указанному идентификатору.
     *
     * @param id идентификатор монеты
     * @return монета, если найдена, в противном случае пустое значение Optional
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<Coin> findOneById(int id) throws SQLException, IOException {
        return coinRepository.findOneById(id);
    }

    /**
     * Создает новую монету с указанными параметрами.
     *
     * @param denominationId идентификатор номинала
     * @param currencyId     идентификатор валюты
     * @param countryId      идентификатор страны
     * @param mintId         идентификатор монетного двора
     * @param yearMinting    год чеканки
     * @param picturePath    путь к изображению монеты
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void create(int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException, IOException {
        coinRepository.create(new Coin(denominationId, currencyId, countryId, mintId, yearMinting, picturePath));
    }

    /**
     * Обновляет монету с указанным идентификатором новыми параметрами.
     *
     * @param id             идентификатор монеты, которую нужно обновить
     * @param denominationId идентификатор номинала
     * @param currencyId     идентификатор валюты
     * @param countryId      идентификатор страны
     * @param mintId         идентификатор монетного двора
     * @param yearMinting    год чеканки
     * @param picturePath    путь к изображению монеты
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void update(int id, int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException, IOException {
        coinRepository.edit(new Coin(id, denominationId, currencyId, countryId, mintId, yearMinting, picturePath));
    }

    /**
     * Удаляет монету с указанным идентификатором.
     *
     * @param id идентификатор монеты, которую нужно удалить
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        coinRepository.delete(id);
    }

    /**
     * Включает информацию о стране, валюте и монетном дворе в указанную монету.
     * @param coin монета, которую нужно дополнить информацией
     * @return монета с добавленной информацией о стране, валюте и монетном дворе, либо null, если одна из сущностей не найдена
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Coin include(Coin coin) throws SQLException, IOException {
        Optional<Country> country = countryRepository.findOneById(coin.getCountryId());
        Optional<Currency> currency = currencyRepository.findOneById(coin.getCurrencyId());
        Optional<Mint> mint = mintRepository.findOneById(coin.getMintId());

        if (country.isEmpty() || currency.isEmpty() || mint.isEmpty()) return null;

        coin.setCountry(country.get());
        coin.setCurrency(currency.get());
        coin.setMint(mint.get());

        return coin;
    }
}
