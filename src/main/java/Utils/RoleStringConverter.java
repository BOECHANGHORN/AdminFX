package Utils;

import Role.Role;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

public class RoleStringConverter extends StringConverter<Role> {
    private ObservableList<Role> roleList;

    public void setRoleList(ObservableList<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString(Role x) {
        return x == null ? null : x.getUserName();
    }

    @Override
    public Role fromString(String s) {
        if (roleList == null)
            return null;

        for (Role role : roleList)
            if (role.getUserName().equals(s))
                return role;

        return null;
    }
}
