package shapov.cointrack.repositories.implement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Currency;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.HolderCellRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class HolderCellRepositoryImpl extends DatabaseHelper implements HolderCellRepository {
    private final static String TABLE_NAME = "HolderCell";
    private String nameDB = "CoinTrackTest";
    private String getFullTableName(){
        return nameDB + "." + TABLE_NAME;
    }

    @Override
    public List<HolderCell> findAll() throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_ALL, getFullTableName())));
    }

    @Override
    public Optional<HolderCell> findOneById(int id) throws SQLException {
        List<HolderCell> holderCells = mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), "id=" + id)));
        if (holderCells.size() > 0)
            return Optional.of(holderCells.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<HolderCell> findByPageId(int pageId) throws SQLException {
        return mapper(query(String.format(DatabaseQueryConst.SELECT_WHERE, getFullTableName(), getParameters(pageId))));
    }

    @Override
    public int create(HolderCell holderCell) throws SQLException {
        return update(String.format(DatabaseQueryConst.INSERT, getFullTableName(), getParameters(holderCell)));
    }

    @Override
    public int edit(HolderCell holderCell) throws SQLException {
        return update(String.format(DatabaseQueryConst.UPDATE, getFullTableName(), getParameters(holderCell), holderCell.getId()));
    }

    @Override
    public int delete(int id) throws SQLException {
        return update(String.format(DatabaseQueryConst.DELETE, getFullTableName(), id));
    }

    private List<HolderCell> mapper(ResultSet resultSet) throws SQLException {
        List<HolderCell> holderCells = new ArrayList<>();
        HolderCell holderCell;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int coinId = resultSet.getInt("coinId");
            boolean available = resultSet.getBoolean("available");
            int pageId = resultSet.getInt("pageId");
            int column = resultSet.getInt("columnHolder");
            int line = resultSet.getInt("lineHolder");
            String title = resultSet.getString("title");
            holderCell = new HolderCell(id, coinId, available, pageId, column, line, title);
            holderCells.add(holderCell);
        }
        connectionClose();
        return holderCells;
    }

    private String getParameters(HolderCell holderCell){
        int available = 0;
        if (holderCell.isAvailable()) available = 1;
        return "coinId=" + holderCell.getCoinId() +
                ", available=" + available +
                ", pageId=" + holderCell.getPageId() +
                ", columnHolder=" + holderCell.getColumn() +
                ", lineHolder=" + holderCell.getLine() +
                ", title='" + holderCell.getTitle() + "' ";
    }

    private String getParameters(int pageId){
        return "pageId=" + pageId;
    }
}
