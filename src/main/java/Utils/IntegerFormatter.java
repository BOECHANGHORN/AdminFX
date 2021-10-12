package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * <h1>IntegerFormatter</h1>
 * The IntegerFormatter class is a class that store data fields
 * of IntegerFormatter
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class IntegerFormatter {
    private final TextFormatter<Integer> textFormatter;

    /**
     * Instantiates a new IntegerFormatter.
     */
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

    /**
     * Gets text formatter
     *
     * @return the TextFormatter object
     */
    public TextFormatter<Integer> getInstance() {

        return this.textFormatter;
    }
}
