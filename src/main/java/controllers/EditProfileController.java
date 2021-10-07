package controllers;

import AppHolder.AppHolder;
import Phone.Phone;
import Role.Role;
import Role.RoleDatabase;
import Utils.PhoneFormatter;
import Utils.Utils;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class EditProfileController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;

    private final Role currentUser = AppHolder.getInstance().getUser();

    @FXML
    private void initialize() {
        populateData();
    }

    @FXML
    private void onUpdate(MouseEvent mouseEvent) {
        if (!isValid()) {
            Utils.showAlert("All fields are required", false, mouseEvent);
            return;
        }

        String currentUsername = currentUser.getUserName();
        String newUsername = usernameField.getText();
        String newPassword = passwordField.getText();
        String newPhoneNo = phoneNoField.getText();

        if (!currentUsername.equals(newUsername) && RoleDatabase.isUserExist(newUsername)) {
            Utils.showAlert("User name has been taken", false, mouseEvent);
            return;
        }

        Role currentUser = RoleDatabase.searchUser(currentUsername);
        currentUser.setUserName(newUsername);
        currentUser.setPassword(newPassword);
        currentUser.setPhone(new Phone(newPhoneNo));
        RoleDatabase.update(currentUser);

        Utils.showAlert("Updated Successful!!", true, mouseEvent);
    }

    private boolean isValid() {
        return !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    private void populateData() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            phoneNoField.setText(currentUser.getPhone().getNumber());
            phoneNoField.setTextFormatter(new PhoneFormatter().getInstance());
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
