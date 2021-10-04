package controllers;

import Admin.*;
import Agent.*;
import Owner.*;
import Phone.Phone;
import Role.Role;
import Tenant.*;
import Utils.*;
import com.app.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private CheckBox addCheck;
    @FXML
    private CheckBox editDltCheck;
    @FXML
    private ChoiceBox<Role> userChoices;
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

    private void populateData() {
        RoleStringConverter roleStringConverter = new RoleStringConverter();
        IntegerFormatter integerFormatter = new IntegerFormatter();

        roleChoices.getItems().add("Owner");
        roleChoices.getItems().add("Agent");
        roleChoices.getItems().add("Tenant");
        roleChoices.getItems().add("Admin");

        userChoices.setConverter(roleStringConverter);
    }

    @FXML
    private void onRoleRequest(ActionEvent actionEvent) {
        onSelectRole();
    }

    private void onSelectRole() {
        userChoices.getItems().clear();

        if (editDltCheck.isSelected()) {
            if (roleChoices.getValue() == "Owner") {
                userChoices.getItems().addAll(ownerList.values());

            } else if (roleChoices.getValue() == "Agent") {
                userChoices.getItems().addAll(agentList.values());

            } else if (roleChoices.getValue() == "Tenant") {
                userChoices.getItems().addAll(tenantList.values());

            } else if (roleChoices.getValue() == "Admin") {
                userChoices.getItems().addAll(adminList.values());
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
    private void onEdit(MouseEvent mouseEvent) {
        if (!inputValidate()){
            return;
        }

        if (roleChoices.getValue() == "Owner") {

            Owner owner = (Owner) userChoices.getValue();
            owner.setUserName(usernameField.getText());
            owner.setPassword(passwordField.getText());
            owner.setPhone(new Phone(phoneNoField.getText()));
            OwnerDatabase.getInstance().update(owner);

        } else if (roleChoices.getValue() == "Agent") {

            Agent agent = (Agent) userChoices.getValue();
            agent.setUserName(usernameField.getText());
            agent.setPassword(passwordField.getText());
            agent.setPhone(new Phone(phoneNoField.getText()));
            AgentDatabase.getInstance().update(agent);

        } else if (roleChoices.getValue() == "Tenant") {

            Tenant tenant = (Tenant) userChoices.getValue();
            tenant.setUserName(usernameField.getText());
            tenant.setPassword(passwordField.getText());
            tenant.setPhone(new Phone(phoneNoField.getText()));
            TenantDatabase.getInstance().update(tenant);

        } else if (roleChoices.getValue() == "Admin") {

            Admin admin = (Admin) userChoices.getValue();
            admin.setUserName(usernameField.getText());
            admin.setPassword(passwordField.getText());
            admin.setPhone(new Phone(phoneNoField.getText()));
            AdminDatabase.getInstance().update(admin);
        }
        Utils.showAlert("Edit Successful!!", true);

        Role selectedUser = userChoices.getValue();
        onSelectRole();
        userChoices.setValue(selectedUser);
    }

    @FXML
    private void onDlt(MouseEvent mouseEvent) {
        if (!inputValidate()){
            return;
        }
        Set<String> affectedRole = Set.of("Owner, Agent");
        if ((roleChoices.getValue() == "Owner" || roleChoices.getValue() == "Agent") && !deleteProperty(userChoices.getValue())){
            return;
        }
        if (roleChoices.getValue() == "Tenant" && !unlinkProperty((Tenant) userChoices.getValue())) {
            return;
        }

        if (roleChoices.getValue() == "Owner") {
            OwnerDatabase ownerDB = OwnerDatabase.getInstance();
            ownerDB.delete((Owner) userChoices.getValue());

        } else if (roleChoices.getValue() == "Agent") {
            AgentDatabase agentDB = AgentDatabase.getInstance();
            agentDB.delete((Agent) userChoices.getValue());

        } else if (roleChoices.getValue() == "Tenant") {
            TenantDatabase tenantDB = TenantDatabase.getInstance();
            tenantDB.delete((Tenant) userChoices.getValue());

        } else if (roleChoices.getValue() == "Admin") {
            AdminDatabase adminDB = AdminDatabase.getInstance();
            adminDB.delete((Admin) userChoices.getValue());
        }
        Utils.showAlert("Deleted Successful!!", true);

        userChoices.setValue(null);
        clearText();
        onSelectRole();
    }

    private boolean deleteProperty(Role role) {
        PropertyFilterBuilder pfb = new PropertyFilterBuilder();

        if (roleChoices.getValue() == "Owner")
            pfb.setOwner((Owner) role);
        else
            pfb.setAgent((Agent) role);

        ArrayList<Property> properties = pfb.build().getResult();

        if (properties.isEmpty())
            return true;

        String asked = role.getUserName() + " has " + properties.size() + " properties.";
        asked += " Do you want to delete " + role.getUserName() + " which will delete all the affected properties ?";

        if (Utils.showConfirm(asked)) {
            for (Property p : properties)
                PropertyDatabase.getInstance().delete(p);
            Utils.showAlert("All affected properties have been deleted", true);
            return true;
        } else
            return false;
    }

    private boolean unlinkProperty(Tenant tenant) {
        ArrayList<Property> properties = new PropertyFilterBuilder().setTenant(tenant).build().getResult();
        if (properties.isEmpty())
            return true;

        String asked = tenant.getUserName() + " is still renting " + properties.size() + " properties.";
        asked += " Do you want to delete " + tenant.getUserName() + " which will unlink all the affected properties ?";

        if (Utils.showConfirm(asked)) {
            for (Property p : properties) {
                p.setTenant(null);
                PropertyDatabase.getInstance().update(p);
            }
            Utils.showAlert("All affected properties have been unlinked", true);
            return true;
        } else
            return false;
    }

    @FXML
    private void onCreate(MouseEvent mouseEvent) {
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
            Utils.showAlert("Create Successful!!", true);
        } else {
            Utils.showAlert("All fields are required", false);
        }
    }

    private boolean isValid() {
        return roleChoices.getValue() != null && !usernameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !phoneNoField.getText().isEmpty();
    }

    private boolean inputValidate() {
        if (userChoices.getValue() == null) {
            Utils.showAlert("Please select a user", false);
            return false;
        }
        if (!isValid()) {
            Utils.showAlert("All fields are required", false);
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
