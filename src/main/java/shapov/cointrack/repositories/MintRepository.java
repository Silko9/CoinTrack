package shapov.cointrack.repositories;

import shapov.cointrack.models.Mint;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MintRepository {
    List<Mint> findAll() throws SQLException, IOException;

    Optional<Mint> findOneById(int id) throws SQLException, IOException;

    void create(Mint mint) throws SQLException, IOException;

    void edit(Mint mint) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}