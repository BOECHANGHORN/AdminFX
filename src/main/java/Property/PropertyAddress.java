package Property;

/**
 * <h1>PropertyAddress Class</h1>
 * The PropertyAddress class is a model class stores
 * the data fields of PropertyAddress
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class PropertyAddress {
    private String detailAddress;
    private String postalCode;
    private String state;

    /**
     * Instantiates a new Property address.
     *
     * @param detailAddress the detail address
     * @param postalCode    the postal code
     * @param state         the state
     */
    public PropertyAddress(String detailAddress, String postalCode, String state) {
        this.detailAddress = detailAddress;
        this.postalCode = postalCode;
        this.state = state;
    }

    /**
     * Gets detail address.
     *
     * @return the detail address
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * Sets detail address.
     *
     * @param detailAddress the detail address
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets full address.
     *
     * @return the full address
     */
    public String getFullAddress() {
        return detailAddress + ", " + postalCode + ", " + state;
    }

    @Override
    public String toString() {
        return "PropertyAddress{" +
                "detailAddress='" + detailAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}