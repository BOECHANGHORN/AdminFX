package controllers;

import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.app.main.Main.switchScene;

public class AddMenuController {

    @FXML
    private void addRoleClicked(MouseEvent mouseEvent) throws IOException {
        switchScene("AddRole.fxml");
    }

    @FXML
    private void addPropertyClicked(MouseEvent mouseEvent) throws IOException {
        switchScene("AddProperty.fxml");
    }

    @FXML
    private void onClickHomeBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToViewBoardPage();
    }

    @FXML
    private void onClickAddBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToAddMenuPage();
    }

    @FXML
    private void onClickProfileBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToEditProfilePage();
    }

    @FXML
    private void onLogout(MouseEvent mouseEvent) throws IOException {
        Main.goToLoginPage();
    }
}
