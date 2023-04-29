package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolderCell {
    private int id;
    private int coinId;
    private boolean available;
    private int pageId;
    private int column;
    private int line;

    public HolderCell(int coinId, boolean available, int pageId, int column, int line) {
        this.coinId = coinId;
        this.available = available;
        this.pageId = pageId;
        this.column = column;
        this.line = line;
    }
}
