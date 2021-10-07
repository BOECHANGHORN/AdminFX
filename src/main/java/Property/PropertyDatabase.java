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

public class PropertyDatabase implements ReadWrite<Property> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//property.CSV");

    private final TreeMap<Integer, Property> propertyList;
    private static final PropertyDatabase instance = new PropertyDatabase();

    private PropertyDatabase() {
        propertyList = new TreeMap<>();
    }

    @Override
    public void init() {
        readData();
    }

    public static PropertyDatabase getInstance() {
        return instance;
    }

    // All the methods require to create instances
    private Owner getOwner(int id) {
        return OwnerDatabase.getInstance().searchByID(id);
    }

    private Agent getAgent(int id) {
        return AgentDatabase.getInstance().searchByID(id);
    }

    private Tenant getTenant(int id) {
        return TenantDatabase.getInstance().searchByID(id);
    }
    //Done

    //Method to unpack/pack address and facilities
    private PropertyAddress unpackAddress(String rawAddress) {
        rawAddress = rawAddress.substring(1, rawAddress.length() - 1);
        String[] splited = rawAddress.split("&");

        return new PropertyAddress(splited[0], splited[1], splited[2]);
    }

    private String packAddress(PropertyAddress address) {

        return "[" + address.getDetailAddress() + "&" +
                address.getPostalCode() + "&" +
                address.getState() + "]";
    }

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

    private String packFacilities(PropertyFacilities facilities) {

        return "[" + (facilities.isSwimmingPool() ? "t" : "f") + "&" +
                (facilities.isWifi() ? "t" : "f") + "&" +
                facilities.getTv() + "&" +
                facilities.getFridge() + "&" +
                facilities.getAirCond() + "&" +
                facilities.getWaterHeater() + "]";
    }
    //Done

    public Property searchByID(int id) {
        return propertyList.get(id);
    }

    @Override
    public int getNewID() {
        return propertyList.lastKey() + 1;
    }

    //CRUD
    @Override
    public void create(Property property) {
        propertyList.put(property.getId(), property);
        appendData(property);
    }

    @Override
    public TreeMap<Integer, Property> read() {
        return propertyList;
    }

    @Override
    public void update(Property property) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Property property) {
        propertyList.remove(property.getId());
        writeData();
    }


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

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Property property : propertyList.values())
            rawData.add(rawProperty(property));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Property property) {
        CSV.appendCSV(rawProperty(property), FILE_PATH);
    }

}