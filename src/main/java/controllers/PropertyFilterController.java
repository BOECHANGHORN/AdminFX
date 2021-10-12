package controllers;

import Agent.Agent;
import Agent.AgentDatabase;
import AppHolder.AppHolder;
import AppHolder.PropertyFilterHolder;
import Owner.Owner;
import Owner.OwnerDatabase;
import Property.PropertyType;
import Role.Role;
import Tenant.Tenant;
import Tenant.TenantDatabase;
import Utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.TreeMap;

/**
 * <h1>PropertyFilterController Class</h1>
 * The PropertyFilterController class is a controller class that
 * aids in controlling the PropertyFilter component
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class PropertyFilterController {
    public static final String[] STATUSES = {Utils.ACTIVE, Utils.INACTIVE};
    public static final String[] SORT_CHOICES = {Utils.LOWEST_FIRST, Utils.HIGHEST_FIRST};

    @FXML
    private DialogPane dialogPane;
    @FXML
    private CheckBox typeChecked;
    @FXML
    private CheckBox statusChecked;
    @FXML
    private CheckBox commentsChecked;
    @FXML
    private CheckBox facilitiesChecked;
    @FXML
    private CheckBox addressChecked;
    @FXML
    private CheckBox minRateChecked;
    @FXML
    private CheckBox maxRateChecked;
    @FXML
    private CheckBox sortChecked;
    @FXML
    private CheckBox ownerChecked;
    @FXML
    private CheckBox agentChecked;
    @FXML
    private CheckBox tenantChecked;
    @FXML
    private ChoiceBox<PropertyType> typeChoices;
    @FXML
    private ChoiceBox<String> statusChoices;
    @FXML
    private CheckBox isCommented;
    @FXML
    private CheckBox isWifi;
    @FXML
    private CheckBox isFridge;
    @FXML
    private CheckBox isTv;
    @FXML
    private CheckBox isAirCond;
    @FXML
    private CheckBox isWaterHeater;
    @FXML
    private CheckBox isSwimmingPool;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox<String> stateChoices;
    @FXML
    private TextField postcodeField;
    @FXML
    private TextField minRate;
    @FXML
    private TextField maxRate;
    @FXML
    private ChoiceBox<String> sortChoices;
    @FXML
    private ComboBox<Role> ownerChoices;
    @FXML
    private ComboBox<Role> agentChoices;
    @FXML
    private ComboBox<Role> tenantChoices;

    private final TreeMap<Integer, Owner> ownerList = OwnerDatabase.getInstance().read();
    private final TreeMap<Integer, Agent> agentList = AgentDatabase.getInstance().read();
    private final TreeMap<Integer, Tenant> tenantList = TenantDatabase.getInstance().read();

    /**
     * A private method that will be triggered when
     * the scene initializes and trigger populateData, initCheck method
     * and add event filter for button
     */
    @FXML
    private void initialize() {
        populateData();
        initCheck();

        Button okButton = (Button) dialogPane.lookupButton(ButtonType.APPLY);
        okButton.addEventFilter(
                ActionEvent.ACTION, event -> setPropertyFilterHolder());
    }

    /**
     * A private method that sets the data fields of PropertyFilterHolder
     * object that stored in AppHolder instance
     */
    private void setPropertyFilterHolder() {
        AppHolder holder = AppHolder.getInstance();
        PropertyFilterHolder propertyFilterHolder = new PropertyFilterHolder();
        propertyFilterHolder.setTypeChecked(typeChecked.isSelected());
        propertyFilterHolder.setStatusChecked(statusChecked.isSelected());
        propertyFilterHolder.setCommentsChecked(commentsChecked.isSelected());
        propertyFilterHolder.setFacilitiesChecked(facilitiesChecked.isSelected());
        propertyFilterHolder.setAddressChecked(addressChecked.isSelected());
        propertyFilterHolder.setMinRateChecked(minRateChecked.isSelected());
        propertyFilterHolder.setMaxRateChecked(maxRateChecked.isSelected());
        propertyFilterHolder.setSortChecked(sortChecked.isSelected());
        propertyFilterHolder.setOwnerChecked(ownerChecked.isSelected());
        propertyFilterHolder.setAgentChecked(agentChecked.isSelected());
        propertyFilterHolder.setTenantChecked(tenantChecked.isSelected());

        propertyFilterHolder.setTypeChoice(typeChoices.getValue());
        propertyFilterHolder.setStatusChoice(statusChoices.getValue());
        propertyFilterHolder.setIsCommented(isCommented.isSelected());
        propertyFilterHolder.setIsWifi(isWifi.isSelected());
        propertyFilterHolder.setIsFridge(isFridge.isSelected());
        propertyFilterHolder.setIsTv(isTv.isSelected());
        propertyFilterHolder.setIsAirCond(isAirCond.isSelected());
        propertyFilterHolder.setIsWaterHeater(isWaterHeater.isSelected());
        propertyFilterHolder.setIsSwimmingPool(isSwimmingPool.isSelected());
        propertyFilterHolder.setAddressField(addressField.getText());
        propertyFilterHolder.setStateChoice(stateChoices.getValue());
        propertyFilterHolder.setPostcodeField((postcodeField.getText()));
        propertyFilterHolder.setMinRate(minRate.getText());
        propertyFilterHolder.setMaxRate(maxRate.getText());
        propertyFilterHolder.setSortChoice(sortChoices.getValue());
        propertyFilterHolder.setOwnerChoice((Owner) ownerChoices.getValue());
        propertyFilterHolder.setAgentChoice((Agent) agentChoices.getValue());
        propertyFilterHolder.setTenantChoice((Tenant) tenantChoices.getValue());

        holder.setPropertyFilterHolder((propertyFilterHolder));
    }

    /**
     * A private method that setups the all the inputs
     */
    @FXML
    private void populateData() {
        PropertyTypeStringConverter propertyTypeStringConverter = new PropertyTypeStringConverter();
        RoleStringConverter roleStringConverter = new RoleStringConverter();
        IntegerFormatter integerFormatter = new IntegerFormatter();
        RinggitFormatter ringgitFormatter1 = new RinggitFormatter();
        RinggitFormatter ringgitFormatter2 = new RinggitFormatter();

        typeChoices.getItems().addAll(PropertyType.values());
        typeChoices.setConverter(propertyTypeStringConverter);
        statusChoices.getItems().addAll(STATUSES);
        stateChoices.getItems().addAll(Utils.STATES);
        postcodeField.setTextFormatter(new PostcodeFormatter().getInstance());
        minRate.setTextFormatter(ringgitFormatter1.getInstance());
        maxRate.setTextFormatter(ringgitFormatter2.getInstance());
        sortChoices.getItems().addAll(SORT_CHOICES);
        ownerChoices.getItems().addAll(ownerList.values());
        agentChoices.getItems().addAll(agentList.values());
        tenantChoices.getItems().addAll(tenantList.values());
        new AutoCompleteRoleBox(ownerChoices);
        new AutoCompleteRoleBox(agentChoices);
        new AutoCompleteRoleBox(tenantChoices);

        AppHolder holder = AppHolder.getInstance();
        PropertyFilterHolder propertyFilterHolder = holder.getPropertyFilterHolder();
        if (propertyFilterHolder != null) {
            typeChecked.setSelected(propertyFilterHolder.isTypeChecked());
            statusChecked.setSelected(propertyFilterHolder.isStatusChecked());
            commentsChecked.setSelected(propertyFilterHolder.isCommentsChecked());
            facilitiesChecked.setSelected(propertyFilterHolder.isFacilitiesChecked());
            addressChecked.setSelected(propertyFilterHolder.isAddressChecked());
            minRateChecked.setSelected(propertyFilterHolder.isMinRateChecked());
            maxRateChecked.setSelected(propertyFilterHolder.isMaxRateChecked());
            sortChecked.setSelected(propertyFilterHolder.isSortChecked());
            ownerChecked.setSelected(propertyFilterHolder.isOwnerChecked());
            agentChecked.setSelected(propertyFilterHolder.isAgentChecked());
            tenantChecked.setSelected(propertyFilterHolder.isTenantChecked());

            typeChoices.setValue(propertyFilterHolder.getTypeChoice());
            statusChoices.setValue(propertyFilterHolder.getStatusChoice());
            isCommented.setSelected(propertyFilterHolder.isCommented());
            isWifi.setSelected(propertyFilterHolder.isWifi());
            isFridge.setSelected(propertyFilterHolder.isFridge());
            isTv.setSelected(propertyFilterHolder.isTv());
            isAirCond.setSelected(propertyFilterHolder.isAirCond());
            isWaterHeater.setSelected(propertyFilterHolder.isWaterHeater());
            isSwimmingPool.setSelected(propertyFilterHolder.isSwimmingPool());
            addressField.setText(propertyFilterHolder.getAddressField());
            stateChoices.setValue(propertyFilterHolder.getStateChoice());
            postcodeField.setText(propertyFilterHolder.getPostcodeField());
            minRate.setText(propertyFilterHolder.getMinRate());
            maxRate.setText(propertyFilterHolder.getMaxRate());
            sortChoices.setValue(propertyFilterHolder.getSortChoice());
            ownerChoices.setValue(propertyFilterHolder.getOwnerChoice());
            agentChoices.setValue(propertyFilterHolder.getAgentChoice());
            tenantChoices.setValue(propertyFilterHolder.getTenantChoice());
        }
    }

    /**
     * A private method that determines whether disables sortChoices input
     * based on sortChecked value
     */
    private void checkSorting() {
        sortChoices.setDisable(!sortChecked.isSelected());
    }

    /**
     * A private method that triggers checkSorting method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onSortingCheck(MouseEvent mouseEvent) {
        checkSorting();
    }

    /**
     * A private method that determines whether disables isWifi, isFridge, isTv, isAirCond, isWaterHeater
     * and isSwimmingPool inputs based on facilitiesChecked value
     */
    private void checkFacilities() {
        isWifi.setDisable(!facilitiesChecked.isSelected());
        isFridge.setDisable(!facilitiesChecked.isSelected());
        isTv.setDisable(!facilitiesChecked.isSelected());
        isAirCond.setDisable(!facilitiesChecked.isSelected());
        isWaterHeater.setDisable(!facilitiesChecked.isSelected());
        isSwimmingPool.setDisable(!facilitiesChecked.isSelected());
    }

    /**
     * A private method that triggers checkFacilities method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onFacilitiesCheck(MouseEvent mouseEvent) {
        checkFacilities();
    }

    private void checkAddress() {
        addressField.setDisable(!addressChecked.isSelected());
        stateChoices.setDisable(!addressChecked.isSelected());
        postcodeField.setDisable(!addressChecked.isSelected());
    }

    /**
     * A private method that triggers checkAddress method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onAddressCheck(MouseEvent mouseEvent) {
        checkAddress();
    }

    /**
     * A private method that determines whether disables minRate input
     * based on minRateChecked value
     */
    private void checkMinRate() {
        minRate.setDisable(!minRateChecked.isSelected());
    }

    /**
     * A private method that triggers checkMinRate method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onMinRateCheck(MouseEvent mouseEvent) {
        checkMinRate();
    }

    /**
     * A private method that determines whether disables maxRate input
     * based on maxRateChecked value
     */
    private void checkMaxRate() {
        maxRate.setDisable(!maxRateChecked.isSelected());
    }

    /**
     * A private method that triggers checkMaxRate method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onMaxRateCheck(MouseEvent mouseEvent) {
        checkMaxRate();
    }

    /**
     * A private method that determines whether disables statusChoices input
     * based on statusChecked value
     */
    private void checkStatus() {
        statusChoices.setDisable(!statusChecked.isSelected());
    }

    /**
     * A private method that triggers checkStatus method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onStatusCheck(MouseEvent mouseEvent) {
        checkStatus();
    }

    /**
     * A private method that determines whether disables isCommented input
     * based on commentsChecked value
     */
    private void checkComments() {
        isCommented.setDisable(!commentsChecked.isSelected());
    }

    /**
     * A private method that triggers checkComments method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onCommentsCheck(MouseEvent mouseEvent) {
        checkComments();
    }

    /**
     * A private method that determines whether disables typeChoices input
     * based on typeChecked value
     */
    private void checkPropertyType() {
        typeChoices.setDisable(!typeChecked.isSelected());
    }

    /**
     * A private method that triggers checkPropertyType method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onPropertyTypeCheck(MouseEvent mouseEvent) {
        checkPropertyType();
    }

    /**
     * A private method that determines whether disables ownerChoices input
     * based on ownerChecked value
     */
    private void checkOwner() {
        ownerChoices.setDisable(!ownerChecked.isSelected());
    }

    /**
     * A private method that triggers checkOwner method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onOwnerCheck(MouseEvent mouseEvent) {
        checkOwner();
    }

    /**
     * A private method that determines whether disables agentChoices input
     * based on agentChecked value
     */
    private void checkAgent() {
        agentChoices.setDisable(!agentChecked.isSelected());
    }

    /**
     * A private method that triggers checkAgent method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onAgentCheck(MouseEvent mouseEvent) {
        checkAgent();
    }

    /**
     * A private method that determines whether disables tenantChoices input
     * based on tenantChecked value
     */
    private void checkTenant() {
        tenantChoices.setDisable(!tenantChecked.isSelected());
    }

    /**
     * A private method that triggers checkTenant method
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    private void onTenantCheck(MouseEvent mouseEvent) {
        checkTenant();
    }

    /**
     * A private method that triggers all the checking methods for all the inputs
     */
    private void initCheck() {
        checkPropertyType();
        checkStatus();
        checkComments();
        checkAddress();
        checkMinRate();
        checkMaxRate();
        checkFacilities();
        checkSorting();
        checkOwner();
        checkAgent();
        checkTenant();
    }
}
