package Phone;

/**
 * <h1>Phone Class</h1>
 * The Phone class store the property of a phone number, the reason to
 * create a class is for extended usage in the future
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class Phone {
    private String number;

    /**
     * Instantiates a new Phone
     *
     * @param number the phone number
     */
    public Phone(String number) {
        this.number = number;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                '}';
    }
}
