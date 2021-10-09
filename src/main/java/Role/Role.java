package Role;

import Agent.Agent;
import Owner.Owner;
import Phone.Phone;
import Tenant.Tenant;

/**
 * <h1>Role Class</h1>
 * The Role class is an abstract model class stores
 * the data fields of Role sublcass
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public abstract class Role {
    private String role;
    private int id;
    private String userName;
    private String password;
    private Phone phone;

    /**
     * Instantiates a new Role.
     *
     * @param role     the role
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     */
    public Role(String role, int id, String userName, String password, Phone phone) {
        this.role = role;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    /**
     * Instantiate a new role subclass object
     *
     * @param role     the role
     * @param id       the id
     * @param userName the user name
     * @param password the password
     * @param phone    the phone
     * @return the new upcasted to role object
     */
    public static Role newRole(String role, int id, String userName, String password, Phone phone) {
        switch (role) {
            case "Agent":
                return new Agent(id, userName, password, phone);
            case "Owner":
                return new Owner(id, userName, password, phone);
            case "Tenant":
                return new Tenant(id, userName, password, phone);
            default:
                return null;
        }
        //  return new Admin(id, userName, password, phone);
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}