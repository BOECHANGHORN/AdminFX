package Utils;

import Role.Role;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

/**
 * <h1>RoleStringConverter</h1>
 * The RoleStringConverter class is a class that store data fields
 * of RoleStringConverter
 *
 * @author Chan Yun Hong
 * @version 1.0
 * @since 2021-10-11
 */
public class RoleStringConverter extends StringConverter<Role> {
    private ObservableList<Role> roleList;

    /**
     * Sets role list.
     *
     * @param roleList the role list
     */
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
