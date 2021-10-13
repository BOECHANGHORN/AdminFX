package Property;

import java.io.Serializable;

/**
 * <h1>PropertyFacilities Class</h1>
 * The PropertyFacilities class is a model class stores
 * the data fields of PropertyFacilities
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class PropertyFacilities implements Serializable {
    private boolean swimmingPool;
    private boolean wifi;
    private int tv;
    private int fridge;
    private int airCond;
    private int waterHeater;

    /**
     * Instantiates a new Property facilities.
     *
     * @param swimmingPool the swimming pool
     * @param wifi         the wifi
     * @param tv           the tv
     * @param fridge       the fridge
     * @param airCond      the air cond
     * @param waterHeater  the water heater
     */
    public PropertyFacilities(boolean swimmingPool, boolean wifi, int tv, int fridge, int airCond, int waterHeater) {
        this.swimmingPool = swimmingPool;
        this.wifi = wifi;
        this.tv = tv;
        this.fridge = fridge;
        this.airCond = airCond;
        this.waterHeater = waterHeater;
    }

    /**
     * Is swimming pool boolean.
     *
     * @return the boolean
     */
    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    /**
     * Sets swimming pool.
     *
     * @param swimmingPool the swimming pool
     */
    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    /**
     * Is wifi boolean.
     *
     * @return the boolean
     */
    public boolean isWifi() {
        return wifi;
    }

    /**
     * Sets wifi.
     *
     * @param wifi the wifi
     */
    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    /**
     * Gets tv.
     *
     * @return the tv
     */
    public int getTv() {
        return tv;
    }

    /**
     * Sets tv.
     *
     * @param tv the tv
     */
    public void setTv(int tv) {
        this.tv = tv;
    }

    /**
     * Gets fridge.
     *
     * @return the fridge
     */
    public int getFridge() {
        return fridge;
    }

    /**
     * Sets fridge.
     *
     * @param fridge the fridge
     */
    public void setFridge(int fridge) {
        this.fridge = fridge;
    }

    /**
     * Gets air cond.
     *
     * @return the air cond
     */
    public int getAirCond() {
        return airCond;
    }

    /**
     * Sets air cond.
     *
     * @param airCond the air cond
     */
    public void setAirCond(int airCond) {
        this.airCond = airCond;
    }

    /**
     * Gets water heater.
     *
     * @return the water heater
     */
    public int getWaterHeater() {
        return waterHeater;
    }

    /**
     * Sets water heater.
     *
     * @param waterHeater the water heater
     */
    public void setWaterHeater(int waterHeater) {
        this.waterHeater = waterHeater;
    }

    @Override
    public String toString() {
        return "PropertyFacilities{" +
                "swimmingPool=" + swimmingPool +
                ", wifi=" + wifi +
                ", tv=" + tv +
                ", fridge=" + fridge +
                ", airCond=" + airCond +
                ", waterHeater=" + waterHeater +
                '}';
    }
}
