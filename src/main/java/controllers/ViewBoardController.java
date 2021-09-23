package controllers;

import Agent.Agent;
import Agent.AgentDatabase;
import AppHolder.*;
import Owner.Owner;
import Owner.OwnerDatabase;
import Phone.Phone;
import Property.*;
import Property.PropertySearch.FacilitiesPicker;
import Property.PropertySearch.PropertyFilterBuilder;
import Role.Role;
import Utils.PropertyListener;
import Utils.Utils;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

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
        displayPropertyList(this.ownedPropertyList);
    }

    @FXML
    void onClearFilter() {
        holder.setPropertyFilterHolder(null);
        displayPropertyList( this.ownedPropertyList);
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

    private PropertyFilterBuilder getOwnPropertyFilterBuilder(){

        Role currentUser = holder.getUser();
        PropertyFilterBuilder propertyFilterBuilder = new PropertyFilterBuilder();

        if (currentUser != null) {
            String role = currentUser.getRole();
            if (role.equals("Owner")) {
                propertyFilterBuilder.setOwner((Owner) currentUser);
            } else if (role.equals("Agent")) {
                propertyFilterBuilder.setAgent((Agent) currentUser);

            }
        }
        return propertyFilterBuilder;
    }

    private ArrayList<Property> filterPropertyList() {
        PropertyFilterHolder propertyFilterHolder = holder.getPropertyFilterHolder();
        PropertyFilterBuilder propertyFilterBuilder = getOwnPropertyFilterBuilder();
        if (propertyFilterHolder.isTypeChecked() && propertyFilterHolder.getTypeChoice() != null) {
            propertyFilterBuilder.setType(propertyFilterHolder.getTypeChoice());
        }
        if (propertyFilterHolder.isStatusChecked() && propertyFilterHolder.getStatusChoice() != null) {
            boolean isAvailable = propertyFilterHolder.getStatusChoice() == Utils.ACTIVE;
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
        if (propertyFilterHolder.isAddressChecked() && propertyFilterHolder.getAddressField() != null && propertyFilterHolder.getPostcodeField() != null && propertyFilterHolder.getStateChoice() != null) {
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

        ArrayList<Property> filteredPropertyList = propertyFilterBuilder.build().getResult();
        return filteredPropertyList;

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
                    fxmlLoader.setLocation((Main.class.getResource("propertyRow.fxml")));
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
