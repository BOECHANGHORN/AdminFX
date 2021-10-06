package Role;

import Admin.AdminDatabase;
import Agent.Agent;
import Agent.AgentDatabase;
import CSV.ReadWriteRole;
import Owner.Owner;
import Owner.OwnerDatabase;
import Tenant.Tenant;
import Tenant.TenantDatabase;

import java.util.TreeMap;

public class RoleDatabase {
    public static int getNewID(String role) {
        switch (role) {
            case "Agent":
                return AgentDatabase.getInstance().getNewID();
            case "Owner":
                return OwnerDatabase.getInstance().getNewID();
            case "Tenant":
                return TenantDatabase.getInstance().getNewID();
            default:
                return 0;
        }
        /*
            return AdminDatabase.getInstance().getNewID();
         */
    }

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
        }
        /*
        else
            AdminDatabase.getInstance().create((Admin) role);
         */
    }

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
        }
        /*
        else
            AdminDatabase.getInstance().delete((Admin) role);
         */
    }

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
        }
        /*
        else
            AdminDatabase.getInstance().update((Admin) role);
         */
    }

    public static Role searchUser(String username) {
        Role r = AgentDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        r = OwnerDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        r =  TenantDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        return AdminDatabase.getInstance().searchUser(username);
    }

    public static boolean isUserExist(String userName) {
        return searchUser(userName) != null;
    }
}
