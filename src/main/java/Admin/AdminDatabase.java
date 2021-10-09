package Admin;

import CSV.CSV;
import CSV.ReadWriteRole;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>AdminDatabase Class</h1>
 * The AdminDatabase class is a DAO class of the Admin class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Admin object in the CSV.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class AdminDatabase implements ReadWriteRole<Admin> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//admin.CSV");

    private final TreeMap<Integer, Admin> adminList;
    private final static AdminDatabase instance = new AdminDatabase();

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
        readData();
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
        return adminList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into adminList
     * call appendData method to append data into CSV
     *
     * @param admin the new Admin object to be written
     */
    @Override
    public void create(Admin admin) {
        adminList.put(admin.getId(), admin);
        appendData(admin);
    }

    /**
     * Retrieve all the Admin objects
     *
     * @return all the Admin objects
     */
    @Override
    public TreeMap<Integer, Admin> read() {
        return adminList;
    }

    /**
     * update to change the data in the CSV by
     * calling changeData method
     *
     * @param admin the new Admin object to be update
     */
    @Override
    public void update(Admin admin) {
        changeData(admin);
    }

    /**
     * remove the object from adminList and
     * delete it from CSV file
     *
     * @param admin the new Admin object to be delete
     */
    @Override
    public void delete(Admin admin) {
        deleteData(admin);
        adminList.remove(admin.getId());
    }

    /**
     * read data from the CSV file and convert it to
     * Admin object and append into adminList
     */
    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            int id = Integer.parseInt(rawRow.get(0));
            String userName = rawRow.get(1);
            String password = rawRow.get(2);
            Phone phone = new Phone(rawRow.get(3));

            Admin admin = new Admin(id, userName, password, phone);
            adminList.put(admin.getId(), admin);
        }
    }

    /**
     * convert Admin object into String Array to be written in
     * the CSV file
     *
     * @param admin the new Admin object to become String Array
     */
    private ArrayList<String> rawAdmin(Admin admin) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(admin.getId())); //id
        result.add(admin.getUserName()); //userName
        result.add(admin.getPassword());
        result.add(admin.getPhone().getNumber()); //phone

        return result;
    }

    /**
     * write data from the adminList to the CSV
     */
    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Admin admin : adminList.values())
            rawData.add(rawAdmin(admin));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    /**
     * write new data to the CSV
     *
     * @param admin the Admin object to be written
     */
    private void appendData(Admin admin) {
        CSV.appendCSV(rawAdmin(admin), FILE_PATH);
    }

    /**
     * change data in the CSV
     *
     * @param admin the Admin object to be change
     */
    private void changeData(Admin admin) {
        int pos = adminList.headMap(admin.getId()).size();
        CSV.changeCSV(rawAdmin(admin), pos, FILE_PATH);
    }

    /**
     * delete data in the CSV
     *
     * @param admin the Admin object to be deleted
     */
    private void deleteData(Admin admin) {
        int pos = adminList.headMap(admin.getId()).size();
        CSV.deleteCSV(pos, FILE_PATH);
    }
}