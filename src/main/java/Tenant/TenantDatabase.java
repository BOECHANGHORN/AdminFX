package Tenant;

import CSV.CSV;
import CSV.ReadWriteRole;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>TenantDatabase Class</h1>
 * The TenantDatabase class is a DAO class of the Tenant class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Tenant object in the CSV.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class TenantDatabase implements ReadWriteRole<Tenant> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//tenant.CSV");

    private final TreeMap<Integer, Tenant> tenantList;
    private final static TenantDatabase instance = new TenantDatabase();

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
        readData();
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
        return tenantList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into tenantList
     * call appendData method to append data into CSV
     *
     * @param tenant the new Tenant object to be written
     */
    @Override
    public void create(Tenant tenant) {
        tenantList.put(tenant.getId(), tenant);
        appendData(tenant);
    }

    /**
     * Retrieve all the Tenant objects
     *
     * @return all the Tenant objects
     */
    @Override
    public TreeMap<Integer, Tenant> read() {
        return tenantList;
    }

    /**
     * update to change the data in the CSV by
     * calling changeData method
     *
     * @param tenant the new Tenant object to be update
     */
    @Override
    public void update(Tenant tenant) {
        changeData(tenant);
    }

    /**
     * remove the object from tenantList and
     * delete it from CSV file
     *
     * @param tenant the new Tenant object to be delete
     */
    @Override
    public void delete(Tenant tenant) {
        deleteData(tenant);
        tenantList.remove(tenant.getId());
    }

    /**
     * read data from the CSV file and convert it to
     * Tenant object and append into tenantList
     */
    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            int id = Integer.parseInt(rawRow.get(0));
            String userName = rawRow.get(1);
            String password = rawRow.get(2);
            Phone phone = new Phone(rawRow.get(3));

            Tenant tenant = new Tenant(id, userName, password, phone);
            tenantList.put(tenant.getId(), tenant);
        }
    }

    /**
     * convert Tenant object into String Array to be written in
     * the CSV file
     *
     * @param tenant the new Tenant object to become String Array
     */
    private ArrayList<String> rawTenant(Tenant tenant) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(tenant.getId())); //id
        result.add(tenant.getUserName()); //userName
        result.add(tenant.getPassword());
        result.add(tenant.getPhone().getNumber()); //phone

        return result;
    }

    /**
     * write data from the tenantList to the CSV
     */
    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Tenant tenant : tenantList.values())
            rawData.add(rawTenant(tenant));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    /**
     * write new data to the CSV
     *
     * @param tenant the Tenant object to be written
     */
    private void appendData(Tenant tenant) {
        CSV.appendCSV(rawTenant(tenant), FILE_PATH);
    }

    /**
     * change data in the CSV
     *
     * @param tenant the Tenant object to be change
     */
    private void changeData(Tenant tenant) {
        int pos = tenantList.headMap(tenant.getId()).size();
        CSV.changeCSV(rawTenant(tenant), pos, FILE_PATH);
    }

    /**
     * delete data in the CSV
     *
     * @param tenant the Tenant object to be deleted
     */
    private void deleteData(Tenant tenant) {
        int pos = tenantList.headMap(tenant.getId()).size();
        CSV.deleteCSV(pos, FILE_PATH);
    }
}