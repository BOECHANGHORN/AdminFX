package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class PostcodeFormatter {
    private final TextFormatter<Integer> textFormatter;

    public PostcodeFormatter() {
        UnaryOperator<TextFormatter.Change> integerValidationFormatter = change -> {
            if (change.getControlNewText().matches("\\d{0,5}")) {
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