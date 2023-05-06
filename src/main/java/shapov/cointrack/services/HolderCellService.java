package shapov.cointrack.services;

import shapov.cointrack.models.HolderCell;

import java.util.List;
import java.util.Optional;

public interface HolderCellService {
    List<HolderCellService> findAll();

    Optional<HolderCell> findOneById(int id);

    List<HolderCell> findByPageId(int pageId);

    int create(int coinId, int pageId, int column, int line, String title);

    int update(int id, boolean available, int coinId, int pageId, int column, int line, String title);

    int delete(int id);
}
