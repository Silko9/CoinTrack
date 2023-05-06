package shapov.cointrack.repositories;

import shapov.cointrack.models.Page;

import java.util.List;
import java.util.Optional;

public interface PageRepository {
    List<Page> findAll();

    Optional<Page> findOneById(int id);

    List<Page> findByAlbumId(int albumId);

    int create(Page page);

    int update(Page page);

    int delete(int id);
}
