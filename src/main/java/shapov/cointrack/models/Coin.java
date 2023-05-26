package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    private int id;
    private int denomination;
    private int currencyId;
    private int countryId;
    private int mintId;
    private int yearMinting;
    private String picturePath;

    public Coin(int denomination, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) {
        this.denomination = denomination;
        this.currencyId = currencyId;
        this.countryId = countryId;
        this.mintId = mintId;
        this.yearMinting = yearMinting;
        this.picturePath = picturePath;
    }
}