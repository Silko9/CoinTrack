package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coin {
    private int id;
    private int denomination;
    private int currencyId;
    private Currency currency;
    private int countryId;
    private Country country;
    private int mintId;
    private Mint mint;
    private int yearMinting;
    private String picturePath;

    public Coin(int denomination, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) {
        this(0, denomination, currencyId, countryId, mintId, yearMinting, picturePath);
    }
    public Coin(int id, int denomination, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) {
        this.denomination = denomination;
        this.currencyId = currencyId;
        this.countryId = countryId;
        this.mintId = mintId;
        this.yearMinting = yearMinting;
        this.picturePath = picturePath;
    }
}