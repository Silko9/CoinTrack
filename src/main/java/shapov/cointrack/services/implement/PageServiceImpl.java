package shapov.cointrack.services.implement;

import shapov.cointrack.models.Page;
import shapov.cointrack.repositories.PageRepository;
import shapov.cointrack.repositories.implement.PageRepositoryImpl;
import shapov.cointrack.services.PageService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository = new PageRepositoryImpl("CoinTrack");
    @Override
    public List<Page> findAll() throws SQLException {
        return pageRepository.findAll();
    }

    @Override
    public Optional<Page> findOneById(int id) throws SQLException {
        return pageRepository.findOneById(id);
    }

    @Override
    public List<Page> findByAlbumId(int albumId) throws SQLException {
        return pageRepository.findByAlbumId(albumId);
    }

    @Override
    public int create(int albumId, int number, String title) throws SQLException {
        return pageRepository.create(new Page(albumId, number, title));
    }

    @Override
    public int update(int id, int albumId, int number, String title) throws SQLException {
        return pageRepository.edit(new Page(id, albumId, number, title));
    }

    @Override
    public int delete(int id) throws SQLException {
        return pageRepository.delete(id);
    }
}
