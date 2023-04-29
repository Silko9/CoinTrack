package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int id;
    private int albumId;
    private int number;
    private String title;

    public Page(int albumId, int number, String title) {
        this.albumId = albumId;
        this.number = number;
        this.title = title;
    }
}
