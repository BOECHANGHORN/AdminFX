package Utils;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;

public class DoubleFormatter {
    private final TextFormatter<Double> textFormatter;

    public DoubleFormatter() {
        UnaryOperator<TextFormatter.Change> doubleNumValidationFormatter = change -> {
            if (change.getControlNewText().matches("\\d+\\.\\d{0,2}")
                    || change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        };

        StringConverter<Double> converter = new StringConverter<>() {

            @Override
            public Double fromString(String s) {
                return Double.valueOf(s);
            }

            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        this.textFormatter = new TextFormatter<>(converter, 0.0, doubleNumValidationFormatter);
    }

    public TextFormatter<Double> getInstance() {

        return this.textFormatter;
    }
}