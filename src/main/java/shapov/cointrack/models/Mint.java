package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mint {
    private int id;
    private String name;
    private int countryId;

    public Mint(String name, int countryId) {
        this.name = name;
        this.countryId = countryId;
    }
}
