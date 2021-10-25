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

/**
 * <h1>EditProfileController Class</h1>
 * The EditProfileController class is a controller class that
 * connect the EditProfile screen with the models
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class EditProfileController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;

    private final Role currentUser = AppHolder.getInstance().getUser();

    /**
     * A private method that will be triggered when
     * the scene initializes and trigger populateData method
     */
    @FXML
    private void initialize() {
        populateData();
    }

    /**
     * A private method that triggers updating user data
     * and validates input value before updating
     *
     * @param mouseEvent the mouse event
     */
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

    /**
     * A private method that validates inputs
     *
     * @return boolean value that determine whether inputs value are valid
     */
    private boolean isValid() {
        return !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    /**
     * A private method that populates all the inputs by using the Role object
     * data and setups input
     */
    private void populateData() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            phoneNoField.setText(currentUser.getPhone().getNumber());
            phoneNoField.setTextFormatter(new PhoneFormatter().getInstance());
        }
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
     * A private method that initializes AddProperty scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onClickAddBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToAddPropertyPage();
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

    /**
     * A private method that initializes ManageRole scene
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void onClickManageRole(MouseEvent mouseEvent) throws IOException {
        Main.goToManageRolePage();
    }
}
