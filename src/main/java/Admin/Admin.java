package Admin;

import Phone.Phone;
import Role.Role;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * <h1>Admin Class</h1>
 * The Admin class is a model class that inherit the Role class and it stores
 * the data fields of Admin
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Admin extends Role implements Serializable {

    private static final long serialVersionUID = 6543585098267757690L;

    /**
     * Instantiates a new Admin.
     *
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     */
    public Admin(int id, String userName, String password, Phone phone) {
        super("Admin", id, userName, password, phone);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + super.getId() +
                ", username='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", phone=" + super.getPhone() +
                '}';
    }
}