package controllers;

import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.app.main.Main.switchScene;

/**
 * <h1>AddMenuController Class</h1>
 * The AddMenuController class is a controller class that
 * lets the user choose between the AddProperty scene or
 * the AddRole scene
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class AddMenuController {

    /**
     * A private method that initializes AddRole scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void addRoleClicked(MouseEvent mouseEvent) throws IOException {
        switchScene("AddRole.fxml");
    }

    /**
     * A private method that initializes AddProperty scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void addPropertyClicked(MouseEvent mouseEvent) throws IOException {
        switchScene("AddProperty.fxml");
    }

    /**
     * A private method that initializes ViewBoard scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onClickHomeBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToViewBoardPage();
    }

    /**
     * A private method that initializes AddMenu scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onClickAddBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToAddMenuPage();
    }

    /**
     * A private method that initializes EditProfile scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onClickProfileBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToEditProfilePage();
    }

    /**
     * A private method that initializes Login scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onLogout(MouseEvent mouseEvent) throws IOException {
        Main.goToLoginPage();
    }
}
