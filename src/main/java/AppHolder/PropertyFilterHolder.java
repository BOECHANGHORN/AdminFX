package AppHolder;


import Agent.Agent;
import Owner.Owner;
import Property.PropertyType;
import Role.Role;
import Tenant.Tenant;

/**
 * <h1>PropertyFilterHolder Class</h1>
 * The PropertyFilterHolder class is a model class that store t
 * the data fields of PropertyFilterHolder
 *
 * @author Boe Chang Horn
 * @version 1.0
 * @since 2021-10-12
 */

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

    /**
     * Gets sort choice.
     *
     * @return the sort choice
     */
    public String getSortChoice() {
        return sortChoice;
    }

    /**
     * Sets sort choice.
     *
     * @param sortChoice the sort choice
     */
    public void setSortChoice(String sortChoice) {
        this.sortChoice = sortChoice;
    }

    /**
     * Gets sortChecked.
     *
     * @return the sortChecked
     */
    public boolean isSortChecked() {
        return sortChecked;
    }

    /**
     * Sets sortChecked.
     *
     * @param sortChecked the sortChecked
     */
    public void setSortChecked(boolean sortChecked) {
        this.sortChecked = sortChecked;
    }

    /**
     * Gets state choice.
     *
     * @return the state choice
     */
    public String getStateChoice() {
        return stateChoice;
    }

    /**
     * Sets state choice.
     *
     * @param stateChoice the state choice
     */
    public void setStateChoice(String stateChoice) {
        this.stateChoice = stateChoice;
    }

    /**
     * Gets postcode field;.
     *
     * @return the postcode field;
     */
    public String getPostcodeField() {
        return postcodeField;
    }

    /**
     * Sets postcode field.
     *
     * @param postcodeField the postcode field
     */
    public void setPostcodeField(String postcodeField) {
        this.postcodeField = postcodeField;
    }

    /**
     * Gets typeChecked.
     *
     * @return the typeChecked
     */
    public boolean isTypeChecked() {
        return typeChecked;
    }

    /**
     * Sets typeChecked.
     *
     * @param typeChecked the typeChecked
     */
    public void setTypeChecked(boolean typeChecked) {
        this.typeChecked = typeChecked;
    }

    /**
     * Gets statusChecked.
     *
     * @return the statusChecked
     */
    public boolean isStatusChecked() {
        return statusChecked;
    }

    /**
     * Sets statusChecked.
     *
     * @param statusChecked the statusChecked
     */
    public void setStatusChecked(boolean statusChecked) {
        this.statusChecked = statusChecked;
    }

    /**
     * Gets commentsChecked.
     *
     * @return the commentsChecked
     */
    public boolean isCommentsChecked() {
        return commentsChecked;
    }

    /**
     * Sets commentsChecked.
     *
     * @param commentsChecked the commentsChecked
     */
    public void setCommentsChecked(boolean commentsChecked) {
        this.commentsChecked = commentsChecked;
    }

    /**
     * Gets facilitiesChecked.
     *
     * @return the facilitiesChecked
     */
    public boolean isFacilitiesChecked() {
        return facilitiesChecked;
    }

    /**
     * Sets facilitiesChecked.
     *
     * @param facilitiesChecked the facilitiesChecked
     */
    public void setFacilitiesChecked(boolean facilitiesChecked) {
        this.facilitiesChecked = facilitiesChecked;
    }

    /**
     * Gets addressChecked.
     *
     * @return the addressChecked
     */
    public boolean isAddressChecked() {
        return addressChecked;
    }

    /**
     * Sets addressChecked.
     *
     * @param addressChecked the addressChecked
     */
    public void setAddressChecked(boolean addressChecked) {
        this.addressChecked = addressChecked;
    }

    /**
     * Gets minRateChecked.
     *
     * @return the minRateChecked
     */
    public boolean isMinRateChecked() {
        return minRateChecked;
    }

    /**
     * Sets minRateChecked.
     *
     * @param minRateChecked the minRateChecked
     */
    public void setMinRateChecked(boolean minRateChecked) {
        this.minRateChecked = minRateChecked;
    }

    /**
     * Gets maxRateChecked.
     *
     * @return the maxRateChecked
     */
    public boolean isMaxRateChecked() {
        return maxRateChecked;
    }

    /**
     * Sets maxRateChecked.
     *
     * @param maxRateChecked the maxRateChecked
     */
    public void setMaxRateChecked(boolean maxRateChecked) {
        this.maxRateChecked = maxRateChecked;
    }

    /**
     * Gets ownerChecked.
     *
     * @return the ownerChecked
     */
    public boolean isOwnerChecked() {
        return ownerChecked;
    }

    /**
     * Sets ownerChecked.
     *
     * @param ownerChecked the ownerChecked
     */
    public void setOwnerChecked(boolean ownerChecked) {
        this.ownerChecked = ownerChecked;
    }

    /**
     * Gets agentChecked.
     *
     * @return the agentChecked
     */
    public boolean isAgentChecked() {
        return agentChecked;
    }

    /**
     * Sets agentChecked.
     *
     * @param agentChecked the agentChecked
     */
    public void setAgentChecked(boolean agentChecked) {
        this.agentChecked = agentChecked;
    }

    /**
     * Gets tenantChecked.
     *
     * @return the tenantChecked
     */
    public boolean isTenantChecked() {
        return tenantChecked;
    }

    /**
     * Sets tenantChecked.
     *
     * @param tenantChecked the tenantChecked
     */
    public void setTenantChecked(boolean tenantChecked) {
        this.tenantChecked = tenantChecked;
    }

    /**
     * Gets type choice.
     *
     * @return the type choice
     */
    public PropertyType getTypeChoice() {
        return typeChoice;
    }

    /**
     * Sets type choice.
     *
     * @param typeChoice the type choice
     */
    public void setTypeChoice(PropertyType typeChoice) {
        this.typeChoice = typeChoice;
    }

    /**
     * Gets status choice.
     *
     * @return the status choice
     */
    public String getStatusChoice() {
        return statusChoice;
    }

    /**
     * Sets status choice.
     *
     * @param statusChoice the status choice
     */
    public void setStatusChoice(String statusChoice) {
        this.statusChoice = statusChoice;
    }

    /**
     * Gets isCommented.
     *
     * @return the isCommented
     */
    public boolean isCommented() {
        return isCommented;
    }

    /**
     * Sets isCommented.
     *
     * @param commented the isCommented
     */
    public void setIsCommented(boolean commented) {
        isCommented = commented;
    }

    /**
     * Gets isWifi.
     *
     * @return the isWifi
     */
    public boolean isWifi() {
        return isWifi;
    }

    /**
     * Sets isWifi.
     *
     * @param wifi the isWifi
     */
    public void setIsWifi(boolean wifi) {
        isWifi = wifi;
    }

    /**
     * Gets isFridge.
     *
     * @return the isFridge
     */
    public boolean isFridge() {
        return isFridge;
    }

    /**
     * Sets isFridge.
     *
     * @param fridge the isFridge
     */
    public void setIsFridge(boolean fridge) {
        isFridge = fridge;
    }

    /**
     * Gets isTV.
     *
     * @return the isTV
     */
    public boolean isTv() {
        return isTv;
    }

    /**
     * Sets isTV.
     *
     * @param Tv the isTV
     */
    public void setIsTv(boolean Tv) {
        isTv = Tv;
    }

    /**
     * Gets isAirCond.
     *
     * @return the isAirCond
     */
    public boolean isAirCond() {
        return isAirCond;
    }

    /**
     * Sets isAirCond.
     *
     * @param airCond the isAirCond
     */
    public void setIsAirCond(boolean airCond) {
        isAirCond = airCond;
    }

    /**
     * Gets isWaterHeater.
     *
     * @return the isWaterHeater
     */
    public boolean isWaterHeater() {
        return isWaterHeater;
    }

    /**
     * Sets isWaterHeater.
     *
     * @param waterHeater the isWaterHeater
     */
    public void setIsWaterHeater(boolean waterHeater) {
        isWaterHeater = waterHeater;
    }

    /**
     * Gets isSwimmingPool.
     *
     * @return the isSwimmingPool
     */
    public boolean isSwimmingPool() {
        return isSwimmingPool;
    }

    /**
     * Sets isSwimmingPool.
     *
     * @param swimmingPool the isSwimmingPool
     */
    public void setIsSwimmingPool(boolean swimmingPool) {
        isSwimmingPool = swimmingPool;
    }

    /**
     * Gets address field.
     *
     * @return the address field
     */
    public String getAddressField() {
        return addressField;
    }

    /**
     * Sets address field.
     *
     * @param addressField the address field
     */
    public void setAddressField(String addressField) {
        this.addressField = addressField;
    }

    /**
     * Gets min rate.
     *
     * @return the min rate
     */
    public String getMinRate() {
        return minRate;
    }

    /**
     * Sets min rate.
     *
     * @param minRate the min rate
     */
    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    /**
     * Gets max rate.
     *
     * @return the max rate
     */
    public String getMaxRate() {
        return maxRate;
    }

    /**
     * Sets max rate.
     *
     * @param maxRate the max rate
     */
    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
    }

    /**
     * Gets owner choice.
     *
     * @return the owner choice
     */
    public Role getOwnerChoice() {
        return ownerChoice;
    }

    /**
     * Sets owner choice.
     *
     * @param ownerChoice the owner choice
     */
    public void setOwnerChoice(Owner ownerChoice) {
        this.ownerChoice = ownerChoice;
    }

    /**
     * Gets agent choice.
     *
     * @return the agent choice
     */
    public Role getAgentChoice() {
        return agentChoice;
    }

    /**
     * Sets agent choice.
     *
     * @param agentChoice the agent choice
     */
    public void setAgentChoice(Agent agentChoice) {
        this.agentChoice = agentChoice;
    }

    /**
     * Gets tenant choice.
     *
     * @return the tenant choice
     */
    public Role getTenantChoice() {
        return tenantChoice;
    }

    /**
     * Sets tenant choice.
     *
     * @param tenantChoice the tenant choice
     */
    public void setTenantChoice(Tenant tenantChoice) {
        this.tenantChoice = tenantChoice;
    }
}
