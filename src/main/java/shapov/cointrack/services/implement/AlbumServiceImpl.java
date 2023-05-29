package shapov.cointrack.services.implement;

import shapov.cointrack.models.Album;
import shapov.cointrack.repositories.AlbumRepository;
import shapov.cointrack.repositories.implement.AlbumRepositoryImpl;
import shapov.cointrack.services.AlbumService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository = new AlbumRepositoryImpl("CoinTrack");
    @Override
    public List<Album> findAll() throws SQLException {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findOneById(int id) throws SQLException {
        return albumRepository.findOneById(id);
    }

    @Override
    public List<Album> findByTitle(String title) throws SQLException {
        return albumRepository.findByTitle(title);
    }

    @Override
    public int create(String title) throws SQLException {
        return albumRepository.create(new Album(title));
    }

    @Override
    public int update(int id, String title) throws SQLException {
        return albumRepository.edit(new Album(id, title));
    }

    @Override
    public int delete(int id) throws SQLException {
        return albumRepository.delete(id);
    }
}
