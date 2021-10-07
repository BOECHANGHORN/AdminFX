package controllers;

import AppHolder.AppHolder;
import Role.Role;
import Role.RoleDatabase;
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
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            msg.setText("Please enter your credentials.");
            return;
        }

        String userNameEntered = username.getText();
        String paswordEntered = password.getText();

        Role getUser = RoleDatabase.searchUser(userNameEntered);

        if (getUser == null) {
            msg.setText("User does not exist");
            return;
        }

        String role = getUser.getRole();
        String password = getUser.getPassword();

        if (!role.equals("Admin")) {
            msg.setText("Wrong software. For Admin only");
            return;
        }

        if (!password.equals(paswordEntered)) {
            msg.setText("Invalid credentials!");
            return;
        }

        AppHolder holder = AppHolder.getInstance();
        holder.setUser(getUser);

        Main.switchScene("ViewBoard.fxml");
    }
}