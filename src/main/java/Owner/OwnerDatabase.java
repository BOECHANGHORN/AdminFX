package Owner;

import ReadWrite.ReadWriteRole;
import Initializer.Initialization;

import java.io.*;
import java.util.TreeMap;

/**
 * <h1>OwnerDatabase Class</h1>
 * The OwnerDatabase class is a DAO class of the Owner class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Owner object in a Serializable file.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class OwnerDatabase implements ReadWriteRole<Owner> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//owner.bin");

    private TreeMap<Integer, Owner> ownerList;
    private final static OwnerDatabase instance = new OwnerDatabase();

    /**
     * A private method to serialize ownerList
     */
    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ownerList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private method to deserialize ownerList
     */
    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ownerList = (TreeMap<Integer, Owner>) ois.readObject();
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
     * instance of OwnerDatabase
     */
    private OwnerDatabase() {
        ownerList = new TreeMap<>();
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
    public static OwnerDatabase getInstance() {
        return instance;
    }

    /**
     * Search and retrieve Owner object by its id
     * return null if not found
     *
     * @param id the id of Owner object
     * @return the Owner object if found else null
     */
    @Override
    public Owner searchByID(int id) {
        return ownerList.get(id);
    }

    /**
     * Search and retrieve Owner object by its user name
     * return null if not found
     *
     * @param userName the user name of Owner object
     * @return the Owner object if found else null
     */
    @Override
    public Owner searchUser(String userName) {
        for (Owner owner : ownerList.values())
            if (owner.getUserName().equals(userName))
                return owner;
        return null;
    }

    /**
     * Get the next new ID for the Owner object by getting the last key of the
     * tree map then + 1
     *
     * @return the next new ID of Owner object
     */
    @Override
    public int getNewID() {
        return ownerList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into ownerList and Serialize
     *
     * @param owner the new Owner object to be written
     */
    @Override
    public void create(Owner owner) {
        ownerList.put(owner.getId(), owner);
        serialize();
    }

    /**
     * Retrieve all the Owner objects
     *
     * @return all the Owner objects
     */
    @Override
    public TreeMap<Integer, Owner> read() {
        return ownerList;
    }

    /**
     * Update data by Serialize the ownerList
     *
     * @param owner the new Owner object to be update
     */
    @Override
    public void update(Owner owner) {
        serialize();
    }

    /**
     * remove the object from ownerList and Serialize
     *
     * @param owner the new Owner object to be delete
     */
    @Override
    public void delete(Owner owner) {
        ownerList.remove(owner.getId());
        serialize();
    }
}