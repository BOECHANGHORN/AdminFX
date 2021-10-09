package Property;

import Agent.Agent;
import Agent.AgentDatabase;
import CSV.CSV;
import CSV.ReadWrite;
import Initializer.Initialization;
import Owner.Owner;
import Owner.OwnerDatabase;
import Tenant.Tenant;
import Tenant.TenantDatabase;

import java.io.File;
import java.util.ArrayList;
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
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//property.CSV");

    private final TreeMap<Integer, Property> propertyList;
    private static final PropertyDatabase instance = new PropertyDatabase();

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
        readData();
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static PropertyDatabase getInstance() {
        return instance;
    }

    // All the methods require to create instances

    /**
     * Get owner object from id from OwnerDatabase
     *
     * @param id the owner id
     * @return the owner object
     */
    private Owner getOwner(int id) {
        return OwnerDatabase.getInstance().searchByID(id);
    }

    /**
     * Get agent object from id from AgentDatabase
     *
     * @param id the agent id
     * @return the agent object
     */
    private Agent getAgent(int id) {
        return AgentDatabase.getInstance().searchByID(id);
    }

    /**
     * Get tenant object from id from TenantDatabase
     *
     * @param id the tenant id
     * @return the tenant object
     */
    private Tenant getTenant(int id) {
        return TenantDatabase.getInstance().searchByID(id);
    }
    //Done

    //Method to unpack/pack address and facilities

    /**
     * Convert the rawString that retrieved from CSV
     * to an Address object
     *
     * @param rawAddress raw address String
     * @return the Address object
     */
    private PropertyAddress unpackAddress(String rawAddress) {
        rawAddress = rawAddress.substring(1, rawAddress.length() - 1);
        String[] splited = rawAddress.split("&");

        return new PropertyAddress(splited[0], splited[1], splited[2]);
    }

    /**
     * Convert the address to raw address string to be
     * written in CSV
     *
     * @param address address object
     * @return the raw address String
     */
    private String packAddress(PropertyAddress address) {

        return "[" + address.getDetailAddress() + "&" +
                address.getPostalCode() + "&" +
                address.getState() + "]";
    }

    /**
     * Convert the rawString that retrieved from CSV
     * to a PropertyFacilities object
     *
     * @param rawFacilities raw facilities String
     * @return the PropertyFacilities object
     */
    private PropertyFacilities unpackFacilities(String rawFacilities) {
        rawFacilities = rawFacilities.substring(1, rawFacilities.length() - 1);
        String[] splited = rawFacilities.split("&");

        boolean swimmingPool = splited[0].equals("t");
        boolean wifi = splited[1].equals("t");
        int tv = Integer.parseInt(splited[2]);
        int fridge = Integer.parseInt(splited[3]);
        int airCond = Integer.parseInt(splited[4]);
        int waterHeater = Integer.parseInt(splited[5]);

        return new PropertyFacilities(swimmingPool, wifi, tv, fridge, airCond, waterHeater);
    }

    /**
     * Convert the PropertyFacilities object to
     * rawString to be written in CSV
     *
     * @param facilities the PropertyFacilities object
     * @return raw facilities String
     */
    private String packFacilities(PropertyFacilities facilities) {

        return "[" + (facilities.isSwimmingPool() ? "t" : "f") + "&" +
                (facilities.isWifi() ? "t" : "f") + "&" +
                facilities.getTv() + "&" +
                facilities.getFridge() + "&" +
                facilities.getAirCond() + "&" +
                facilities.getWaterHeater() + "]";
    }
    //Done

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
        return propertyList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into propertyList
     * call appendData method to append data into CSV
     *
     * @param property the new Property object to be written
     */
    @Override
    public void create(Property property) {
        propertyList.put(property.getId(), property);
        appendData(property);
    }

    /**
     * Retrieve all the Property objects
     *
     * @return all the Property objects
     */
    @Override
    public TreeMap<Integer, Property> read() {
        return propertyList;
    }

    /**
     * update to change the data in the CSV by
     * calling changeData method
     *
     * @param property the new Property object to be update
     */
    @Override
    public void update(Property property) {
        changeData(property);
    }

    /**
     * remove the object from propertyList and
     * delete it from CSV file
     *
     * @param property the new Property object to be delete
     */
    @Override
    public void delete(Property property) {
        deleteData(property);
        propertyList.remove(property.getId());
    }

    /**
     * read data from the CSV file and convert it to
     * Property object and append into propertyList
     */
    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            PropertyBuilder pb = new PropertyBuilder(Integer.parseInt(rawRow.get(0)), rawRow.get(1));
            pb.setType(PropertyType.valueOf(rawRow.get(2)));
            pb.setOwner(getOwner(Integer.parseInt(rawRow.get(3))));
            pb.setAgent(getAgent(Integer.parseInt(rawRow.get(4))));
            pb.setRoomNum(Integer.parseInt(rawRow.get(5)));
            pb.setBathRoomNum(Integer.parseInt(rawRow.get(6)));
            pb.setAddress(unpackAddress(rawRow.get(7)));
            pb.setSize(Integer.parseInt(rawRow.get(8)));
            pb.setFacilities(unpackFacilities(rawRow.get(9)));

            if (!rawRow.get(10).isEmpty())
                pb.setTenant(getTenant(Integer.parseInt(rawRow.get(10))));

            if (!rawRow.get(11).isEmpty())
                pb.setComment(rawRow.get(11));

            pb.setRate(Double.parseDouble(rawRow.get(12)));
            pb.setPublished(rawRow.get(13).equals("t"));

            Property property = new Property(pb);
            propertyList.put(property.getId(), property);
        }
    }

    /**
     * convert Property object into String Array to be written in
     * the CSV file
     *
     * @param property the new Property object to become String Array
     */
    private ArrayList<String> rawProperty(Property property) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(property.getId()));
        result.add(property.getName());
        result.add(property.getType().toString());
        result.add(property.getOwner().getId() + "");
        result.add(property.getAgent().getId() + "");
        result.add(Integer.toString(property.getRoomNum()));
        result.add(Integer.toString(property.getBathRoomNum()));
        result.add(packAddress(property.getAddress()));
        result.add(Integer.toString(property.getSize()));
        result.add(packFacilities(property.getFacilities()));

        if (property.getTenant() != null)
            result.add(property.getTenant().getId() + "");
        else
            result.add("");

        if (property.getComment() != null)
            result.add(property.getComment());
        else
            result.add("");

        result.add(Double.toString(property.getRate()));
        result.add(property.isPublished() ? "t" : "f");
        return result;
    }

    /**
     * write data from the propertyList to the CSV
     */
    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Property property : propertyList.values())
            rawData.add(rawProperty(property));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    /**
     * write new data to the CSV
     *
     * @param property the Property object to be written
     */
    private void appendData(Property property) {
        CSV.appendCSV(rawProperty(property), FILE_PATH);
    }

    /**
     * change data in the CSV
     *
     * @param property the Property object to be change
     */
    private void changeData(Property property) {
        int pos = propertyList.headMap(property.getId()).size();
        CSV.changeCSV(rawProperty(property), pos, FILE_PATH);
    }

    /**
     * delete data in the CSV
     *
     * @param property the Property object to be deleted
     */
    private void deleteData(Property property) {
        int pos = propertyList.headMap(property.getId()).size();
        CSV.deleteCSV(pos, FILE_PATH);
    }

}