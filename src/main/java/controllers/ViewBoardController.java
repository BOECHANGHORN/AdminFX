package controllers;

import Agent.Agent;
import AppHolder.AppHolder;
import AppHolder.PropertyFilterHolder;
import Owner.Owner;
import Property.Property;
import Property.PropertyAddress;
import Property.PropertySearch.FacilitiesPicker;
import Property.PropertySearch.PropertyFilterBuilder;
import Tenant.Tenant;
import Utils.PropertyListener;
import Utils.Utils;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * <h1>ViewBoardController Class</h1>
 * The ViewBoardController class is a controller class that
 * connect the ViewBoard screen with the models
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */
public class ViewBoardController {

    @FXML
    private GridPane grid;
    private PropertyListener myListener;
    ArrayList<Property> ownedPropertyList;
    AppHolder holder = AppHolder.getInstance();

    /**
     * A private method that will be triggered when
     * the scene initializes and displays property list
     * based on the presence of PropertyFilterHolder object
     * that stored in AppHolder instance
     */
    @FXML
    private void initialize() {
        // Show Property List owned by current owner/agent
        this.ownedPropertyList = getOwnPropertyFilterBuilder().build().getResult();

        if (holder.getPropertyFilterHolder() == null) {
            displayPropertyList(this.ownedPropertyList);
        } else {
            ArrayList<Property> filteredPropertyList = filterPropertyList();
            displayPropertyList(filteredPropertyList);
        }
    }

    /**
     * A private method that clears the PropertyFilterHolder object data
     * and displays original property list
     */
    @FXML
    void onClearFilter() {
        holder.setPropertyFilterHolder(null);
        displayPropertyList(this.ownedPropertyList);
    }

    /**
     * A private method that load the PropertyFilter.fxml and open
     * filters dialog
     */
    @FXML
    void onOpenFilterDialog(MouseEvent event) {
        String dialogTitle = "Filter Property List";

        try {
            // Load the fxml file and create a new popup dialog.
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("PropertyFilter.fxml"));
            DialogPane propertyFilterDialogPane = fxmlLoader.load();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(propertyFilterDialogPane);
            dialog.setTitle(dialogTitle);
            dialog.setX(event.getScreenX());
            dialog.setY(event.getScreenY());

            Optional<ButtonType> clickedButton = dialog.showAndWait();

            if (clickedButton.get() == ButtonType.APPLY) {
                // Displayed filtered list
                ArrayList<Property> filteredPropertyList = filterPropertyList();
                displayPropertyList(filteredPropertyList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private method that build up the property filter
     *
     * @return PropertyFilterBuilder object
     */
    private PropertyFilterBuilder getOwnPropertyFilterBuilder() {

        return new PropertyFilterBuilder();
    }

    /**
     * A private method that filters the property list
     * based on PropertyFilterHolder object data
     *
     * @return arraylist of Property object
     */
    private ArrayList<Property> filterPropertyList() {
        PropertyFilterHolder propertyFilterHolder = holder.getPropertyFilterHolder();
        PropertyFilterBuilder propertyFilterBuilder = getOwnPropertyFilterBuilder();

        if (propertyFilterHolder.isTypeChecked() && propertyFilterHolder.getTypeChoice() != null) {
            propertyFilterBuilder.setType(propertyFilterHolder.getTypeChoice());
        }
        if (propertyFilterHolder.isStatusChecked() && propertyFilterHolder.getStatusChoice() != null) {
            boolean isAvailable = propertyFilterHolder.getStatusChoice().equals(Utils.ACTIVE);
            propertyFilterBuilder.setAvailability(isAvailable);
        }
        if (propertyFilterHolder.isCommentsChecked()) {
            propertyFilterBuilder.setComment(propertyFilterHolder.isCommented());
        }
        if (propertyFilterHolder.isFacilitiesChecked()) {
            FacilitiesPicker facilitiesPicker = new FacilitiesPicker();
            facilitiesPicker.setWifi(propertyFilterHolder.isWifi());
            facilitiesPicker.setFridge(propertyFilterHolder.isFridge());
            facilitiesPicker.setTv(propertyFilterHolder.isTv());
            facilitiesPicker.setAirCond(propertyFilterHolder.isAirCond());
            facilitiesPicker.setWaterHeater(propertyFilterHolder.isWaterHeater());
            facilitiesPicker.setSwimmingPool(propertyFilterHolder.isSwimmingPool());
            propertyFilterBuilder.setFacilitiesPicker(facilitiesPicker);
        }
        if (propertyFilterHolder.isAddressChecked()) {
            PropertyAddress propertyAddress = new PropertyAddress(propertyFilterHolder.getAddressField(), propertyFilterHolder.getPostcodeField(), propertyFilterHolder.getStateChoice());
            propertyFilterBuilder.setPropertyAddress(propertyAddress);
        }
        if (propertyFilterHolder.isMinRateChecked() && propertyFilterHolder.getMinRate() != null) {
            double doubleMinRate = Double.parseDouble(propertyFilterHolder.getMinRate());
            propertyFilterBuilder.setRentalLowBound(doubleMinRate);
        }
        if (propertyFilterHolder.isMaxRateChecked() && propertyFilterHolder.getMaxRate() != null) {
            double doubleMaxRate = Double.parseDouble(propertyFilterHolder.getMaxRate());
            propertyFilterBuilder.setRentalUpBound(doubleMaxRate);
        }
        if (propertyFilterHolder.isSortChecked() && propertyFilterHolder.getSortChoice() != null) {
            boolean isSortedByLowestFirst = propertyFilterHolder.getSortChoice().equals(Utils.LOWEST_FIRST);
            propertyFilterBuilder.setAscending(isSortedByLowestFirst);
        }
        if (propertyFilterHolder.isOwnerChecked() && propertyFilterHolder.getOwnerChoice() != null) {
            propertyFilterBuilder.setOwner((Owner) propertyFilterHolder.getOwnerChoice());
        }
        if (propertyFilterHolder.isAgentChecked() && propertyFilterHolder.getAgentChoice() != null) {
            propertyFilterBuilder.setAgent((Agent) propertyFilterHolder.getAgentChoice());
        }
        if (propertyFilterHolder.isTenantChecked() && propertyFilterHolder.getTenantChoice() != null) {
            propertyFilterBuilder.setTenant((Tenant) propertyFilterHolder.getTenantChoice());
        }

        return propertyFilterBuilder.build().getResult();

    }

    /**
     * A private method that displays all the property objects
     * by loading PropertyRow component
     *
     * @param propertyList the arraylist of Property object
     */
    private void displayPropertyList(ArrayList<Property> propertyList) {
        int row = 0;
        grid.getChildren().clear();
        try {
            if (propertyList.size() > 0) {
                myListener = new PropertyListener() {
                    @Override
                    public void onClickListener(Property property) {
                        try {
                            updateSelectedProperty(property);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                for (Property p : propertyList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation((Main.class.getResource("PropertyRow.fxml")));
                    AnchorPane anchorPane = fxmlLoader.load();
                    PropertyRowController propertyRowController = fxmlLoader.getController();
                    propertyRowController.setData(p, myListener);

                    grid.add(anchorPane, 0, row);
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(890);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                    row++;
                }
            } else {
                Label msg = new Label();
                msg.setText("No result found.");
                msg.setFont(new Font("System", 20));
                msg.setPadding(new Insets(10));
                grid.add(msg, 0, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * A private method that sets the selectedProperty object stored
     * in AppHolder instance
     *
     * @param property the Property object
     */
    private void updateSelectedProperty(Property property) throws IOException {
        holder.setSelectedProperty(property);
        Main.switchScene("UpdateProperty.fxml");
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
