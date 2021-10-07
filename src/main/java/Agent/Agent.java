package Agent;

import Phone.Phone;
import Role.Role;

public class Agent extends Role {

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