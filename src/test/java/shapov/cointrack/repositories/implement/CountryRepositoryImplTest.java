package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryRepositoryImplTest {
    static CountryRepository countryRepository = new CountryRepositoryImpl();
    static DatabaseHelper databaseHelper = new CountryRepositoryImpl();
    Country country1 = new Country("США");
    Country country2 = new Country("Китай");
    Country country3 = new Country("РФ");
    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`Country` (" +
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
    static void tearDown() throws SQLException {
        databaseHelper.update("DROP DATABASE CoinTrackTest;");
        databaseHelper.connectionClose();
    }

    @Test
    @Order(1)
    void create() throws SQLException {
        assertEquals(1, countryRepository.create(country1));
        assertEquals(1, countryRepository.create(country2));
        assertEquals(1, countryRepository.create(country3));
    }

    @Test
    @Order(1)
    void addCurrency() throws SQLException {
        assertEquals(1, countryRepository.addRelationCurrency(1, 1));
        assertEquals(1, countryRepository.addRelationCurrency(2, 2));
        assertEquals(1, countryRepository.addRelationCurrency(3, 2));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<Country> countries = countryRepository.findAll();
        assertEquals(3, countries.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<Country> country = countryRepository.findOneById(1);
        assert(country.isPresent());
        country = countryRepository.findOneById(2);
        assert(country.isPresent());
        country = countryRepository.findOneById(3);
        assert(country.isPresent());
        country = countryRepository.findOneById(-1);
        assert(country.isEmpty());
    }

    @Test
    @Order(2)
    void findByName() throws SQLException {
        List<Country> countries = countryRepository.findByName("США");
        assertEquals(1, countries.size());
        countries = countryRepository.findByName("Китай");
        assertEquals(1, countries.size());
        countries = countryRepository.findByName("РФ");
        assertEquals(1, countries.size());
        countries = countryRepository.findByName("Япония");
        assertEquals(0, countries.size());
    }

    @Test
    @Order(2)
    void findByCurrencyId() throws SQLException {
        List<Country> countries = countryRepository.findByCurrencyId(1);
        assertEquals(1, countries.size());
        countries = countryRepository.findByCurrencyId(2);
        assertEquals(2, countries.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<Country> country = countryRepository.findOneById(1);
        assert(country.isPresent());
        country.get().setName("Германия");
        assertEquals(1, countryRepository.edit(country.get()));
        List<Country> countries = countryRepository.findByName("Германия");
        assertEquals(1, countries.size());
        country.get().setId(-1);
        assertEquals(0, countryRepository.edit(country.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, countryRepository.delete(1));
        List<Country> countries = countryRepository.findAll();
        assertEquals(2, countries.size());

        assertEquals(1, countryRepository.delete(2));
        countries = countryRepository.findAll();
        assertEquals(1, countries.size());

        assertEquals(1, countryRepository.delete(3));
        countries = countryRepository.findAll();
        assertEquals(0, countries.size());

        assertEquals(0, countryRepository.delete(-1));
    }

    @Test
    @Order(4)
    void removeCurrency() throws SQLException {
        assertEquals(1, countryRepository.removeRelationCurrency(1, 1));
        assertEquals(1, countryRepository.removeRelationCurrency(2, 2));
        assertEquals(1, countryRepository.removeRelationCurrency(3, 2));
    }
}