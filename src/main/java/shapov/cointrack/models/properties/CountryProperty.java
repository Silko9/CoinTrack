package shapov.cointrack.models.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shapov.cointrack.models.Country;

public class CountryProperty {
    private final IntegerProperty id;
    private final StringProperty name;
    public CountryProperty(Country country){
        this.id = new SimpleIntegerProperty(country.getId());
        this.name = new SimpleStringProperty(country.getName());
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
}
