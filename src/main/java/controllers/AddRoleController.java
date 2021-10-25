package controllers;

import Admin.Admin;
import Admin.AdminDatabase;
import Agent.Agent;
import Agent.AgentDatabase;
import AppHolder.AppHolder;
import Owner.Owner;
import Owner.OwnerDatabase;
import Phone.Phone;
import Property.Property;
import Property.PropertyDatabase;
import Property.PropertySearch.PropertyFilterBuilder;
import Role.Role;
import Role.RoleDatabase;
import Tenant.Tenant;
import Tenant.TenantDatabase;
import Utils.AutoCompleteRoleBox;
import Utils.PhoneFormatter;
import Utils.RoleStringConverter;
import Utils.Utils;
import com.app.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>AddRoleController Class</h1>
 * The AddRoleController class is a controller class that
 * connect the AddRole screen with the models
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class AddRoleController {
    @FXML
    private ChoiceBox<String> roleChoices;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneNoField;
    @FXML
    private CheckBox addCheck;
    @FXML
    private CheckBox editDltCheck;
    @FXML
    private ComboBox<Role> userChoices;
    @FXML
    private Button createBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button dltBtn;
    @FXML
    private Label userLabel;

    /**
     * A private method that will be triggered when
     * the scene initializes and trigger populateData method
     * and addSelected method
     */
    @FXML
    private void initialize() {
        addSelected();
        populateData();
    }

    /**
     * A private method that setup all the inputs
     */
    private void populateData() {
        RoleStringConverter roleStringConverter = new RoleStringConverter();

        roleChoices.getItems().add("Owner");
        roleChoices.getItems().add("Agent");
        roleChoices.getItems().add("Tenant");
        roleChoices.getItems().add("Admin");

        new AutoCompleteRoleBox(userChoices);
        phoneNoField.setTextFormatter(new PhoneFormatter().getInstance());
    }

    /**
     * A private method that setup all the inputs
     * for the add function
     */
    private void addSelected() {
        addCheck.setSelected(true);
        editDltCheck.setSelected(false);
        createBtn.setVisible(true);
        editBtn.setVisible(false);
        dltBtn.setVisible(false);
        userChoices.setVisible(false);
        userLabel.setVisible(false);
        clearAll();
    }

    /**
     * A private method that setup all the inputs
     * for the edit or delete function
     */
    private void editDltSelected() {
        addCheck.setSelected(false);
        editDltCheck.setSelected(true);
        createBtn.setVisible(false);
        editBtn.setVisible(true);
        dltBtn.setVisible(true);
        userChoices.setVisible(true);
        userLabel.setVisible(true);
        clearAll();
    }

    /**
     * A private method that clears ComboBox and inputs
     */
    private void clearAll() {
        roleChoices.setValue(null);
        userChoices.getItems().clear();
        clearText();
    }

    /**
     * A private method that clears text fields
     */
    private void clearText() {
        usernameField.setText("");
        passwordField.setText("");
        phoneNoField.setText("");
    }

    /**
     * A private method that setup all the add functions
     * by calling addSelected and editDltSelected methods
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onAddClick(MouseEvent mouseEvent) {
        if (addCheck.isSelected()) {
            addSelected();
        } else {
            editDltSelected();
        }
    }

    /**
     * A private method that setup all the edit or delete functions
     * by calling addSelected and editDltSelected methods
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onEditDltClick(MouseEvent mouseEvent) {
        if (editDltCheck.isSelected()) {
            editDltSelected();
        } else {
            addSelected();
        }
    }

    /**
     * A private method that calls onSelectRole method
     *
     * @param actionEvent the action event
     */
    @FXML
    private void onRoleRequest(ActionEvent actionEvent) {
        onSelectRole();
    }

    /**
     * A private method that setups the userChoices ComboBox
     * by the Role selected in roleChoices ComboBox
     */
    private void onSelectRole() {
        userChoices.getItems().clear();

        if (editDltCheck.isSelected()) {
            if (roleChoices.getValue() == null)
                return;

            userChoices.getItems().addAll(RoleDatabase.read(roleChoices.getValue()));
        }
    }

    /**
     * A private method that setup all the text fields that is
     * selected in the userChoices ComboBox
     *
     * @param actionEvent the action event
     */
    @FXML
    private void onUserRequest(ActionEvent actionEvent) {
        if (userChoices.getValue() != null) {
            Role role = userChoices.getValue();

            usernameField.setText(role.getUserName());
            passwordField.setText(role.getPassword());
            phoneNoField.setText(role.getPhone().getNumber());

            dltBtn.setDisable(role == AppHolder.getInstance().getUser()); //prevent delete ourselves
        } else {
            clearText();
        }
    }

    /**
     * A private method that creates the new Role with validation
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onCreate(MouseEvent mouseEvent) {
        if (isValid()) {

            String newUsername = usernameField.getText();
            if (RoleDatabase.isUserExist(newUsername)) {
                Utils.showAlert("User name has been taken", false, mouseEvent);
                return;
            }

            String role = roleChoices.getValue();
            int id = RoleDatabase.getNewID(role);
            String password = passwordField.getText();
            Phone phone = new Phone(phoneNoField.getText());

            Role newUser = Role.newRole(role, id, newUsername, password, phone);
            assert newUser != null;
            RoleDatabase.create(newUser);

            Utils.showAlert("Create Successful!!", true, mouseEvent);
        } else {
            Utils.showAlert("All fields are required", false, mouseEvent);
        }
    }

    /**
     * A private method that edits the selected Role with validation
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onEdit(MouseEvent mouseEvent) {
        if (!inputValidate(mouseEvent)) {
            return;
        }

        String newUsername = usernameField.getText();
        Role selectedUser = userChoices.getValue();

        if (!selectedUser.getUserName().equals(newUsername) && RoleDatabase.isUserExist(newUsername)) {
            Utils.showAlert("User name has been taken", false, mouseEvent);
            return;
        }

        selectedUser.setUserName(newUsername);
        selectedUser.setPassword(passwordField.getText());
        selectedUser.setPhone(new Phone(phoneNoField.getText()));
        RoleDatabase.update(selectedUser);

        Utils.showAlert("Edit Successful!!", true, mouseEvent);

        onSelectRole();
        userChoices.setValue(selectedUser);
    }

    /**
     * A private method that deletes the selected Role with validation
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onDlt(MouseEvent mouseEvent) {
        if (!isUserChoice(mouseEvent)) {
            return;
        }

        if ((roleChoices.getValue().equals("Owner") ||
                roleChoices.getValue().equals("Agent")) &&
                !deleteProperty(userChoices.getValue(), mouseEvent)) {
            return;
        }

        if (roleChoices.getValue().equals("Tenant") && !unlinkProperty((Tenant) userChoices.getValue(), mouseEvent)) {
            return;
        }

        RoleDatabase.delete(userChoices.getValue());

        Utils.showAlert("Deleted Successful!!", true, mouseEvent);

        userChoices.setValue(null);
        clearText();
        onSelectRole();
    }

    /**
     * A private method that prompts the user on confirmation at deleting the Role object
     * that is pass into this method
     *
     * @param role the Role that wants to be deleted
     * @param mouseEvent the mouse event
     * @return boolean value that determine whether the Role is agreed to delete
     */
    private boolean deleteProperty(Role role, MouseEvent mouseEvent) {
        PropertyFilterBuilder pfb = new PropertyFilterBuilder();

        if (roleChoices.getValue().equals("Owner"))
            pfb.setOwner((Owner) role);
        else
            pfb.setAgent((Agent) role);

        ArrayList<Property> properties = pfb.build().getResult();

        if (properties.isEmpty())
            return true;

        String asked = role.getUserName() + " has " + properties.size() + " properties.";
        asked += " Do you want to delete " + role.getUserName() + " which will delete all the affected properties ?";

        if (Utils.showConfirm(asked, mouseEvent)) {
            for (Property p : properties)
                PropertyDatabase.getInstance().delete(p);
            Utils.showAlert("All affected properties have been deleted", true, mouseEvent);
            return true;
        } else
            return false;
    }

    /**
     * A private method that prompts the user on confirmation at unlinking the Property object
     * that is pass into this method
     *
     * @param tenant the Tenant that wants to be unlinked
     * @param mouseEvent the mouse event
     * @return boolean value that determine whether the Property is agreed to unlink
     */
    private boolean unlinkProperty(Tenant tenant, MouseEvent mouseEvent) {
        ArrayList<Property> properties = new PropertyFilterBuilder().setTenant(tenant).build().getResult();
        if (properties.isEmpty())
            return true;

        String asked = tenant.getUserName() + " is still renting " + properties.size() + " properties.";
        asked += " Do you want to delete " + tenant.getUserName() + " which will unlink all the affected properties ?";

        if (Utils.showConfirm(asked, mouseEvent)) {
            for (Property p : properties) {
                p.setTenant(null);
                PropertyDatabase.getInstance().update(p);
            }
            Utils.showAlert("All affected properties have been unlinked", true, mouseEvent);
            return true;
        } else
            return false;
    }

    /**
     * A private method that validates inputs
     *
     * @return boolean value that determine whether inputs value are valid
     */
    private boolean isValid() {
        return roleChoices.getValue() != null && !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    /**
     * A private method that validates the userChoice value with alert
     *
     * @param mouseEvent the mouse event
     * @return boolean value that determine whether inputs value are valid
     */
    private boolean isUserChoice(MouseEvent mouseEvent) {
        if (userChoices.getValue() == null) {
            Utils.showAlert("Please select a user", false, mouseEvent);
            return false;
        }
        return true;
    }

    /**
     * A private method that validates the input value with alert
     *
     * @param mouseEvent the mouse event
     * @return boolean value that determine whether inputs value are valid
     */
    private boolean inputValidate(MouseEvent mouseEvent) {
        if (!isUserChoice(mouseEvent))
            return false;

        if (!isValid()) {
            Utils.showAlert("All fields are required", false, mouseEvent);
            return false;
        }
        return true;
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
