package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Coin;

public class CoinProperty {
    private final IntegerProperty id;
    private final IntegerProperty denomination;
    private final StringProperty currency;
    private final StringProperty country;
    private final StringProperty mint;
    private final IntegerProperty yearMinting;
    private final StringProperty picturePath;

    public CoinProperty(Coin coin) {
        this.id = new SimpleIntegerProperty(coin.getId());
        this.denomination = new SimpleIntegerProperty(coin.getDenomination());
        this.currency = new SimpleStringProperty(coin.getCurrency().getName());
        this.country = new SimpleStringProperty(coin.getCountry().getName());
        this.mint = new SimpleStringProperty(coin.getMint().getName());
        this.yearMinting = new SimpleIntegerProperty(coin.getYearMinting());
        this.picturePath = new SimpleStringProperty(coin.getPicturePath());
    }
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getDenomination() {
        return denomination.get();
    }

    public IntegerProperty denominationProperty() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination.set(denomination);
    }

    public String getCurrency() {
        return currency.get();
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getMint() {
        return mint.get();
    }

    public StringProperty mintProperty() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint.set(mint);
    }

    public int getYearMinting() {
        return yearMinting.get();
    }

    public IntegerProperty yearMintingProperty() {
        return yearMinting;
    }

    public void setYearMinting(int yearMinting) {
        this.yearMinting.set(yearMinting);
    }

    public String getPicturePath() {
        return picturePath.get();
    }

    public StringProperty picturePathProperty() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath.set(picturePath);
    }
}

