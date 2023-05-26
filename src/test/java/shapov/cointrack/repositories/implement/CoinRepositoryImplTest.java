package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Coin;
import shapov.cointrack.repositories.CoinRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoinRepositoryImplTest {
    static CoinRepository coinRepository = new CoinRepositoryImpl();
    static DatabaseHelper databaseHelper = new CoinRepositoryImpl();
    static Coin coin1 = new Coin(1,1,1,1,2000,"image.png");
    static Coin coin2 = new Coin(2,1,1,2,2000,"image.png");
    static Coin coin3 = new Coin(5,2,1,1,2001,"image.png");
    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`Coin` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `denomination` INT NOT NULL," +
                "  `currencyId` INT NOT NULL," +
                "  `countryId` INT NOT NULL," +
                "  `mintId` INT NOT NULL," +
                "  `yearMinting` INT NOT NULL," +
                "  `picturePath` VARCHAR(40) NOT NULL," +
                "  PRIMARY KEY (`id`));");

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
        assertEquals(1, coinRepository.create(coin1));
        assertEquals(1, coinRepository.create(coin2));
        assertEquals(1, coinRepository.create(coin3));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<Coin> coins = coinRepository.findAll();
        assertEquals(3, coins.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<Coin> coin = coinRepository.findOneById(1);
        assert(coin.isPresent());
        coin = coinRepository.findOneById(2);
        assert(coin.isPresent());
        coin = coinRepository.findOneById(3);
        assert(coin.isPresent());
        coin = coinRepository.findOneById(-1);
        assert(coin.isEmpty());
    }

    @Test
    @Order(2)
    void findByDenomination() throws SQLException {
        List<Coin> coins = coinRepository.findByDenomination(1);
        assertEquals(1, coins.size());
        coins = coinRepository.findByDenomination(2);
        assertEquals(1, coins.size());
        coins = coinRepository.findByDenomination(5);
        assertEquals(1, coins.size());
        coins = coinRepository.findByDenomination(-1);
        assertEquals(0, coins.size());
    }

    @Test
    @Order(2)
    void findByCurrencyId() throws SQLException {
        List<Coin> coins = coinRepository.findByCurrencyId(1);
        assertEquals(2, coins.size());
        coins = coinRepository.findByCurrencyId(2);
        assertEquals(1, coins.size());
        coins = coinRepository.findByCurrencyId(-1);
        assertEquals(0, coins.size());
    }

    @Test
    @Order(2)
    void findByCountryId() throws SQLException {
        List<Coin> coins = coinRepository.findByCountryId(1);
        assertEquals(3, coins.size());
        coins = coinRepository.findByCountryId(-1);
        assertEquals(0, coins.size());
    }

    @Test
    @Order(2)
    void findByMintId() throws SQLException {
        List<Coin> coins = coinRepository.findByMintId(1);
        assertEquals(2, coins.size());
        coins = coinRepository.findByMintId(2);
        assertEquals(1, coins.size());
        coins = coinRepository.findByMintId(-1);
        assertEquals(0, coins.size());
    }

    @Test
    @Order(2)
    void findByDateMinting() throws SQLException {
        List<Coin> coins = coinRepository.findByDateMinting(2000);
        assertEquals(2, coins.size());
        coins = coinRepository.findByDateMinting(2001);
        assertEquals(1, coins.size());
        coins = coinRepository.findByDateMinting(-1);
        assertEquals(0, coins.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<Coin> coin = coinRepository.findOneById(2);
        assert(coin.isPresent());
        coin.get().setDenomination(20);
        assertEquals(1, coinRepository.edit(coin.get()));
        List<Coin> coins = coinRepository.findByDenomination(20);
        assertEquals(1, coins.size());
        coin.get().setId(-1);
        assertEquals(0, coinRepository.edit(coin.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, coinRepository.delete(1));
        List<Coin> coins = coinRepository.findAll();
        assertEquals(2, coins.size());

        assertEquals(1, coinRepository.delete(2));
        coins = coinRepository.findAll();
        assertEquals(1, coins.size());

        assertEquals(1, coinRepository.delete(3));
        coins = coinRepository.findAll();
        assertEquals(0, coins.size());

        assertEquals(0, coinRepository.delete(-1));
    }
}