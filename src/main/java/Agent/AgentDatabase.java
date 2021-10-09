package Agent;

import CSV.CSV;
import CSV.ReadWriteRole;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * <h1>AgentDatabase Class</h1>
 * The AgentDatabase class is a DAO class of the Agent class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Agent object in the CSV.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class AgentDatabase implements ReadWriteRole<Agent> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//agent.CSV");

    private final TreeMap<Integer, Agent> agentList;
    private final static AgentDatabase instance = new AgentDatabase();

    /**
     * A private method that initialize the singleton
     * instance of AgentDatabase
     */
    private AgentDatabase() {
        agentList = new TreeMap<>();
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
    public static AgentDatabase getInstance() {
        return instance;
    }

    /**
     * Search and retrieve Agent object by its id
     * return null if not found
     *
     * @param id the id of Agent object
     * @return the Agent object if found else null
     */
    @Override
    public Agent searchByID(int id) {
        return agentList.get(id);
    }

    /**
     * Search and retrieve Agent object by its user name
     * return null if not found
     *
     * @param userName the user name of Agent object
     * @return the Agent object if found else null
     */
    @Override
    public Agent searchUser(String userName) {
        for (Agent agent : agentList.values())
            if (agent.getUserName().equals(userName))
                return agent;
        return null;
    }

    /**
     * Get the next new ID for the Agent object by getting the last key of the
     * tree map then + 1
     *
     * @return the next new ID of Agent object
     */
    @Override
    public int getNewID() {
        return agentList.lastKey() + 1;
    }

    //CRUD

    /**
     * Append new data into agentList
     * call appendData method to append data into CSV
     *
     * @param agent the new Agent object to be written
     */
    @Override
    public void create(Agent agent) {
        agentList.put(agent.getId(), agent);
        appendData(agent);
    }

    /**
     * Retrieve all the Agent objects
     *
     * @return all the Agent objects
     */
    @Override
    public TreeMap<Integer, Agent> read() {
        return agentList;
    }

    /**
     * update to change the data in the CSV by
     * calling changeData method
     *
     * @param agent the new Agent object to be update
     */
    @Override
    public void update(Agent agent) {
        changeData(agent);
    }

    /**
     * remove the object from agentList and
     * delete it from CSV file
     *
     * @param agent the new Agent object to be delete
     */
    @Override
    public void delete(Agent agent) {
        deleteData(agent);
        agentList.remove(agent.getId());
    }

    /**
     * read data from the CSV file and convert it to
     * Agent object and append into agentList
     */
    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            int id = Integer.parseInt(rawRow.get(0));
            String userName = rawRow.get(1);
            String password = rawRow.get(2);
            Phone phone = new Phone(rawRow.get(3));

            Agent agent = new Agent(id, userName, password, phone);
            agentList.put(agent.getId(), agent);
        }
    }

    /**
     * convert Agent object into String Array to be written in
     * the CSV file
     *
     * @param agent the new Agent object to become String Array
     */
    private ArrayList<String> rawAgent(Agent agent) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(agent.getId())); //id
        result.add(agent.getUserName()); //userName
        result.add(agent.getPassword());
        result.add(agent.getPhone().getNumber()); //phone

        return result;
    }

    /**
     * write data from the agentList to the CSV
     */
    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Agent agent : agentList.values())
            rawData.add(rawAgent(agent));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    /**
     * write new data to the CSV
     *
     * @param agent the Agent object to be written
     */
    private void appendData(Agent agent) {
        CSV.appendCSV(rawAgent(agent), FILE_PATH);
    }

    /**
     * change data in the CSV
     *
     * @param agent the Agent object to be change
     */
    private void changeData(Agent agent) {
        int pos = agentList.headMap(agent.getId()).size();
        CSV.changeCSV(rawAgent(agent), pos, FILE_PATH);
    }

    /**
     * delete data in the CSV
     *
     * @param agent the Agent object to be deleted
     */
    private void deleteData(Agent agent) {
        int pos = agentList.headMap(agent.getId()).size();
        CSV.deleteCSV(pos, FILE_PATH);
    }
}