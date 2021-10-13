package Tenant;

import Phone.Phone;
import Role.Role;

import java.io.Serializable;


/**
 * <h1>Tenant Class</h1>
 * The Tenant class is a model class that inherit the Role class and it stores
 * the data fields of Tenant
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Tenant extends Role implements Serializable {

    private static final long serialVersionUID = 6543585098267757693L;

    /**
     * Instantiates a new Tenant.
     *
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     */
    public Tenant(int id, String userName, String password, Phone phone) {
        super("Tenant", id, userName, password, phone);
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + super.getId() +
                ", username='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", phone=" + super.getPhone() +
                '}';
    }
}