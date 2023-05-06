package shapov.cointrack.repositories;

import shapov.cointrack.models.HolderCell;
import shapov.cointrack.services.HolderCellService;

import java.util.List;
import java.util.Optional;

public interface HolderCellRepository {
    List<HolderCellService> findAll();

    Optional<HolderCell> findOneById(int id);

    List<HolderCell> findByPageId(int pageId);

    int create(HolderCell holderCell);

    int update(HolderCell holderCell);

    int delete(int id);
}
