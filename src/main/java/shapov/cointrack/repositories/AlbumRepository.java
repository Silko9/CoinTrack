package shapov.cointrack.repositories;

import shapov.cointrack.models.Album;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlbumRepository {
    Collection<Album> findAll();

    Optional<Album> findOneById(int id);

    List<Album> findByTitle(String title);

    int create(Album album);

    int update(Album album);

    int delete(int id);
}
