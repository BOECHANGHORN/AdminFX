package Admin;

import CSV.*;
import Initializer.Initialization;
import Phone.Phone;
import Role.Role;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class AdminDatabase implements ReadWriteRole<Admin> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//admin.CSV");

    private final TreeMap<Integer, Admin> adminList;
    private final static AdminDatabase instance = new AdminDatabase();

    private AdminDatabase () {
        adminList = new TreeMap<Integer, Admin>();
    }

    @Override
    public void init() {
        readData();
    }

    public static AdminDatabase getInstance() {
        return instance;
    }

    @Override
    public Admin searchByID(int id) {
        return adminList.get(id);
    }

    @Override
    public Admin searchUser(String userName) {
        for (Admin admin : adminList.values())
            if (admin.getUserName().equals(userName))
                return admin;
        return null;
    }

    @Override
    public int getNewID() {
        return adminList.lastKey() + 1;
    }

    //CRUD
    @Override
    public void create(Admin admin) {
        adminList.put(admin.getId(), admin);
        appendData(admin);
    }

    @Override
    public TreeMap<Integer, Admin> read() {
        return adminList;
    }

    @Override
    public void update(Admin admin) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Admin admin) {
        adminList.remove(admin.getId());
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

            Admin admin = new Admin(id, userName, password, phone);
            adminList.put(admin.getId(), admin);
        }
    }

    private ArrayList<String> rawAdmin (Admin admin) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(admin.getId())); //id
        result.add(admin.getUserName()); //userName
        result.add(admin.getPassword());
        result.add(admin.getPhone().getNumber()); //phone

        return result;
    }

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Admin admin : adminList.values())
            rawData.add(rawAdmin(admin));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Admin admin) {
        CSV.appendCSV(rawAdmin(admin), FILE_PATH);
    }
}