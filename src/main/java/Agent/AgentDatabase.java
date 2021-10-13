package Agent;

import ReadWrite.ReadWriteRole;
import Initializer.Initialization;

import java.io.*;
import java.util.TreeMap;

/**
 * <h1>AgentDatabase Class</h1>
 * The AgentDatabase class is a DAO class of the Agent class which implements the Singleton
 * design pattern that is responsible for the search and CRUD of Agent object in a Serializable file.
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class AgentDatabase implements ReadWriteRole<Agent> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//agent.bin");

    private TreeMap<Integer, Agent> agentList;
    private final static AgentDatabase instance = new AgentDatabase();

    /**
     * A private method to serialize agentList
     */
    private void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(agentList);
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
            agentList = (TreeMap<Integer, Agent>) ois.readObject();
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
        deserialize();
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
     * Append new data into agentList and Serialize
     *
     * @param agent the new Agent object to be written
     */
    @Override
    public void create(Agent agent) {
        agentList.put(agent.getId(), agent);
        serialize();
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
     * Update data by Serialize the agentList
     *
     * @param agent the new Agent object to be update
     */
    @Override
    public void update(Agent agent) {
        serialize();
    }

    /**
     * remove the object from agentList and Serialize
     *
     * @param agent the new Agent object to be delete
     */
    @Override
    public void delete(Agent agent) {
        agentList.remove(agent.getId());
        serialize();
    }
}