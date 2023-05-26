package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private int id;
    private String name;
    //private List<Currency> currencies;

    public Country(String name) {
        this.name = name;
    }
}
