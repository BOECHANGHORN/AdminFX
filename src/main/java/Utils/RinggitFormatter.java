package Utils;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;

/**
 * <h1>RinggitFormatter</h1>
 * The RinggitFormatter class is a class that store data fields
 * of RinggitFormatter
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class RinggitFormatter {
    private final TextFormatter<Double> textFormatter;

    /**
     * Instantiates a new RinggitFormatter.
     */
    public RinggitFormatter() {
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

    /**
     * Gets text formatter
     *
     * @return the TextFormatter object
     */
    public TextFormatter<Double> getInstance() {

        return this.textFormatter;
    }
}
