package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * <h1>PhoneFormatter</h1>
 * The PhoneFormatter class is a class that store data fields
 * of PhoneFormatter
 *
 * @author Chan Yun Hong
 * @version 1.0
 * @since 2021-10-11
 */
public class PhoneFormatter {
    private final TextFormatter<String> textFormatter;

    public PhoneFormatter() {
        UnaryOperator<TextFormatter.Change> integerValidationFormatter = change -> {
            if (change.getControlNewText().matches("\\+?\\d*")) {
                return change;
            } else {
                return null;
            }
        };

        this.textFormatter = new TextFormatter<>(integerValidationFormatter);
    }

    /**
     * Gets text formatter
     * @return the TextFormatter object
     */
    public TextFormatter<String> getInstance() {

        return this.textFormatter;
    }
}
