package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Country;
import shapov.cointrack.models.Currency;
import shapov.cointrack.repositories.CountryRepository;
import shapov.cointrack.repositories.CurrencyRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CurrencyRepositoryImplTest {
    static CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();
    static DatabaseHelper databaseHelper = new CurrencyRepositoryImpl();
    Currency currency1 = new Currency("Долар");
    Currency currency2 = new Currency("Юани");
    Currency currency3 = new Currency("Рубль");
    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update(" CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`Currency` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(30) NOT NULL," +
                "  PRIMARY KEY (`id`));");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`RelationCountryCurrency` (" +
                " `countryId` INT NOT NULL," +
                " `currencyId` INT NOT NULL," +
                " PRIMARY KEY (`countryId`, `currencyId`));");
        databaseHelper.connectionClose();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        databaseHelper.update("DROP DATABASE CoinTrackTest;");
        databaseHelper.connectionClose();
    }

    @Test
    @Order(1)
    void create() throws SQLException {
        assertEquals(1, currencyRepository.create(currency1));
        assertEquals(1, currencyRepository.create(currency2));
        assertEquals(1, currencyRepository.create(currency3));
    }

    @Test
    @Order(1)
    void addRelationCountry() throws SQLException {
        assertEquals(1, currencyRepository.addRelationCountry(1, 1));
        assertEquals(1, currencyRepository.addRelationCountry(2, 2));
        assertEquals(1, currencyRepository.addRelationCountry(3, 2));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<Currency> currencies = currencyRepository.findAll();
        assertEquals(3, currencies.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<Currency> currency = currencyRepository.findOneById(1);
        assert(currency.isPresent());
        currency = currencyRepository.findOneById(2);
        assert(currency.isPresent());
        currency = currencyRepository.findOneById(3);
        assert(currency.isPresent());
        currency = currencyRepository.findOneById(-1);
        assert(currency.isEmpty());
    }

    @Test
    @Order(2)
    void findByName() throws SQLException {
        List<Currency> currencies = currencyRepository.findByName("Долар");
        assertEquals(1, currencies.size());
        currencies = currencyRepository.findByName("Юани");
        assertEquals(1, currencies.size());
        currencies = currencyRepository.findByName("Рубль");
        assertEquals(1, currencies.size());
        currencies = currencyRepository.findByName("Цент");
        assertEquals(0, currencies.size());
    }

    @Test
    @Order(2)
    void findByCountryId() throws SQLException {
        List<Currency> currencies = currencyRepository.findByCountryId(1);
        assertEquals(1, currencies.size());
        currencies = currencyRepository.findByCountryId(2);
        assertEquals(2, currencies.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<Currency> currency = currencyRepository.findOneById(1);
        assert(currency.isPresent());
        currency.get().setName("Копейка");
        assertEquals(1, currencyRepository.edit(currency.get()));
        List<Currency> currencies = currencyRepository.findByName("Копейка");
        assertEquals(1, currencies.size());
        currency.get().setId(-1);
        assertEquals(0, currencyRepository.edit(currency.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, currencyRepository.delete(1));
        List<Currency> currencies = currencyRepository.findAll();
        assertEquals(2, currencies.size());

        assertEquals(1, currencyRepository.delete(2));
        currencies = currencyRepository.findAll();
        assertEquals(1, currencies.size());

        assertEquals(1, currencyRepository.delete(3));
        currencies = currencyRepository.findAll();
        assertEquals(0, currencies.size());

        assertEquals(0, currencyRepository.delete(-1));
    }

    @Test
    @Order(4)
    void removeRelationCountry() throws SQLException {
        assertEquals(1, currencyRepository.removeRelationCountry(1, 1));
        assertEquals(1, currencyRepository.removeRelationCountry(2, 2));
        assertEquals(1, currencyRepository.removeRelationCountry(3, 2));
    }
}