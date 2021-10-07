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

public class ViewBoardController {

    @FXML
    private GridPane grid;
    private PropertyListener myListener;
    ArrayList<Property> ownedPropertyList;
    AppHolder holder = AppHolder.getInstance();

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

    @FXML
    void onClearFilter() {
        holder.setPropertyFilterHolder(null);
        displayPropertyList(this.ownedPropertyList);
    }

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

    private PropertyFilterBuilder getOwnPropertyFilterBuilder() {

        return new PropertyFilterBuilder();
    }

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
            propertyFilterBuilder.setSorted(isSortedByLowestFirst);
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


    private void updateSelectedProperty(Property property) throws IOException {
        holder.setSelectedProperty(property);
        Main.switchScene("UpdateProperty.fxml");
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
