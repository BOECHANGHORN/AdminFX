package controllers;

import Property.*;
import Tenant.Tenant;
import Utils.*;
import com.app.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PropertyRowController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label rateLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label agentLabel;
    @FXML
    private Label tenantLabel;
    @FXML
    private Label buildingDetailLabel;
    @FXML
    private Label roomNumLabel;
    @FXML
    private Label bathRoomNumLabel;
    @FXML
    private Label tvNumLabel;
    @FXML
    private Label fridgeNumLabel;
    @FXML
    private Label waterHeaterNumLabel;
    @FXML
    private Label airCondNumLabel;
    @FXML
    private Label wifiLabel;
    @FXML
    private Label spLabel;
    @FXML
    private Label commentLabel;
    @FXML
    private AnchorPane propertyPane;


    private Property property;
    private PropertyListener myListener;

    @FXML
    private void onClick(MouseEvent mouseEvent) {
        myListener.onClickListener(property);
    }

    @FXML
    private void onDelete(MouseEvent mouseEvent) throws IOException {
        boolean confirm = Utils.showConfirm("Confirm to delete " + property.getName() + " ?", mouseEvent);

        if (confirm) {
            PropertyDatabase.getInstance().delete(property);
            propertyPane.setVisible(false);
            Utils.showAlert(property.getName() + " is deleted successfully", true, mouseEvent);
        }

        Main.goToViewBoardPage();
    }

    public void setData(Property property, PropertyListener myListener) {
        this.property = property;
        this.myListener = myListener;
        nameLabel.setText(property.getName());
        String addressTxt = property.getAddress().getFullAddress();
        addressLabel.setText(addressTxt);
        rateLabel.setText(Utils.CURRENCY + " " + property.getRate());
        buildingDetailLabel.setText(Utils.getPropertyTypeTxt(property.getType()) + "  •  " + property.getSize() + " " + Utils.SIZE_UNIT);
        roomNumLabel.setText(Integer.toString(property.getRoomNum()));
        bathRoomNumLabel.setText(Integer.toString(property.getBathRoomNum()));
        tvNumLabel.setText(Integer.toString(property.getFacilities().getTv()));
        fridgeNumLabel.setText(Integer.toString(property.getFacilities().getFridge()));
        waterHeaterNumLabel.setText(Integer.toString(property.getFacilities().getWaterHeater()));
        airCondNumLabel.setText(Integer.toString(property.getFacilities().getAirCond()));
        wifiLabel.setText(Utils.getYesOrNo(property.getFacilities().isWifi()));
        spLabel.setText(Utils.getYesOrNo(property.getFacilities().isSwimmingPool()));
        ownerLabel.setText(property.getOwner().getUserName() + " ( " + property.getOwner().getPhone().getNumber() + " )");
        agentLabel.setText(property.getAgent().getUserName() + " ( " + property.getAgent().getPhone().getNumber() + " )");

        String commentStr = property.getComment();
        if (commentStr == null) {
            commentLabel.setText("N/A");
            commentLabel.setStyle("-fx-text-fill: #697684");
        } else {
            commentLabel.setText(property.getComment());
        }

        Tenant tenant = property.getTenant();
        if (tenant == null) {
            tenantLabel.setText("N/A");
            tenantLabel.setStyle("-fx-text-fill: #697684");
        } else {
            tenantLabel.setText(tenant.getUserName()+ " ( " + tenant.getPhone().getNumber() + " )");
        }

        if (!property.isPublished()) {
            propertyPane.setStyle("-fx-background-color: #CCCCCC");
        }
    }
}
