package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private int id;
    private String name;

    public Currency(String name) {
        this.name = name;
    }
}
