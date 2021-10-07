package Owner;

import Phone.Phone;
import Role.Role;

public class Owner extends Role {
    public Owner(int id, String userName, String password, Phone phone) {
        super("Owner", id, userName, password, phone);
    }

    @Override
    public String getUserName() {
        return super.getUserName();
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