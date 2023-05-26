package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Mint;
import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.MintRepository;
import shapov.cointrack.repositories.PageRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PageRepositoryImplTest {
    static PageRepository pageRepository = new PageRepositoryImpl();
    static DatabaseHelper databaseHelper = new PageRepositoryImpl();
    Page page1 = new Page(1, 1, "рубли");
    Page page2 = new Page(1, 2, "копейки");
    Page page3 = new Page(2, 1, "рубли");

    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`Page` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `title` VARCHAR(20) NOT NULL," +
                "  `albumId` INT NOT NULL," +
                "  `number` INT NOT NULL," +
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
        assertEquals(1, pageRepository.create(page1));
        assertEquals(1, pageRepository.create(page2));
        assertEquals(1, pageRepository.create(page3));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<Page> pages = pageRepository.findAll();
        assertEquals(3, pages.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<Page> page = pageRepository.findOneById(1);
        assert(page.isPresent());
        page = pageRepository.findOneById(2);
        assert(page.isPresent());
        page = pageRepository.findOneById(3);
        assert(page.isPresent());
        page = pageRepository.findOneById(-1);
        assert(page.isEmpty());
    }

    @Test
    @Order(2)
    void findByAlbumId() throws SQLException {
        List<Page> pages = pageRepository.findByAlbumId(1);
        assertEquals(2, pages.size());
        pages = pageRepository.findByAlbumId(2);
        assertEquals(1, pages.size());
        pages = pageRepository.findByAlbumId(-1);
        assertEquals(0, pages.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<Page> page = pageRepository.findOneById(1);
        assert(page.isPresent());
        page.get().setAlbumId(3);
        assertEquals(1, pageRepository.edit(page.get()));
        List<Page> pages = pageRepository.findByAlbumId(3);
        assertEquals(1, pages.size());
        page.get().setId(-1);
        assertEquals(0, pageRepository.edit(page.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, pageRepository.delete(1));
        List<Page> pages = pageRepository.findAll();
        assertEquals(2, pages.size());

        assertEquals(1, pageRepository.delete(2));
        pages = pageRepository.findAll();
        assertEquals(1, pages.size());

        assertEquals(1, pageRepository.delete(3));
        pages = pageRepository.findAll();
        assertEquals(0, pages.size());

        assertEquals(0, pageRepository.delete(-1));
    }
}