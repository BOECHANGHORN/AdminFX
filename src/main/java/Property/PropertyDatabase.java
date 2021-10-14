package Property;

import ReadWrite.ReadWrite;
import Initializer.Initialization;

import java.io.*;
import java.util.TreeMap;

/**
 * <h1>PropertyDatabase Class</h1>
 * The PropertyDatabase class is a DAO class of the Property class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Property object in the CSV.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class PropertyDatabase implements ReadWrite<Property> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//property.bin");

    private TreeMap<Integer, Property> propertyList;
    private static final PropertyDatabase instance = new PropertyDatabase();

    /**
     * A private method to serialize agentList
     */
    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(propertyList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A private method to deserialize agentList
     */
    private void deserialize() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            propertyList = (TreeMap<Integer, Property>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            //No serialization found
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setReference();
    }

    /**
     * A private method to reference all the aggregation
     */
    private void setReference() {
        for (Property property : propertyList.values()) {
            property.setAgentViaID();
            property.setOwnerViaID();
            property.setTenantViaID();
        }
    }

    /**
     * A private method that initialize the singleton
     * instance of PropertyDatabase
     */
    private PropertyDatabase() {
        propertyList = new TreeMap<>();
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
    public static PropertyDatabase getInstance() {
        return instance;
    }

    /**
     * Search and retrieve Property object by its id
     * return null if not found
     *
     * @param id the id of Property object
     * @return the Property object if found else null
     */
    public Property searchByID(int id) {
        return propertyList.get(id);
    }

    /**
     * Get the next new ID for the Property object by getting the last key of the
     * tree map then + 1
     *
     * @return the next new ID of Property object
     */
    @Override
    public int getNewID() {
        return propertyList.size() == 0 ? 1 : propertyList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into propertyList and Serialize
     *
     * @param property the new Property object to be written
     */
    @Override
    public void create(Property property) {
        propertyList.put(property.getId(), property);
        serialize();
    }

    /**
     * Retrieve all the Property objects
     *
     * @return all the Property objects
     */
    @Override
    public TreeMap<Integer, Property> read() {
        return new TreeMap<>(propertyList);
    }

    /**
     * update data by serialize the propertyList
     *
     * @param property the new Property object to be update
     */
    @Override
    public void update(Property property) {
        serialize();
    }

    /**
     * remove the object from propertyList and serialize
     *
     * @param property the new Property object to be delete
     */
    @Override
    public void delete(Property property) {
        propertyList.remove(property.getId());
        serialize();
    }

}