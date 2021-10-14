package Admin;

import ReadWrite.ReadWriteRole;
import Initializer.Initialization;

import java.io.*;
import java.util.TreeMap;

/**
 * <h1>AdminDatabase Class</h1>
 * The AdminDatabase class is a DAO class of the Admin class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Admin object in a Serializable file.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class AdminDatabase implements ReadWriteRole<Admin> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//admin.bin");

    private TreeMap<Integer, Admin> adminList;
    private final static AdminDatabase instance = new AdminDatabase();

    /**
     * A private method to serialize adminList
     */
    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(adminList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private method to deserialize adminList
     */
    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            adminList = (TreeMap<Integer, Admin>) ois.readObject();
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
     * instance of AdminDatabase
     */
    private AdminDatabase() {
        adminList = new TreeMap<>();
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
    public static AdminDatabase getInstance() {
        return instance;
    }

    /**
     * Search and retrieve Admin object by its id
     * return null if not found
     *
     * @param id the id of Admin object
     * @return the Admin object if found else null
     */
    @Override
    public Admin searchByID(int id) {
        return adminList.get(id);
    }

    /**
     * Search and retrieve Admin object by its user name
     * return null if not found
     *
     * @param userName the user name of Admin object
     * @return the Admin object if found else null
     */
    @Override
    public Admin searchUser(String userName) {
        for (Admin admin : adminList.values())
            if (admin.getUserName().equals(userName))
                return admin;
        return null;
    }

    /**
     * Get the next new ID for the Admin object by getting the last key of the
     * tree map then + 1
     *
     * @return the next new ID of Admin object
     */
    @Override
    public int getNewID() {
        return adminList.size() == 0 ? 1 : adminList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into adminList and Serialize
     *
     * @param admin the new Admin object to be written
     */
    @Override
    public void create(Admin admin) {
        adminList.put(admin.getId(), admin);
        serialize();
    }

    /**
     * Retrieve all the Admin objects
     *
     * @return all the Admin objects
     */
    @Override
    public TreeMap<Integer, Admin> read() {
        return new TreeMap<>(adminList);
    }

    /**
     * Update data by Serialize the adminList
     *
     * @param admin the new Admin object to be update
     */
    @Override
    public void update(Admin admin) {
        serialize();
    }

    /**
     * remove the object from adminList and Serialize
     *
     * @param admin the new Admin object to be delete
     */
    @Override
    public void delete(Admin admin) {
        adminList.remove(admin.getId());
        serialize();
    }
}