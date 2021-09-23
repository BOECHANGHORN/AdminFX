package Utils;

import Property.PropertyType;
import com.app.main.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class Utils {
    public static final String[] STATES = {"Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Sabah", "Sarawak", "Selangor", "Terengganu"};
    public static final String CURRENCY = "RM";
    public static final String SIZE_UNIT = "sqft";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";


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

    public static String getYesOrNo(boolean isExist) {
        if (isExist) {
            return YES;
        } else {
            return NO;
        }
    }

    public static void showAlert(String msg,boolean success){
        // Success Alert
        Alert alert = new Alert(Alert.AlertType.NONE,msg, ButtonType.CLOSE);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                Main.class.getResource("dialog.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        if(success){
            dialogPane.getStyleClass().add("success");
        }else{
            dialogPane.getStyleClass().add("error");
        }
        alert.show();
    }

}
