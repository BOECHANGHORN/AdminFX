package controllers;

import Agent.Agent;
import Agent.AgentDatabase;
import AppHolder.AppHolder;
import Owner.Owner;
import Owner.OwnerDatabase;
import Property.*;
import Role.Role;
import Tenant.Tenant;
import Tenant.TenantDatabase;
import Utils.*;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.TreeMap;

/**
 * <h1>UpdatePropertyController Class</h1>
 * The UpdatePropertyController class is a controller class that
 * connect the UpdateProperty screen with the models
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class UpdatePropertyController {
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<PropertyType> typeChoices;
    @FXML
    private ComboBox<Role> ownerChoices;
    @FXML
    private ComboBox<Role> agentChoices;
    @FXML
    private Spinner<Integer> roomNum;
    private final SpinnerValueFactory<Integer> roomNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);

    @FXML
    private Spinner<Integer> bathRoomNum;
    private final SpinnerValueFactory<Integer> bathRoomNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);

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
    private final SpinnerValueFactory<Integer> tvNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
    @FXML
    private Spinner<Integer> fridgeNum;
    private final SpinnerValueFactory<Integer> fridgeNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
    @FXML
    private Spinner<Integer> airConNum;
    private final SpinnerValueFactory<Integer> airConNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
    @FXML
    private Spinner<Integer> waterHeaterNum;
    private final SpinnerValueFactory<Integer> waterHeaterNumVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
    @FXML
    private TextField sizeTxt;
    @FXML
    private TextField rateTxt;
    @FXML
    private CheckBox isPublished;
    @FXML
    private TextField comment;
    @FXML
    private ComboBox<Role> tenantChoices;
    @FXML
    private CheckBox isTenant;

    AppHolder holder = AppHolder.getInstance();

    private final TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private final TreeMap<Integer, Tenant> tenantList = TenantDatabase.getInstance().read();
    private final TreeMap<Integer, Owner> ownerList = OwnerDatabase.getInstance().read();

    private final Property selectedProperty = AppHolder.getInstance().getSelectedProperty();

    /**
     * A private method that will be triggered when
     * the scene initializes and trigger populateData method
     */
    @FXML
    private void initialize() {
        // Setup Input
        populateData();
    }

    /**
     * A private method that triggers updating selected property data
     * and will validate input value before updating
     *
     * @param mouseEvent the mouse event
     */
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
                    Utils.showAlert("Updated Successful!!", true, mouseEvent);
                }
            } else {
                Utils.showAlert("All fields are required", false, mouseEvent);
            }

        }
    }

    /**
     * A private method that populates all the inputs by using the property object
     * data and setups input
     */
    private void populateData() {
        if (selectedProperty != null) {
            PropertyTypeStringConverter propertyTypeStringConverter = new PropertyTypeStringConverter();
            IntegerFormatter integerFormatter2 = new IntegerFormatter();
            RinggitFormatter doubleFormatter = new RinggitFormatter();

            ownerChoices.getItems().addAll(ownerList.values());
            agentChoices.getItems().addAll(agentList.values());
            tenantChoices.getItems().addAll(tenantList.values());

            isTenant.setSelected(selectedProperty.getTenant() != null);
            tenantChoices.setVisible(selectedProperty.getTenant() != null);

            typeChoices.getItems().addAll(PropertyType.values());
            typeChoices.setConverter(propertyTypeStringConverter);
            new AutoCompleteRoleBox(ownerChoices);
            new AutoCompleteRoleBox(agentChoices);
            new AutoCompleteRoleBox(tenantChoices);
            stateChoices.getItems().addAll(Utils.STATES);
            name.setText(selectedProperty.getName());
            typeChoices.setValue(selectedProperty.getType());
            ownerChoices.setValue(selectedProperty.getOwner());
            agentChoices.setValue(selectedProperty.getAgent());

            tenantChoices.setValue(selectedProperty.getTenant());
            stateChoices.setValue(selectedProperty.getAddress().getState());
            address.setText(selectedProperty.getAddress().getDetailAddress());
            postcode.setTextFormatter(new PostcodeFormatter().getInstance());
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

    /**
     * A private method that determines whether tenantChoices is visible
     * based on isTenant value
     *
     * @param mouseEvent the mouse Event
     */
    @FXML
    private void onTenantCheck(MouseEvent mouseEvent) {
        tenantChoices.setVisible(isTenant.isSelected());
    }

    /**
     * A private method that validates inputs
     *
     * @return boolean value that determine whether inputs value are valid
     */
    private boolean isValid() {
        return !name.getText().isEmpty() && typeChoices.getValue() != null && ownerChoices.getValue() != null && agentChoices.getValue() != null
                && stateChoices.getValue() != null && !address.getText().isEmpty() && !postcode.getText().isEmpty();
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
