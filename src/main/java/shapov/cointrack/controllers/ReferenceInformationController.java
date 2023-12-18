package shapov.cointrack.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import shapov.cointrack.models.Country;
import shapov.cointrack.models.Currency;
import shapov.cointrack.models.Mint;
import shapov.cointrack.models.properties.CountryProperty;
import shapov.cointrack.models.properties.CurrencyProperty;
import shapov.cointrack.models.properties.MintProperty;
import shapov.cointrack.services.CountryService;
import shapov.cointrack.services.CurrencyService;
import shapov.cointrack.services.MintService;
import shapov.cointrack.services.implement.CountryServiceImpl;
import shapov.cointrack.services.implement.CurrencyServiceImpl;
import shapov.cointrack.services.implement.MintServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReferenceInformationController implements Initializable {
    private final CountryService countryService = new CountryServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final MintService mintService = new MintServiceImpl();
    ObservableList<CountryProperty> countries;
    ObservableList<CurrencyProperty> currencies;
    ObservableList<MintProperty> mints;
    @FXML
    private TableColumn<CountryProperty, String> columnCountry;

    @FXML
    private TableColumn<CurrencyProperty, String> columnCurrency;

    @FXML
    private TableColumn<MintProperty, String> columnMint;

    @FXML
    private TableView<CountryProperty> tableCountry;

    @FXML
    private TableView<CurrencyProperty> tableCurrency;

    @FXML
    private TableView<MintProperty> tableMint;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countries = country2property(countryService.findAll());
            currencies = currency2property(currencyService.findAll());
            mints = mint2property(mintService.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        columnCountry.setCellValueFactory(countryStringCellDataFeatures -> countryStringCellDataFeatures.getValue().nameProperty());
        columnCurrency.setCellValueFactory(currencyStringCellDataFeatures -> currencyStringCellDataFeatures.getValue().nameProperty());
        columnMint.setCellValueFactory(mintStringCellDataFeatures -> mintStringCellDataFeatures.getValue().nameProperty());

        tableCountry.setItems(countries);
        tableCurrency.setItems(currencies);
        tableMint.setItems(mints);
    }

    private ObservableList<CountryProperty> country2property(List<Country> countries) {
        ObservableList<CountryProperty> countriesProperty = FXCollections.observableArrayList();
        for(Country country : countries)
            countriesProperty.add(new CountryProperty(country));
        return countriesProperty;
    }

    private ObservableList<CurrencyProperty> currency2property(List<Currency> currencies) {
        ObservableList<CurrencyProperty> currencyProperties = FXCollections.observableArrayList();
        for(Currency currency : currencies)
            currencyProperties.add(new CurrencyProperty(currency));
        return currencyProperties;
    }

    private ObservableList<MintProperty> mint2property(List<Mint> mints){
        ObservableList<MintProperty> mintProperties = FXCollections.observableArrayList();
        for(Mint mint : mints)
            mintProperties.add(new MintProperty(mint));
        return mintProperties;
    }
}
