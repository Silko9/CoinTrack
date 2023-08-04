package shapov.cointrack.controllers.editControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.Currency;
import shapov.cointrack.services.CurrencyService;
import shapov.cointrack.services.implement.CurrencyServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CoinEditorController implements Initializable {

    @FXML
    private ChoiceBox cbCurrency;
    @FXML
    private TextField tDate;
    @FXML
    private TextField tDenomination;

    @Setter
    private Stage stage;

    @Getter
    private Coin coin = new Coin();

    private CurrencyService currencyService = new CurrencyServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tDenomination.setTextFormatter(numberTextFormatter());
        tDate.setTextFormatter(numberTextFormatter());

        try {
            List<Currency> currencies = currencyService.findAll();
            for(Currency currency : currencies){
                cbCurrency.getItems().add(currency.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private TextFormatter numberTextFormatter(){
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*"))
                return change;
            return null;
        });
    }

    @FXML
    private void onClickedAccept() throws SQLException {
        boolean isNull = true;

        if(!tDenomination.getText().equals("")) {
            coin.setDenomination(Integer.parseInt(tDenomination.getText()));
            isNull = false;
        }

        if(!tDate.getText().equals("")) {
            coin.setYearMinting(Integer.parseInt(tDate.getText()));
            isNull = false;
        }

        if(!cbCurrency.getValue().equals("")){
            coin.setCurrencyId(currencyService.findByName(cbCurrency.getValue().toString()).getId());
            isNull = false;
        }

        if(isNull)
            coin = null;

        stage.close();
    }

    @FXML
    private void onClickedCancel(){
        coin = null;
        stage.close();
    }
}
