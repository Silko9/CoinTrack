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
    private final CoinRepository coinRepository = new CoinRepositoryImpl("CoinTrack");

    /**
     * Репозиторий для работы с странами
     */
    private final CountryRepository countryRepository = new CountryRepositoryImpl("CoinTrack");

    /**
     * Репозиторий для работы с валютами
     */
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl("CoinTrack");

    /**
     * Репозиторий для работы с монетными дворами
     */
    private final MintRepository mintRepository = new MintRepositoryImpl("CoinTrack");

    /**
     * Получает список всех монет.
     *
     * @return список всех монет
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findAll() throws SQLException {
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
    public Optional<Coin> findOneById(int id) throws SQLException {
        return coinRepository.findOneById(id);
    }

    /**
     * Находит монеты с указанным номиналом.
     *
     * @param denomination номинал монеты
     * @return список монет с указанным номиналом
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findByDenomination(int denomination) throws SQLException {
        return coinRepository.findByDenomination(denomination);
    }

    /**
     * Находит монеты с указанным идентификатором валюты.
     *
     * @param currencyId идентификатор валюты
     * @return список монет с указанным идентификатором валюты
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findByCurrencyId(int currencyId) throws SQLException {
        return coinRepository.findByCurrencyId(currencyId);
    }

    /**
     * Находит монеты с указанным идентификатором страны.
     *
     * @param countryId идентификатор страны
     * @return список монет с указанным идентификатором страны
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findByCountryId(int countryId) throws SQLException {
        return coinRepository.findByCountryId(countryId);
    }

    /**
     * Находит монеты с указанным идентификатором монетного двора.
     *
     * @param mintId идентификатор монетного двора
     * @return список монет с указанным идентификатором монетного двора
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findByMintId(int mintId) throws SQLException {
        return coinRepository.findByMintId(mintId);
    }

    /**
     * Находит монеты с указанным годом чеканки.
     *
     * @param yearMinting год чеканки монет
     * @return список монет с указанным годом чеканки
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Coin> findByDateMinting(int yearMinting) throws SQLException {
        return coinRepository.findByDateMinting(yearMinting);
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
     * @return количество созданных монет (обычно 1, если монета успешно создалась)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException {
        return coinRepository.create(new Coin(denominationId, currencyId, countryId, mintId, yearMinting, picturePath));
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
     * @return количество обновленных монет (обычно 1, если монета с указанным идентификатором существует)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, int denominationId, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) throws SQLException {
        return coinRepository.edit(new Coin(id, denominationId, currencyId, countryId, mintId, yearMinting, picturePath));
    }

    /**
     * Удаляет монету с указанным идентификатором.
     *
     * @param id идентификатор монеты, которую нужно удалить
     * @return количество удаленных монет (обычно 1, если монета с указанным идентификатором существует)
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        return coinRepository.delete(id);
    }

    /**
     * Включает информацию о стране, валюте и монетном дворе в указанную монету.
     * @param coin монета, которую нужно дополнить информацией
     * @return монета с добавленной информацией о стране, валюте и монетном дворе, либо null, если одна из сущностей не найдена
     * @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Coin include(Coin coin) throws SQLException {
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
