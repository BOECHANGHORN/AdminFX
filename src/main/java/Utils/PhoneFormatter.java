package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

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

    public TextFormatter<String> getInstance() {

        return this.textFormatter;
    }
}