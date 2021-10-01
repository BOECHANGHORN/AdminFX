package controllers;

import Admin.AdminDatabase;
import AppHolder.AppHolder;
import Role.Role;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label msg;

    @FXML
    private void initialize() {
        username.setFocusTraversable(true);
        password.setFocusTraversable(false);
    }

    @FXML
    private void onLogin(MouseEvent mouseEvent) throws IOException {

        Role adminUser = AdminDatabase.getInstance().searchUser(username.getText()); //try get from AdminDB

        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            msg.setText("Please enter your credentials.");
        } else if ((adminUser != null) && adminUser.getPassword().equals(password.getText())) {
            AppHolder holder = AppHolder.getInstance();
            holder.setUser(adminUser);

            Main.switchScene("ViewBoard.fxml");
        } else {
            msg.setText("Invalid credentials!");
        }

    }
}
