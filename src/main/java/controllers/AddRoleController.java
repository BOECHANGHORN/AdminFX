package controllers;

import Admin.*;
import Agent.*;
import Owner.*;
import Phone.Phone;
import Role.Role;
import Tenant.*;
import Utils.*;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    private ChoiceBox<Role> dltRoleChoices;

    private TreeMap<Integer, Owner> ownerList = OwnerDatabase.getInstance().read();
    private TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private TreeMap<Integer, Tenant> tenantList = TenantDatabase.getInstance().read();
    private TreeMap<Integer, Admin> adminList = AdminDatabase.getInstance().read();

    @FXML
    private void initialize() {
        populateData();
    }

    private void populateData() {
        RoleStringConverter roleStringConverter = new RoleStringConverter();
        IntegerFormatter integerFormatter = new IntegerFormatter();

        roleChoices.getItems().add("Owner");
        roleChoices.getItems().add("Agent");
        roleChoices.getItems().add("Tenant");
        roleChoices.getItems().add("Admin");

        phoneNoField.setTextFormatter(integerFormatter.getInstance());

        dltRoleChoices.getItems().addAll(ownerList.values());
        dltRoleChoices.getItems().addAll(agentList.values());
        dltRoleChoices.getItems().addAll(tenantList.values());
        dltRoleChoices.getItems().addAll(adminList.values());

        dltRoleChoices.setConverter(roleStringConverter);
    }

    @FXML
    private void onDelete(MouseEvent mouseEvent) throws  IOException {
        if (dltRoleChoices.getValue() != null) {

        }
    }

    @FXML
    private void onUpdate(MouseEvent mouseEvent) throws IOException {
        if (isValid()) {

            if (roleChoices.getValue() == "Owner") {
                OwnerDatabase ownerDB = OwnerDatabase.getInstance();
                int id = ownerDB.getNewID();
                Phone phone = new Phone(phoneNoField.getText());
                Owner owner = new Owner(id, usernameField.getText(), passwordField.getText(), phone);
                ownerDB.create(owner);
            //} else if () {

            }

            Utils.showAlert("Updated Successful!!", true);
        } else {
            Utils.showAlert("All fields are required", false);
        }
    }

    private boolean isValid() {
        return roleChoices.getValue() != null && !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
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
