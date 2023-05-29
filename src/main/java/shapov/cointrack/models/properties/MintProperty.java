package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Mint;

public class MintProperty {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty countryId;

    public MintProperty(Mint mint) {
        this.id = new SimpleIntegerProperty(mint.getId());
        this.name = new SimpleStringProperty(mint.getName());
        this.countryId = new SimpleIntegerProperty(mint.getCountryId());
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCountryId() {
        return countryId.get();
    }

    public IntegerProperty countryIdProperty() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId.set(countryId);
    }
}

