package Tenant;

import Phone.Phone;
import Role.Role;

public class Tenant extends Role {
    public Tenant(int id, String userName, String password, Phone phone) {
        super("Tenant", id, userName, password, phone);
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