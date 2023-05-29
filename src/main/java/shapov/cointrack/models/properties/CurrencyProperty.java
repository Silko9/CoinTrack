package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Currency;

public class CurrencyProperty {
    private final IntegerProperty id;
    private final StringProperty name;

    public CurrencyProperty(Currency currency) {
        this.id = new SimpleIntegerProperty(currency.getId());
        this.name = new SimpleStringProperty(currency.getName());
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

