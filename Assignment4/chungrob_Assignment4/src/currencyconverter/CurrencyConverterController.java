package currencyconverter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author karen
 */
public class CurrencyConverterController implements Initializable {

    @FXML
    private ToggleGroup grpCurrency;
    @FXML
    private TextField txtCdnAmount;
    @FXML
    private RadioButton radUS;
    @FXML
    private RadioButton radUK;
    @FXML
    private RadioButton radEuro;
    @FXML
    private RadioButton radRupee;
    @FXML
    private RadioButton radYuan;
    @FXML
    private RadioButton radPesos;
    @FXML
    private Label lblExchangeRate;
    @FXML
    private Button btnConvert;
    @FXML
    private Label lblAmount;
    @FXML
    private ImageView imgMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                double value = 0;
                double exchangeRate = 0;
                String currency = "";
                char symbol = ' ';
                boolean isValid = true;
                RadioButton currencyRb = (RadioButton) grpCurrency.getSelectedToggle();

                // must only contain digits
                if (!txtCdnAmount.getText().matches("\\b\\d+\\b")) {
                    lblAmount.setText("Error! Must be a valid number!");
                    lblExchangeRate.setText("");
                    isValid = false;
                }

                // empty text field
                if (txtCdnAmount.getText().isEmpty()) {
                    lblAmount.setText("Must enter a number!");
                    isValid = false;
                }

//                // if contains any amount of letters
//                if (txtCdnAmount.getText().matches("[A-Za-z]+")) {
//                    lblAmount.setText("Error! Must be a number!");
//                    isValid = false;
//                }

                // if negative number
                if (txtCdnAmount.getText().matches("-\\d+")) {
                    lblAmount.setText("Error! Must be a positive number!");
                    isValid = false;
                }

                value = Double.parseDouble(txtCdnAmount.getText());

                // if radio button selected, then :
                if (currencyRb != null) {
                    if (radUS.isSelected()) {
                        exchangeRate = 0.80;
                        value *= exchangeRate;
                        currency = "USD";
                        symbol = '\u0024';
                    } else if (radUK.isSelected()) {
                        exchangeRate = 0.52;
                        value *= exchangeRate;
                        currency = "GBP";
                        symbol = '\u00A3';
                    } else if (radEuro.isSelected()) {
                        exchangeRate = 0.70;
                        value *= exchangeRate;
                        currency = "EUR";
                        symbol = '\u20AC';
                    } else if (radRupee.isSelected()) {
                        exchangeRate = 49.77;
                        value *= exchangeRate;
                        currency = "INR";
                        symbol = '\u20B9';
                    } else if (radYuan.isSelected()) {
                        exchangeRate = 5.01;
                        value *= exchangeRate;
                        currency = "CNY";
                        symbol = '\u00A5';
                    } else if (radPesos.isSelected()) {
                        exchangeRate = 12.01;
                        value *= exchangeRate;
                        currency = "MXN";
                        symbol = '\u0024';
                    }
                } else {
                    lblAmount.setText("Must select a currency!");
                    isValid = false;
                }

                if (isValid) {
                    lblExchangeRate.setText(String.format("1 CAD = %.2f %s", exchangeRate, currency));
                    lblAmount.setText(String.format("%c%.2f", symbol, value));
                }
            }
        });
    }

}
