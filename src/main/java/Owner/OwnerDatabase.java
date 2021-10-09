package Owner;

import CSV.CSV;
import CSV.ReadWriteRole;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>OwnerDatabase Class</h1>
 * The OwnerDatabase class is a DAO class of the Owner class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Owner object in the CSV.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class OwnerDatabase implements ReadWriteRole<Owner> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//owner.CSV");

    private final TreeMap<Integer, Owner> ownerList;
    private final static OwnerDatabase instance = new OwnerDatabase();

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
        readData();
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
     * Append new data into ownerList
     * call appendData method to append data into CSV
     *
     * @param owner the new Owner object to be written
     */
    @Override
    public void create(Owner owner) {
        ownerList.put(owner.getId(), owner);
        appendData(owner);
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
     * update to change the data in the CSV by
     * calling changeData method
     *
     * @param owner the new Owner object to be update
     */
    @Override
    public void update(Owner owner) {
        changeData(owner);
    }

    /**
     * remove the object from ownerList and
     * delete it from CSV file
     *
     * @param owner the new Owner object to be delete
     */
    @Override
    public void delete(Owner owner) {
        deleteData(owner);
        ownerList.remove(owner.getId());
    }

    /**
     * read data from the CSV file and convert it to
     * Owner object and append into ownerList
     */
    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            int id = Integer.parseInt(rawRow.get(0));
            String userName = rawRow.get(1);
            String password = rawRow.get(2);
            Phone phone = new Phone(rawRow.get(3));

            Owner owner = new Owner(id, userName, password, phone);
            ownerList.put(owner.getId(), owner);
        }
    }

    /**
     * convert Owner object into String Array to be written in
     * the CSV file
     *
     * @param owner the new Owner object to become String Array
     */
    private ArrayList<String> rawOwner(Owner owner) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(owner.getId())); //id
        result.add(owner.getUserName()); //userName
        result.add(owner.getPassword());
        result.add(owner.getPhone().getNumber()); //phone

        return result;
    }

    /**
     * write data from the ownerList to the CSV
     */
    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Owner owner : ownerList.values())
            rawData.add(rawOwner(owner));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    /**
     * write new data to the CSV
     *
     * @param owner the Owner object to be written
     */
    private void appendData(Owner owner) {
        CSV.appendCSV(rawOwner(owner), FILE_PATH);
    }

    /**
     * change data in the CSV
     *
     * @param owner the Owner object to be change
     */
    private void changeData(Owner owner) {
        int pos = ownerList.headMap(owner.getId()).size();
        CSV.changeCSV(rawOwner(owner), pos, FILE_PATH);
    }

    /**
     * delete data in the CSV
     *
     * @param owner the Owner object to be deleted
     */
    private void deleteData(Owner owner) {
        int pos = ownerList.headMap(owner.getId()).size();
        CSV.deleteCSV(pos, FILE_PATH);
    }
}