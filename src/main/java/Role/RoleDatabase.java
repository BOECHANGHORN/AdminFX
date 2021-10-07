package Role;

import Admin.Admin;
import Admin.AdminDatabase;
import Agent.Agent;
import Agent.AgentDatabase;
import Owner.Owner;
import Owner.OwnerDatabase;
import Tenant.Tenant;
import Tenant.TenantDatabase;

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
                return AdminDatabase.getInstance().getNewID();
        }
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
            default:
                AdminDatabase.getInstance().create((Admin) role);
                break;
        }
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
            default:
                AdminDatabase.getInstance().delete((Admin) role);
                break;
        }


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
            default:
                AdminDatabase.getInstance().update((Admin) role);
                break;
        }

    }

    public static Role searchUser(String username) {
        Role r = AgentDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        r = OwnerDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        r = TenantDatabase.getInstance().searchUser(username);
        if (r != null) return r;
        return AdminDatabase.getInstance().searchUser(username);
    }

    public static boolean isUserExist(String username) {
        return searchUser(username) != null;
    }
}