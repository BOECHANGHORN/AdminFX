package Role;

import Agent.Agent;
import Owner.Owner;
import Phone.Phone;
import Tenant.Tenant;

public abstract class Role {
    private String role;
    private int id;
    private String userName;
    private String password;
    private Phone phone;

    public Role(String role, int id, String userName, String password, Phone phone) {
        this.role = role;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}