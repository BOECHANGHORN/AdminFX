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
import javafx.scene.control.CheckBox;
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
    @FXML
    private CheckBox ownerCheck;
    @FXML
    private CheckBox agentCheck;
    @FXML
    private CheckBox tenantCheck;
    @FXML
    private CheckBox adminCheck;

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

        dltRoleChoices.setConverter(roleStringConverter);
    }

    @FXML
    private void onDelete(MouseEvent mouseEvent) {
        if (dltRoleChoices.getValue() != null) {

            if (ownerCheck.isSelected()) {
                System.out.println(dltRoleChoices.getValue().getUserName());
                OwnerDatabase ownerDB = OwnerDatabase.getInstance();
                ownerDB.delete((Owner) dltRoleChoices.getValue());

            } else if (agentCheck.isSelected()) {

            } else if (tenantCheck.isSelected()) {

            } else if (adminCheck.isSelected()) {

            }

            Utils.showAlert("Updated Successful!!", true);
        } else {
            Utils.showAlert("All fields are required", false);
        }
    }

    @FXML
    private void onOwnerClick(MouseEvent mouseEvent) {
        dltRoleChoices.getItems().clear();

        agentCheck.setSelected(false);
        tenantCheck.setSelected(false);
        adminCheck.setSelected(false);

        dltRoleChoices.getItems().addAll(ownerList.values());
    }

    @FXML
    private void onAgentClick(MouseEvent mouseEvent) {
        dltRoleChoices.getItems().clear();

        ownerCheck.setSelected(false);
        tenantCheck.setSelected(false);
        adminCheck.setSelected(false);

        dltRoleChoices.getItems().addAll(agentList.values());
    }

    @FXML
    private void onTenantClick(MouseEvent mouseEvent) {
        dltRoleChoices.getItems().clear();

        agentCheck.setSelected(false);
        ownerCheck.setSelected(false);
        adminCheck.setSelected(false);

        dltRoleChoices.getItems().addAll(tenantList.values());
    }

    @FXML
    private void onAdminClick(MouseEvent mouseEvent) {
        dltRoleChoices.getItems().clear();

        agentCheck.setSelected(false);
        tenantCheck.setSelected(false);
        ownerCheck.setSelected(false);

        dltRoleChoices.getItems().addAll(adminList.values());
    }

    @FXML
    private void onUpdate(MouseEvent mouseEvent) {
        if (isValid()) {

            if (roleChoices.getValue() == "Owner") {

                OwnerDatabase ownerDB = OwnerDatabase.getInstance();
                int id = ownerDB.getNewID();
                Phone phone = new Phone(phoneNoField.getText());
                Owner owner = new Owner(id, usernameField.getText(), passwordField.getText(), phone);
                ownerDB.create(owner);

            } else if (roleChoices.getValue() == "Agent") {

                AgentDatabase agentDB = AgentDatabase.getInstance();
                int id = agentDB.getNewID();
                Phone phone = new Phone(phoneNoField.getText());
                Agent agent = new Agent(id, usernameField.getText(), passwordField.getText(), phone);
                agentDB.create(agent);

            } else if (roleChoices.getValue() == "Tenant") {

                TenantDatabase tenantDB = TenantDatabase.getInstance();
                int id = tenantDB.getNewID();
                Phone phone = new Phone(phoneNoField.getText());
                Tenant tenant = new Tenant(id, usernameField.getText(), passwordField.getText(), phone);
                tenantDB.create(tenant);

            } else if (roleChoices.getValue() == "Admin") {

                AdminDatabase adminDB = AdminDatabase.getInstance();
                int id = adminDB.getNewID();
                Phone phone = new Phone(phoneNoField.getText());
                Admin admin = new Admin(id, usernameField.getText(), passwordField.getText(), phone);
                adminDB.create(admin);

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
