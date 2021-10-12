package Utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * <h1>PostcodeFormatter</h1>
 * The PostcodeFormatter class is a class that store data fields
 * of PostcodeFormatter
 *
 * @author Chan Yun Hong
 * @version 1.0
 * @since 2021-10-11
 */
public class PostcodeFormatter {
    private final TextFormatter<Integer> textFormatter;

    /**
     * Instantiates a new PostcodeFormatter.
     */
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

    /**
     * Gets text formatter
     * @return the TextFormatter object
     */
    public TextFormatter<Integer> getInstance() {

        return this.textFormatter;
    }
}
