package AppHolder;


import Agent.Agent;
import Owner.Owner;
import Property.PropertyType;
import Role.Role;
import Tenant.Tenant;

public class PropertyFilterHolder {
    private boolean typeChecked;
    private boolean statusChecked;
    private boolean commentsChecked;
    private boolean facilitiesChecked;
    private boolean addressChecked;
    private boolean minRateChecked;
    private boolean maxRateChecked;
    private boolean sortChecked;
    private boolean ownerChecked;
    private boolean agentChecked;
    private boolean tenantChecked;
    private PropertyType typeChoice;
    private String statusChoice;
    private boolean isCommented;
    private boolean isWifi;
    private boolean isFridge;
    private boolean isTv;
    private boolean isAirCond;
    private boolean isWaterHeater;
    private boolean isSwimmingPool;
    private String addressField;
    private String stateChoice;
    private String postcodeField;
    private String minRate;
    private String maxRate;
    private String sortChoice;
    private Role ownerChoice;
    private Role agentChoice;
    private Role tenantChoice;

    public String getSortChoice() {
        return sortChoice;
    }

    public void setSortChoice(String sortChoice) {
        this.sortChoice = sortChoice;
    }

    public boolean isSortChecked() {
        return sortChecked;
    }

    public void setSortChecked(boolean sortChecked) {
        this.sortChecked = sortChecked;
    }

    public String getStateChoice() {
        return stateChoice;
    }

    public void setStateChoice(String stateChoice) {
        this.stateChoice = stateChoice;
    }

    public String getPostcodeField() {
        return postcodeField;
    }

    public void setPostcodeField(String postcodeField) {
        this.postcodeField = postcodeField;
    }

    public boolean isTypeChecked() {
        return typeChecked;
    }

    public void setTypeChecked(boolean typeChecked) {
        this.typeChecked = typeChecked;
    }

    public boolean isStatusChecked() {
        return statusChecked;
    }

    public void setStatusChecked(boolean statusChecked) {
        this.statusChecked = statusChecked;
    }

    public boolean isCommentsChecked() {
        return commentsChecked;
    }

    public void setCommentsChecked(boolean commentsChecked) {
        this.commentsChecked = commentsChecked;
    }

    public boolean isFacilitiesChecked() {
        return facilitiesChecked;
    }

    public void setFacilitiesChecked(boolean facilitiesChecked) {
        this.facilitiesChecked = facilitiesChecked;
    }

    public boolean isAddressChecked() {
        return addressChecked;
    }

    public void setAddressChecked(boolean addressChecked) {
        this.addressChecked = addressChecked;
    }

    public boolean isMinRateChecked() {
        return minRateChecked;
    }

    public void setMinRateChecked(boolean minRateChecked) {
        this.minRateChecked = minRateChecked;
    }

    public boolean isMaxRateChecked() {
        return maxRateChecked;
    }

    public void setMaxRateChecked(boolean maxRateChecked) {
        this.maxRateChecked = maxRateChecked;
    }

    public boolean isOwnerChecked() {
        return ownerChecked;
    }

    public void setOwnerChecked(boolean ownerChecked) {
        this.ownerChecked = ownerChecked;
    }

    public boolean isAgentChecked() {
        return agentChecked;
    }

    public void setAgentChecked(boolean agentChecked) {
        this.agentChecked = agentChecked;
    }

    public boolean isTenantChecked() {
        return tenantChecked;
    }

    public void setTenantChecked(boolean tenantChecked) {
        this.tenantChecked = tenantChecked;
    }

    public PropertyType getTypeChoice() {
        return typeChoice;
    }

    public void setTypeChoice(PropertyType typeChoice) {
        this.typeChoice = typeChoice;
    }

    public String getStatusChoice() {
        return statusChoice;
    }

    public void setStatusChoice(String statusChoice) {
        this.statusChoice = statusChoice;
    }

    public boolean isCommented() {
        return isCommented;
    }

    public void setIsCommented(boolean commented) {
        isCommented = commented;
    }

    public boolean isWifi() {
        return isWifi;
    }

    public void setIsWifi(boolean wifi) {
        isWifi = wifi;
    }

    public boolean isFridge() {
        return isFridge;
    }

    public void setIsFridge(boolean fridge) {
        isFridge = fridge;
    }

    public boolean isTv() {
        return isTv;
    }

    public void setIsTv(boolean Tv) {
        isTv = Tv;
    }

    public boolean isAirCond() {
        return isAirCond;
    }

    public void setIsAirCond(boolean airCond) {
        isAirCond = airCond;
    }

    public boolean isWaterHeater() {
        return isWaterHeater;
    }

    public void setIsWaterHeater(boolean waterHeater) {
        isWaterHeater = waterHeater;
    }

    public boolean isSwimmingPool() {
        return isSwimmingPool;
    }

    public void setIsSwimmingPool(boolean swimmingPool) {
        isSwimmingPool = swimmingPool;
    }

    public String getAddressField() {
        return addressField;
    }

    public void setAddressField(String addressField) {
        this.addressField = addressField;
    }

    public String getMinRate() {
        return minRate;
    }

    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }

    public Role getOwnerChoice() {
        return ownerChoice;
    }

    public void setOwnerChoice(Owner ownerChoice) {
        this.ownerChoice = ownerChoice;
    }

    public Role getAgentChoice() {
        return agentChoice;
    }

    public void setAgentChoice(Agent agentChoice) {
        this.agentChoice = agentChoice;
    }

    public Role getTenantChoice() {
        return tenantChoice;
    }

    public void setTenantChoice(Tenant tenantChoice) {
        this.tenantChoice = tenantChoice;
    }
}
