package controllers;

import Property.Property;
import Tenant.Tenant;
import Utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PropertyRowController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label rateLabel;
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
    private Label tenantLabel;

    private Property property;
    private PropertyListener myListener;

    @FXML
    private void onClick(MouseEvent mouseEvent) {
        myListener.onClickListener(property);
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
            tenantLabel.setText(tenant.getUserName());
        }
    }
}
