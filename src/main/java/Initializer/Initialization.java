package Initializer;

import Admin.AdminDatabase;
import Agent.AgentDatabase;
import Owner.OwnerDatabase;
import Property.PropertyDatabase;
import Tenant.TenantDatabase;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Initialization {
    public final static String CUR_PATH = getPreviousPath();

    public static String getPreviousPath() {
        Path p1 = Paths.get(System.getProperty("user.dir")).getParent();
        return p1+ "//data";
    }

    public static void initialization() {
        AgentDatabase.getInstance().init();
        OwnerDatabase.getInstance().init();
        TenantDatabase.getInstance().init();
        PropertyDatabase.getInstance().init();
        AdminDatabase.getInstance().init();
    }
}
