package controllers;

import Admin.*;
import Agent.*;
import Owner.*;
import Phone.Phone;
import Property.*;
import Property.PropertySearch.PropertyFilterBuilder;
import Role.*;
import Tenant.*;
import Utils.*;
import com.app.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

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

    private TreeMap<Integer, Owner> ownerList = OwnerDatabase.getInstance().read();
    private TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private TreeMap<Integer, Tenant> tenantList = TenantDatabase.getInstance().read();
    private TreeMap<Integer, Admin> adminList = AdminDatabase.getInstance().read();

    @FXML
    private void initialize() {
        addSelected();
        populateData();
    }

    private void populateData() {
        RoleStringConverter roleStringConverter = new RoleStringConverter();

        roleChoices.getItems().add("Owner");
        roleChoices.getItems().add("Agent");
        roleChoices.getItems().add("Tenant");
        roleChoices.getItems().add("Admin");

        new AutoCompleteRoleBox(userChoices);
        phoneNoField.setTextFormatter(new PhoneFormatter().getInstance());
    }

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

    private void clearAll() {
        roleChoices.setValue(null);
        userChoices.getItems().clear();
        clearText();
    }

    private void clearText() {
        usernameField.setText("");
        passwordField.setText("");
        phoneNoField.setText("");
    }

    @FXML
    private void onAddClick(MouseEvent mouseEvent) {
        if (addCheck.isSelected()) {
            addSelected();
        } else {
            editDltSelected();
        }
    }

    @FXML
    private void onEditDltClick(MouseEvent mouseEvent) {
        if (editDltCheck.isSelected()) {
            editDltSelected();
        } else {
            addSelected();
        }
    }

    @FXML
    private void onRoleRequest(ActionEvent actionEvent) {
        onSelectRole();
    }

    private void onSelectRole() {
        userChoices.getItems().clear();

        if (editDltCheck.isSelected()) {
            if (roleChoices.getValue() == null)
                return;

            switch (roleChoices.getValue()) {
                case "Owner":
                    userChoices.getItems().addAll(ownerList.values());
                    break;
                case "Agent":
                    userChoices.getItems().addAll(agentList.values());
                    break;
                case "Tenant":
                    userChoices.getItems().addAll(tenantList.values());
                    break;
                case "Admin":
                    userChoices.getItems().addAll(adminList.values());
                    break;
            }
        }
    }

    @FXML
    private void onUserRequest(ActionEvent actionEvent) {
        if (userChoices.getValue() != null) {
            Role role = userChoices.getValue();

            usernameField.setText(role.getUserName());
            passwordField.setText(role.getPassword());
            phoneNoField.setText(role.getPhone().getNumber());
        } else {
            clearText();
        }
    }

    @FXML
    private void onCreate(MouseEvent mouseEvent) {
        if (isValid()) {

            String newUsername = usernameField.getText();
            if (RoleDatabase.isUserExist(newUsername))  {
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

    @FXML
    private void onEdit(MouseEvent mouseEvent) {
        if (!inputValidate(mouseEvent)){
            return;
        }

        String newUsername = usernameField.getText();
        Role selectedUser = userChoices.getValue();

        if (!selectedUser.getUserName().equals(newUsername) && RoleDatabase.isUserExist(newUsername))  {
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

    @FXML
    private void onDlt(MouseEvent mouseEvent) {
        if (!inputValidate(mouseEvent)){
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


    private boolean isValid() {
        return roleChoices.getValue() != null && !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    private boolean inputValidate(MouseEvent mouseEvent) {
        if (userChoices.getValue() == null) {
            Utils.showAlert("Please select a user", false, mouseEvent);
            return false;
        }
        if (!isValid()) {
            Utils.showAlert("All fields are required", false, mouseEvent);
            return false;
        }
        return true;
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
