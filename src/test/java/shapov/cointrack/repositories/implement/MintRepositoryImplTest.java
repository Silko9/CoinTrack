package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.models.Mint;
import shapov.cointrack.repositories.MintRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MintRepositoryImplTest {
    static MintRepository mintRepository = new MintRepositoryImpl();
    static DatabaseHelper databaseHelper = new MintRepositoryImpl();
    Mint mint1 = new Mint("Московский монетный двор", 1);
    Mint mint2 = new Mint("Санкт петербургский монетный двор", 1);
    Mint mint3 = new Mint("Норвежский монетный двор", 2);
    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`Mint` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(50) NOT NULL," +
                "  `countryId` INT NOT NULL," +
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
        assertEquals(1, mintRepository.create(mint1));
        assertEquals(1, mintRepository.create(mint2));
        assertEquals(1, mintRepository.create(mint3));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<Mint> mints = mintRepository.findAll();
        assertEquals(3, mints.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<Mint> mint = mintRepository.findOneById(1);
        assert(mint.isPresent());
        mint = mintRepository.findOneById(2);
        assert(mint.isPresent());
        mint = mintRepository.findOneById(3);
        assert(mint.isPresent());
        mint = mintRepository.findOneById(-1);
        assert(mint.isEmpty());
    }

    @Test
    @Order(2)
    void findByName() throws SQLException {
        List<Mint> mints = mintRepository.findByName("Московский монетный двор");
        assertEquals(1, mints.size());
        mints = mintRepository.findByName("Санкт петербургский монетный двор");
        assertEquals(1, mints.size());
        mints = mintRepository.findByName("Норвежский монетный двор");
        assertEquals(1, mints.size());
        mints = mintRepository.findByName("----");
        assertEquals(0, mints.size());
    }

    @Test
    @Order(2)
    void findByCountryId() throws SQLException {
        List<Mint> mints = mintRepository.findByCountryId(1);
        assertEquals(2, mints.size());
        mints = mintRepository.findByCountryId(2);
        assertEquals(1, mints.size());
        mints = mintRepository.findByCountryId(-1);
        assertEquals(0, mints.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<Mint> mint = mintRepository.findOneById(1);
        assert(mint.isPresent());
        mint.get().setCountryId(3);
        assertEquals(1, mintRepository.edit(mint.get()));
        List<Mint> mints = mintRepository.findByCountryId(3);
        assertEquals(1, mints.size());
        mint.get().setId(-1);
        assertEquals(0, mintRepository.edit(mint.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, mintRepository.delete(1));
        List<Mint> mints = mintRepository.findAll();
        assertEquals(2, mints.size());

        assertEquals(1, mintRepository.delete(2));
        mints = mintRepository.findAll();
        assertEquals(1, mints.size());

        assertEquals(1, mintRepository.delete(3));
        mints = mintRepository.findAll();
        assertEquals(0, mints.size());

        assertEquals(0, mintRepository.delete(-1));
    }
}