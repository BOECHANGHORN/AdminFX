package Property.PropertySearch;

/**
 * <h1>FacilitiesPicker Class</h1>
 * The FacilitiesPicker class is a class that use for selection to
 * search and filter facilities in PropertyFilter
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class FacilitiesPicker {
    private Boolean swimmingPool;
    private Boolean wifi;
    private Boolean tv;
    private Boolean fridge;
    private Boolean airCond;
    private Boolean waterHeater;

    /**
     * Instantiates a new Facilities picker.
     */
    public FacilitiesPicker() {
    }

    /**
     * Is swimming pool boolean.
     *
     * @return the boolean
     */
    public Boolean isSwimmingPool() {
        return swimmingPool;
    }

    /**
     * Sets swimming pool.
     *
     * @param swimmingPool the swimming pool
     * @return the swimming pool
     */
    public FacilitiesPicker setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
        return this;
    }

    /**
     * Is wifi boolean.
     *
     * @return the boolean
     */
    public Boolean isWifi() {
        return wifi;
    }

    /**
     * Sets wifi.
     *
     * @param wifi the wifi
     * @return the wifi
     */
    public FacilitiesPicker setWifi(boolean wifi) {
        this.wifi = wifi;
        return this;
    }

    /**
     * Is tv boolean.
     *
     * @return the boolean
     */
    public Boolean isTv() {
        return tv;
    }

    /**
     * Sets tv.
     *
     * @param tv the tv
     * @return the tv
     */
    public FacilitiesPicker setTv(boolean tv) {
        this.tv = tv;
        return this;
    }

    /**
     * Is fridge boolean.
     *
     * @return the boolean
     */
    public Boolean isFridge() {
        return fridge;
    }

    /**
     * Sets fridge.
     *
     * @param fridge the fridge
     * @return the fridge
     */
    public FacilitiesPicker setFridge(boolean fridge) {
        this.fridge = fridge;
        return this;
    }

    /**
     * Is air cond boolean.
     *
     * @return the boolean
     */
    public Boolean isAirCond() {
        return airCond;
    }

    /**
     * Sets air cond.
     *
     * @param airCond the air cond
     * @return the air cond
     */
    public FacilitiesPicker setAirCond(boolean airCond) {
        this.airCond = airCond;
        return this;
    }

    /**
     * Is water heater boolean.
     *
     * @return the boolean
     */
    public Boolean isWaterHeater() {
        return waterHeater;
    }

    /**
     * Sets water heater.
     *
     * @param waterHeater the water heater
     * @return the water heater
     */
    public FacilitiesPicker setWaterHeater(boolean waterHeater) {
        this.waterHeater = waterHeater;
        return this;
    }
}
