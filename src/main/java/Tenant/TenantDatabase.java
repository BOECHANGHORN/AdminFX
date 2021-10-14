package Tenant;

import ReadWrite.ReadWriteRole;
import Initializer.Initialization;

import java.io.*;
import java.util.TreeMap;

/**
 * <h1>TenantDatabase Class</h1>
 * The TenantDatabase class is a DAO class of the Tenant class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Tenant object in a Serializable file.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class TenantDatabase implements ReadWriteRole<Tenant> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//tenant.bin");

    private TreeMap<Integer, Tenant> tenantList;
    private final static TenantDatabase instance = new TenantDatabase();

    /**
     * A private method to serialize tenantList
     */
    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tenantList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private method to deserialize tenantList
     */
    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tenantList = (TreeMap<Integer, Tenant>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            //No serialization found
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private method that initialize the singleton
     * instance of TenantDatabase
     */
    private TenantDatabase() {
        tenantList = new TreeMap<>();
    }

    /**
     * A public method that initialize the DAO object by reading (retrieving)
     * data from the CSV file
     */
    @Override
    public void init() {
        deserialize();
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static TenantDatabase getInstance() {
        return instance;
    }

    /**
     * Search and retrieve Tenant object by its id
     * return null if not found
     *
     * @param id the id of Tenant object
     * @return the Tenant object if found else null
     */
    @Override
    public Tenant searchByID(int id) {
        return tenantList.get(id);
    }

    /**
     * Search and retrieve Tenant object by its user name
     * return null if not found
     *
     * @param userName the user name of Tenant object
     * @return the Tenant object if found else null
     */
    @Override
    public Tenant searchUser(String userName) {
        for (Tenant tenant : tenantList.values())
            if (tenant.getUserName().equals(userName))
                return tenant;
        return null;
    }

    /**
     * Get the next new ID for the Tenant object by getting the last key of the
     * tree map then + 1
     *
     * @return the next new ID of Tenant object
     */
    @Override
    public int getNewID() {
        return tenantList.size() == 0 ? 1: tenantList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into tenantList and Serialize
     *
     * @param tenant the new Tenant object to be written
     */
    @Override
    public void create(Tenant tenant) {
        tenantList.put(tenant.getId(), tenant);
        serialize();
    }

    /**
     * Retrieve all the Tenant objects
     *
     * @return all the Tenant objects
     */
    @Override
    public TreeMap<Integer, Tenant> read() {
        return new TreeMap<>(tenantList);
    }

    /**
     * Update data by Serialize the tenantList
     *
     * @param tenant the new Tenant object to be update
     */
    @Override
    public void update(Tenant tenant) {
        serialize();
    }

    /**
     * remove the object from tenantList and Serialize
     *
     * @param tenant the new Tenant object to be delete
     */
    @Override
    public void delete(Tenant tenant) {
        tenantList.remove(tenant.getId());
        serialize();
    }
}