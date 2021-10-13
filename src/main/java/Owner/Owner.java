package Owner;

import Phone.Phone;
import Role.Role;

import java.io.Serializable;


/**
 * <h1>Owner Class</h1>
 * The Owner class is a model class that inherit the Role class and it stores
 * the data fields of Owner
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Owner extends Role implements Serializable {

    private static final long serialVersionUID = 6543585098267757692L;

    /**
     * Instantiates a new Owner.
     *
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     */
    public Owner(int id, String userName, String password, Phone phone) {
        super("Owner", id, userName, password, phone);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + super.getId() +
                ", username='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", phone=" + super.getPhone() +
                '}';
    }
}