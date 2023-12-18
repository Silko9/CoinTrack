package shapov.cointrack.services;

import shapov.cointrack.models.Mint;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MintService {
    List<Mint> findAll() throws SQLException, IOException;

    Optional<Mint> findOneById(int id) throws SQLException, IOException;

    void create(String name, int countryId) throws SQLException, IOException;

    void update(int id, String name, int countryId) throws SQLException, IOException;

    void delete(int id) throws SQLException, IOException;
}

