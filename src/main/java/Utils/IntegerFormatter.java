package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class IntegerFormatter {
    private TextFormatter<Integer> textFormatter;

    public IntegerFormatter() {
        UnaryOperator<TextFormatter.Change> integerValidationFormatter = change -> {
            if (change.getControlNewText().matches("\\d+")) {
                return change;
            } else if (change.getControlNewText().equals("")){
                change.setText("0");
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
