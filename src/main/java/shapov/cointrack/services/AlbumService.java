package shapov.cointrack.services;

import shapov.cointrack.models.Album;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Collection<Album> findAll();

    Optional<Album> findOneById(int id);

    List<Album> findByTitle(String title);

    int create(String title);

    int update(int id, String title);

    int delete(int id);
}
