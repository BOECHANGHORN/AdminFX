package Tenant;

import CSV.*;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class TenantDatabase implements ReadWriteRole<Tenant> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//tenant.CSV");

    private final TreeMap<Integer, Tenant> tenantList;
    private static final TenantDatabase instance = new TenantDatabase();

    private TenantDatabase () {
        tenantList = new TreeMap<Integer, Tenant>();
    }

    @Override
    public void init() {
        readData();
    }

    public static TenantDatabase getInstance() {
        return instance;
    }

    @Override
    public Tenant searchByID(int id) {
        return tenantList.get(id);
    }

    @Override
    public Tenant searchUser(String userName) {
        for (Tenant tenant : tenantList.values())
            if (tenant.getUserName().equals(userName))
                return tenant;
        return null;
    }

    @Override
    public int getNewID() {
        return tenantList.lastKey() + 1;
    }

    //CRUD
    @Override
    public void create(Tenant tenant) {
        tenantList.put(tenant.getId(), tenant);
        appendData(tenant);
    }

    @Override
    public TreeMap<Integer, Tenant> read() {
        return tenantList;
    }

    @Override
    public void update(Tenant tenant) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Tenant tenant) {
        tenantList.remove(tenant.getId());
        writeData();
    }

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

    private ArrayList<String> rawTenant (Tenant tenant) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(tenant.getId())); //id
        result.add(tenant.getUserName()); //userName
        result.add(tenant.getPassword());
        result.add(tenant.getPhone().getNumber()); //phone

        return result;
    }

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Tenant tenant : tenantList.values())
            rawData.add(rawTenant(tenant));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Tenant tenant) {
        CSV.appendCSV(rawTenant(tenant), FILE_PATH);
    }

}