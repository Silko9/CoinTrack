package shapov.cointrack.repositories.implement;

import org.junit.jupiter.api.*;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.models.Album;
import shapov.cointrack.models.Coin;
import shapov.cointrack.repositories.AlbumRepository;
import shapov.cointrack.repositories.CoinRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlbumRepositoryImplTest {
    static AlbumRepository albumRepository = new AlbumRepositoryImpl();
    static DatabaseHelper databaseHelper = new AlbumRepositoryImpl();
    Album album1 = new Album("Рубли");
    Album album2 = new Album("Рубли");
    Album album3 = new Album("Копейки");
    @BeforeAll
    static void setUp() throws SQLException {
        databaseHelper.update("CREATE DATABASE IF NOT EXISTS `CoinTrackTest`;");
        databaseHelper.update("USE `CoinTrackTest` ;");
        databaseHelper.update("CREATE TABLE IF NOT EXISTS `CoinTrackTest`.`Album` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT, " +
                "  `title` VARCHAR(25) NOT NULL, " +
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
        assertEquals(1, albumRepository.create(album1));
        assertEquals(1, albumRepository.create(album2));
        assertEquals(1, albumRepository.create(album3));
    }

    @Test
    @Order(2)
    void findAll() throws SQLException {
        List<Album> albums = albumRepository.findAll();
        assertEquals(3, albums.size());
    }

    @Test
    @Order(2)
    void findOneById() throws SQLException {
        Optional<Album> album = albumRepository.findOneById(1);
        assert(album.isPresent());
        album = albumRepository.findOneById(2);
        assert(album.isPresent());
        album = albumRepository.findOneById(3);
        assert(album.isPresent());
        album = albumRepository.findOneById(-1);
        assert(album.isEmpty());
    }

    @Test
    @Order(2)
    void findByTitle() throws SQLException {
        List<Album> albums = albumRepository.findByTitle("Рубли");
        assertEquals(2, albums.size());
        albums = albumRepository.findByTitle("Копейки");
        assertEquals(1, albums.size());
        albums = albumRepository.findByTitle("центы");
        assertEquals(0, albums.size());
    }

    @Test
    @Order(3)
    void edit() throws SQLException {
        Optional<Album> album = albumRepository.findOneById(1);
        assert(album.isPresent());
        album.get().setTitle("Долар");
        assertEquals(1, albumRepository.edit(album.get()));
        List<Album> albums = albumRepository.findByTitle("Долар");
        assertEquals(1, albums.size());
        album.get().setId(-1);
        assertEquals(0, albumRepository.edit(album.get()));
    }

    @Test
    @Order(4)
    void delete() throws SQLException {
        assertEquals(1, albumRepository.delete(1));
        List<Album> albums = albumRepository.findAll();
        assertEquals(2, albums.size());

        assertEquals(1, albumRepository.delete(2));
        albums = albumRepository.findAll();
        assertEquals(1, albums.size());

        assertEquals(1, albumRepository.delete(3));
        albums = albumRepository.findAll();
        assertEquals(0, albums.size());

        assertEquals(0, albumRepository.delete(-1));
    }
}