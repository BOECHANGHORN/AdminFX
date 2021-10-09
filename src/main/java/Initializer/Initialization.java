package Initializer;

import Admin.AdminDatabase;
import Agent.AgentDatabase;
import Owner.OwnerDatabase;
import Property.PropertyDatabase;
import Tenant.TenantDatabase;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>Initialization Class</h1>
 * The Initialization Class served as a purpose to initialize
 * the database by retrieving data from CSV
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Initialization {
    public final static String CUR_PATH = getPreviousPath();

    /**
     * get the previous path (directory) in order to access the data
     *
     * @return the previous path/data folder
     */
    public static String getPreviousPath() {
        Path p1 = Paths.get(System.getProperty("user.dir")).getParent();
        return p1 + "//data";
    }

    /**
     * initialize by calling all the database instance to initialize
     */
    public static void initialization() {
        AgentDatabase.getInstance().init();
        OwnerDatabase.getInstance().init();
        TenantDatabase.getInstance().init();
        PropertyDatabase.getInstance().init();
        AdminDatabase.getInstance().init();
    }
}