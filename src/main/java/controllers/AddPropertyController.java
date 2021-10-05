package controllers;

import Agent.*;
import AppHolder.AppHolder;
import Owner.*;
import Property.*;
import Role.Role;
import Tenant.*;
import Utils.*;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.TreeMap;


public class AddPropertyController {
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<PropertyType> typeChoices;
    @FXML
    private ChoiceBox<Role> ownerChoices;
    @FXML
    private ChoiceBox<Role> agentChoices;
    @FXML
    private Spinner<Integer> roomNum;
    private SpinnerValueFactory<Integer> roomNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> bathRoomNum;
    private SpinnerValueFactory<Integer> bathRoomNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private TextArea address;
    @FXML
    private ChoiceBox<String> stateChoices;
    @FXML
    private TextField postcode;
    @FXML
    private CheckBox wifi;
    @FXML
    private CheckBox swimmingPool;
    @FXML
    private Spinner<Integer> tvNum;
    private SpinnerValueFactory<Integer> tvNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> fridgeNum;
    private SpinnerValueFactory<Integer> fridgeNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> airConNum;
    private SpinnerValueFactory<Integer> airConNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private Spinner<Integer> waterHeaterNum;
    private SpinnerValueFactory<Integer> waterHeaterNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
    @FXML
    private TextField sizeTxt;
    @FXML
    private TextField rateTxt;
    @FXML
    private CheckBox isPublished;
    @FXML
    private TextField comment;
    @FXML
    private ChoiceBox<Role> tenantChoices;
    @FXML
    private CheckBox isTenant;

    AppHolder holder = AppHolder.getInstance();
    private TreeMap<Integer, Owner> ownerList = OwnerDatabase.getInstance().read();
    private TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private TreeMap<Integer, Tenant> tenantList = TenantDatabase.getInstance().read();

    @FXML
    private void initialize() {
        // Setup Input
        populateData();
    }

    private void populateData() {
        RoleStringConverter roleStringConverter = new RoleStringConverter();
        PropertyTypeStringConverter propertyTypeStringConverter = new PropertyTypeStringConverter();
        IntegerFormatter integerFormatter1 = new IntegerFormatter();
        IntegerFormatter integerFormatter2 = new IntegerFormatter();
        DoubleFormatter doubleFormatter = new DoubleFormatter();

        ownerChoices.getItems().addAll(ownerList.values());
        agentChoices.getItems().addAll(agentList.values());
        tenantChoices.getItems().addAll(tenantList.values());

        tenantChoices.setVisible(false);

        typeChoices.getItems().addAll(PropertyType.values());
        typeChoices.setConverter(propertyTypeStringConverter);
        ownerChoices.setConverter(roleStringConverter);
        agentChoices.setConverter(roleStringConverter);
        tenantChoices.setConverter(roleStringConverter);
        stateChoices.getItems().addAll(Utils.STATES);
        postcode.setTextFormatter(new PostcodeFormatter().getInstance());
        roomNumVF.setValue(0);
        roomNum.setValueFactory(roomNumVF);
        bathRoomNumVF.setValue(0);
        bathRoomNum.setValueFactory(bathRoomNumVF);
        tvNumVF.setValue(0);
        tvNum.setValueFactory(tvNumVF);
        fridgeNumVF.setValue(0);
        fridgeNum.setValueFactory(fridgeNumVF);
        airConNumVF.setValue(0);
        airConNum.setValueFactory(airConNumVF);
        waterHeaterNumVF.setValue(0);
        waterHeaterNum.setValueFactory(waterHeaterNumVF);
        sizeTxt.setTextFormatter(integerFormatter2.getInstance());
        sizeTxt.setText("0");
        rateTxt.setTextFormatter(doubleFormatter.getInstance());
    }

    @FXML
    private void onSubmit(MouseEvent mouseEvent) {
        if (isValid()) {
            PropertyDatabase propertyDB = PropertyDatabase.getInstance();
            int id = propertyDB.getNewID();
            PropertyBuilder pb = new PropertyBuilder(id, name.getText());
            pb.setType(typeChoices.getValue());
            pb.setOwner((Owner) ownerChoices.getValue());
            pb.setAgent((Agent) agentChoices.getValue());
            pb.setRoomNum(roomNum.getValue());
            pb.setBathRoomNum(bathRoomNum.getValue());
            pb.setAddress(new PropertyAddress(address.getText(), postcode.getText(), stateChoices.getValue()));
            pb.setFacilities(new PropertyFacilities(swimmingPool.isSelected(), wifi.isSelected(), tvNum.getValue(), fridgeNum.getValue(), airConNum.getValue(), waterHeaterNum.getValue()));
            int size = Integer.parseInt(sizeTxt.getText());
            double rate = Double.parseDouble(rateTxt.getText());
            pb.setSize(size);
            pb.setRate(rate);
            pb.setPublished(isPublished.isSelected());
            pb.setComment(comment.getText());

            if (isTenant.isSelected()) {
                pb.setTenant((Tenant) tenantChoices.getValue());
            } else {
                pb.setTenant(null);
            }

            Property property = new Property(pb);
            propertyDB.create(property);
            if (propertyDB.searchByID(id) != null) {
                resetInput();
                Utils.showAlert("Created Successful!!", true, mouseEvent);
            }
        } else {
            Utils.showAlert("All fields are required", false, mouseEvent);
        }
    }

    private boolean isValid() {
        return !name.getText().isEmpty() && typeChoices.getValue() != null && ownerChoices.getValue() != null && agentChoices.getValue() != null
                && stateChoices.getValue() != null && !address.getText().isEmpty() && !postcode.getText().isEmpty();
    }

    private void resetInput() {
        name.setText("");
        typeChoices.setValue(null);
        ownerChoices.setValue(null);
        agentChoices.setValue(null);
        tenantChoices.setValue(null);
        isTenant.setSelected(false);
        stateChoices.setValue(null);
        address.setText("");
        postcode.setTextFormatter(null);
        postcode.setText("");
        postcode.setTextFormatter(new PostcodeFormatter().getInstance());
        roomNumVF.setValue(0);
        bathRoomNumVF.setValue(0);
        tvNumVF.setValue(0);
        fridgeNumVF.setValue(0);
        airConNumVF.setValue(0);
        waterHeaterNumVF.setValue(0);
        wifi.setSelected(false);
        swimmingPool.setSelected(false);
        sizeTxt.setText("0");
        rateTxt.setText("0.0");
        isPublished.setSelected(false);
        comment.setText("");
    }

    @FXML
    private void onTenantCheck(MouseEvent mouseEvent) {
        tenantChoices.setVisible(isTenant.isSelected());
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
