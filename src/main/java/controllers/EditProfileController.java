package controllers;

import Admin.Admin;
import Admin.AdminDatabase;
import AppHolder.AppHolder;
import Phone.Phone;
import Role.Role;
import Utils.Utils;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class EditProfileController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;

    private Role currentUser = AppHolder.getInstance().getUser();

    @FXML
    private void initialize() {
        populateData();
    }

    @FXML
    private void onUpdate(MouseEvent mouseEvent) throws IOException {
        if (currentUser != null) {
            if (isValid()) {
                String role = currentUser.getRole();
                String currentUsername = currentUser.getUserName();
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();
                String newPhoneNo = phoneNoField.getText();

                AdminDatabase adminDB = AdminDatabase.getInstance();
                Admin adminUser = adminDB.searchUser(currentUsername);
                adminUser.setUserName(newUsername);
                adminUser.setPassword(newPassword);
                adminUser.setPhone(new Phone(newPhoneNo));
                adminDB.update(adminUser);

                Utils.showAlert("Updated Successful!!", true);
            } else {
                Utils.showAlert("All fields are required", false);
            }

        }
    }

    private boolean isValid() {
        return !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    private void populateData() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            phoneNoField.setText(currentUser.getPhone().getNumber());
        }
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
