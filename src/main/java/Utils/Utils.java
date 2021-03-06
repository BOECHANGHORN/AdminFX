package Utils;

import Property.PropertyType;
import com.app.main.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

/**
 * <h1>Utils Class</h1>
 * The Utils class is a class that provide utilities methods that will be used
 * throughout the whole apps
 *
 * @author Chan Yun Hong
 * @version 1.0
 * @since 2021-10-11
 */
public class Utils {
    public static final String[] STATES = {"Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Sabah", "Sarawak", "Selangor", "Terengganu",
            "Kuala Lumpur", "Putrajaya", "Labuan"};
    public static final String CURRENCY = "RM";
    public static final String SIZE_UNIT = "sqft";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";
    public static final String HIGHEST_FIRST = "Highest First";
    public static final String LOWEST_FIRST = "Lowest First";

    /**
     * Converts the enum value of
     * PropertyType into desired text
     *
     * @param str the enum value of PropertyType
     * @return the text in string type
     */
    public static String getPropertyTypeTxt(PropertyType str) {
        switch (str) {
            case BUNGALOW:
                return "Bungalow";
            case CONDOMINIUM:
                return "Condominium";
            case TOWNHOUSE:
                return "Townhouse";
            case DOUBLESTOREY:
                return "2-storey House";
            case SINGLESTOREY:
                return "1-storey House";
            default:
                return "";
        }
    }

    /**
     * Gets the YES or NO constants value
     *
     * @param isExist the boolean value that determine either YES or NO
     * @return the text in string type
     */
    public static String getYesOrNo(boolean isExist) {
        if (isExist) {
            return YES;
        } else {
            return NO;
        }
    }

    /**
     * Show alert dialog
     *
     * @param msg, the alert message,
     * @param success, whether it is a success type or fail type for message
     * @param mouseEvent the mouse event
     */
    public static void showAlert(String msg, boolean success, MouseEvent mouseEvent) {
        // Success Alert
        Alert alert = new Alert(Alert.AlertType.NONE, msg, ButtonType.CLOSE);
        alert.setX(mouseEvent.getScreenX() - 600);
        alert.setY(mouseEvent.getScreenY() - 200);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                Main.class.getResource("dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        if (success) {
            dialogPane.getStyleClass().add("success");
        } else {
            dialogPane.getStyleClass().add("error");
        }
        alert.show();
    }

    /**
     * Show confirmation dialog
     *
     * @param msg, the alert message,
     * @param mouseEvent the mouse event
     */
    public static boolean showConfirm(String msg, MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg);
        alert.setX(mouseEvent.getScreenX() - 600);
        alert.setY(mouseEvent.getScreenY() - 200);
        Optional<ButtonType> result = alert.showAndWait();

        return (result.isPresent()) && (result.get() == ButtonType.OK);
    }

}
