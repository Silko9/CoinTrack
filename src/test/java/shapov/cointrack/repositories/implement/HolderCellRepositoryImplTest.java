package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Currency;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.CurrencyRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HolderCellRepositoryImplTest {
    static HolderCellRepositoryImpl holderCellRepository = new HolderCellRepositoryImpl();
    static DatabaseHelper databaseHelper = new HolderCellRepositoryImpl();
    HolderCell holderCell1 = new HolderCell(1, false, 1, 1, 1, "5 руб 1999");
    HolderCell holderCell2 = new HolderCell(1, false, 1, 1, 1, "5 руб 1999");
    HolderCell holderCell3 = new HolderCell(1, false, 2, 1, 1, "5 руб 1999");
    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`HolderCell` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `coinId` INT NOT NULL," +
                "  `pageId` INT NOT NULL," +
                "  `columnHolder` INT NOT NULL," +
                "  `lineHolder` INT NOT NULL," +
                "  `title` VARCHAR(15) NOT NULL," +
                "  `available` TINYINT NOT NULL," +
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
        assertEquals(1, holderCellRepository.create(holderCell1));
        assertEquals(1, holderCellRepository.create(holderCell2));
        assertEquals(1, holderCellRepository.create(holderCell3));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<HolderCell> holderCells = holderCellRepository.findAll();
        assertEquals(3, holderCells.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<HolderCell> holderCell = holderCellRepository.findOneById(1);
        assert(holderCell.isPresent());
        holderCell = holderCellRepository.findOneById(2);
        assert(holderCell.isPresent());
        holderCell = holderCellRepository.findOneById(3);
        assert(holderCell.isPresent());
        holderCell = holderCellRepository.findOneById(-1);
        assert(holderCell.isEmpty());
    }

    @Test
    @Order(2)
    void findByPageId() throws SQLException {
        List<HolderCell> holderCells = holderCellRepository.findByPageId(1);
        assertEquals(2, holderCells.size());
        holderCells = holderCellRepository.findByPageId(2);
        assertEquals(1, holderCells.size());
        holderCells = holderCellRepository.findByPageId(-1);
        assertEquals(0, holderCells.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<HolderCell> holderCell = holderCellRepository.findOneById(1);
        assert(holderCell.isPresent());
        holderCell.get().setPageId(3);
        assertEquals(1, holderCellRepository.edit(holderCell.get()));
        List<HolderCell> holderCells = holderCellRepository.findByPageId(3);
        assertEquals(1, holderCells.size());
        holderCell.get().setId(-1);
        assertEquals(0, holderCellRepository.edit(holderCell.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, holderCellRepository.delete(1));
        List<HolderCell> holderCells = holderCellRepository.findAll();
        assertEquals(2, holderCells.size());

        assertEquals(1, holderCellRepository.delete(2));
        holderCells = holderCellRepository.findAll();
        assertEquals(1, holderCells.size());

        assertEquals(1, holderCellRepository.delete(3));
        holderCells = holderCellRepository.findAll();
        assertEquals(0, holderCells.size());

        assertEquals(0, holderCellRepository.delete(-1));
    }
}