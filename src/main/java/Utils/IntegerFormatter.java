package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class IntegerFormatter {
    private final TextFormatter<Integer> textFormatter;

    public IntegerFormatter() {
        UnaryOperator<TextFormatter.Change> integerValidationFormatter = change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        };

        this.textFormatter = new TextFormatter<>(integerValidationFormatter);
    }

    public TextFormatter<Integer> getInstance() {

        return this.textFormatter;
    }
}