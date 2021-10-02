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

public class UpdatePropertyController {
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
    //private TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private TreeMap<Integer, Tenant> tenantList = TenantDatabase.getInstance().read();
    private Property selectedProperty = AppHolder.getInstance().getSelectedProperty();


    @FXML
    private void initialize() {
        // Setup Input
        populateData();
    }

    @FXML
    private void onUpdate(MouseEvent mouseEvent) {
        if (selectedProperty != null) {
            if (isValid()) {
                PropertyDatabase propertyDB = PropertyDatabase.getInstance();
                selectedProperty.setName(name.getText());
                selectedProperty.setType(typeChoices.getValue());
                selectedProperty.setOwner((Owner) ownerChoices.getValue());
                selectedProperty.setAgent((Agent) agentChoices.getValue());
                selectedProperty.setRoomNum(roomNum.getValue());
                selectedProperty.setBathRoomNum(bathRoomNum.getValue());
                selectedProperty.setAddress(new PropertyAddress(address.getText(), postcode.getText(), stateChoices.getValue()));
                selectedProperty.setFacilities(new PropertyFacilities(swimmingPool.isSelected(), wifi.isSelected(), tvNum.getValue(), fridgeNum.getValue(), airConNum.getValue(), waterHeaterNum.getValue()));
                int size = Integer.parseInt(sizeTxt.getText());
                double rate = Double.parseDouble(rateTxt.getText());
                selectedProperty.setSize(size);
                selectedProperty.setRate(rate);
                selectedProperty.setPublished(isPublished.isSelected());
                selectedProperty.setComment(comment.getText() == null || comment.getText().isEmpty() ? null : comment.getText());

                if (isTenant.isSelected()) {
                    selectedProperty.setTenant((Tenant) tenantChoices.getValue());
                } else {
                    selectedProperty.setTenant(null);
                }

                propertyDB.update(selectedProperty);

                if (propertyDB.searchByID(selectedProperty.getId()) != null) {
                    Utils.showAlert("Updated Successful!!", true);
                }
            } else {
                Utils.showAlert("All fields are required", false);
            }

        }
    }

    private void populateData() {
        if (selectedProperty != null) {
            RoleStringConverter roleStringConverter = new RoleStringConverter();
            PropertyTypeStringConverter propertyTypeStringConverter = new PropertyTypeStringConverter();
            IntegerFormatter integerFormatter1 = new IntegerFormatter();
            IntegerFormatter integerFormatter2 = new IntegerFormatter();
            DoubleFormatter doubleFormatter = new DoubleFormatter();

            ownerChoices.getItems().addAll(selectedProperty.getOwner());
            agentChoices.getItems().addAll(selectedProperty.getAgent());
            //agentChoices.getItems().addAll(agentList.values());
            tenantChoices.getItems().addAll(tenantList.values());

            isTenant.setSelected(selectedProperty.getTenant() != null);
            tenantChoices.setVisible(selectedProperty.getTenant() != null);

            typeChoices.getItems().addAll(PropertyType.values());
            typeChoices.setConverter(propertyTypeStringConverter);
            ownerChoices.setConverter(roleStringConverter);
            agentChoices.setConverter(roleStringConverter);
            tenantChoices.setConverter(roleStringConverter);
            stateChoices.getItems().addAll(Utils.STATES);
            name.setText(selectedProperty.getName());
            typeChoices.setValue(selectedProperty.getType());
            ownerChoices.setValue(selectedProperty.getOwner());
            agentChoices.setValue(selectedProperty.getAgent());
            tenantChoices.setValue(selectedProperty.getTenant());
            stateChoices.setValue(selectedProperty.getAddress().getState());
            address.setText(selectedProperty.getAddress().getDetailAddress());
            postcode.setTextFormatter(integerFormatter1.getInstance());
            postcode.setText(selectedProperty.getAddress().getPostalCode());
            wifi.setSelected(selectedProperty.getFacilities().isWifi());
            swimmingPool.setSelected(selectedProperty.getFacilities().isSwimmingPool());
            roomNumVF.setValue(selectedProperty.getRoomNum());
            roomNum.setValueFactory(roomNumVF);
            bathRoomNumVF.setValue(selectedProperty.getBathRoomNum());
            bathRoomNum.setValueFactory(bathRoomNumVF);
            tvNumVF.setValue(selectedProperty.getFacilities().getTv());
            tvNum.setValueFactory(tvNumVF);
            fridgeNumVF.setValue(selectedProperty.getFacilities().getFridge());
            fridgeNum.setValueFactory(fridgeNumVF);
            airConNumVF.setValue(selectedProperty.getFacilities().getAirCond());
            airConNum.setValueFactory(airConNumVF);
            waterHeaterNumVF.setValue(selectedProperty.getFacilities().getWaterHeater());
            waterHeaterNum.setValueFactory(waterHeaterNumVF);
            sizeTxt.setTextFormatter(integerFormatter2.getInstance());
            sizeTxt.setText(Integer.toString(selectedProperty.getSize()));
            rateTxt.setTextFormatter(doubleFormatter.getInstance());
            rateTxt.setText(Double.toString(selectedProperty.getRate()));
            isPublished.setSelected(selectedProperty.isPublished());
            comment.setText(selectedProperty.getComment());
        }

    }

    @FXML
    private void onTenantCheck(MouseEvent mouseEvent) {
        tenantChoices.setVisible(isTenant.isSelected());
    }

    private boolean isValid() {
        return !name.getText().isEmpty() && typeChoices.getValue() != null && ownerChoices.getValue() != null && agentChoices.getValue() != null
                && stateChoices.getValue() != null && !address.getText().isEmpty() && !postcode.getText().isEmpty();
    }

    @FXML
    private void onClickHomeBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToViewBoardPage();
    }

    @FXML
    private void onClickAddBtn(MouseEvent mouseEvent) throws IOException {
        Main.goToAddPropertyPage();
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
