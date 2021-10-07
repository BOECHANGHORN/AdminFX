package Property;

public class PropertyAddress {
    private String detailAddress;
    private String postalCode;
    private String state;

    public PropertyAddress(String detailAddress, String postalCode, String state) {
        this.detailAddress = detailAddress;
        this.postalCode = postalCode;
        this.state = state;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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