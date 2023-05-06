package shapov.cointrack.services;

import shapov.cointrack.models.Page;

import java.util.List;
import java.util.Optional;

public interface PageService {
    List<Page> findAll();

    Optional<Page> findOneById(int id);

    List<Page> findByAlbumId(int albumId);

    int create(String title, int albumId, int number);

    int update(int id, String title, int albumId, int number);

    int delete(int id);
}
