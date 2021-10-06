package Admin;

import Phone.Phone;
import Role.Role;

public class Admin extends Role {

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
