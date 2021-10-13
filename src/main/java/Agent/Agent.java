package Agent;

import Phone.Phone;
import Role.Role;

import java.io.Serializable;


/**
 * <h1>Agent Class</h1>
 * The Agent class is a model class that inherit the Role class and it stores
 * the data fields of Agent
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Agent extends Role implements Serializable {

    private static final long serialVersionUID = 6543585098267757691L;
    /**
     * Instantiates a new Agent.
     *
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     */
    public Agent(int id, String userName, String password, Phone phone) {
        super("Agent", id, userName, password, phone);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + super.getId() +
                ", username='" + super.getUserName() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", phone=" + super.getPhone() +
                '}';
    }
}