package Role;

import Admin.Admin;
import Admin.AdminDatabase;
import Agent.Agent;
import Agent.AgentDatabase;
import Owner.Owner;
import Owner.OwnerDatabase;
import Tenant.Tenant;
import Tenant.TenantDatabase;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>RoleDatabase Class</h1>
 * The RoleDatabase class is a Facade design pattern class
 * to call all the Role subclass database
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class RoleDatabase {

    /**
     * Gets new id for specific role
     *
     * @param role the role
     * @return the new id
     */
    public static int getNewID(String role) {
        switch (role) {
            case "Agent":
                return AgentDatabase.getInstance().getNewID();
            case "Owner":
                return OwnerDatabase.getInstance().getNewID();
            case "Tenant":
                return TenantDatabase.getInstance().getNewID();
            default:
                return AdminDatabase.getInstance().getNewID();
        }
    }

    /**
     * Create new role in database
     *
     * @param role the role object
     */
    public static void create(Role role) {
        switch (role.getRole()) {
            case "Agent":
                AgentDatabase.getInstance().create((Agent) role);
                break;
            case "Owner":
                OwnerDatabase.getInstance().create((Owner) role);
                break;
            case "Tenant":
                TenantDatabase.getInstance().create((Tenant) role);
                break;
            default:
                AdminDatabase.getInstance().create((Admin) role);
                break;
        }
    }

    /**
     * Delete role in database
     *
     * @param role the role object
     */
    public static void delete(Role role) {
        switch (role.getRole()) {
            case "Agent":
                AgentDatabase.getInstance().delete((Agent) role);
                break;
            case "Owner":
                OwnerDatabase.getInstance().delete((Owner) role);
                break;
            case "Tenant":
                TenantDatabase.getInstance().delete((Tenant) role);
                break;
            default:
                AdminDatabase.getInstance().delete((Admin) role);
                break;
        }


    }

    /**
     * Update role in database
     *
     * @param role the role object
     */
    public static void update(Role role) {
        switch (role.getRole()) {
            case "Agent":
                AgentDatabase.getInstance().update((Agent) role);
                break;
            case "Owner":
                OwnerDatabase.getInstance().update((Owner) role);
                break;
            case "Tenant":
                TenantDatabase.getInstance().update((Tenant) role);
                break;
            default:
                AdminDatabase.getInstance().update((Admin) role);
                break;
        }

    }

    /**
     * Retrieve all the specific Role objects
     *
     * @param role the specific role
     * @return all the specific role objects
     */
    public static ArrayList<Role> read(String role) {
        switch (role) {
            case "Agent":
                return new ArrayList<>(AgentDatabase.getInstance().read().values());
            case "Owner":
                return new ArrayList<>(OwnerDatabase.getInstance().read().values());
            case "Tenant":
                return new ArrayList<>(TenantDatabase.getInstance().read().values());
            default:
                return new ArrayList<>(AdminDatabase.getInstance().read().values());
        }

    }

    /**
     * Search user name and get the role object
     *
     * @param username the username
     * @return the role object if found else null
     */
    public static Role searchUser(String username) {
        Role r = AgentDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        r = OwnerDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        r = TenantDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        return AdminDatabase.getInstance().searchUser(username);
    }

    /**
     * Is user exist by user name
     *
     * @param username the username
     * @return the existence
     */
    public static boolean isUserExist(String username) {
        return searchUser(username) != null;
    }
}