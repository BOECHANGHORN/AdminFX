package Agent;

import CSV.CSV;
import CSV.ReadWriteRole;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

public class AgentDatabase implements ReadWriteRole<Agent> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "//agent.CSV");

    private final TreeMap<Integer, Agent> agentList;
    private final static AgentDatabase instance = new AgentDatabase();

    private AgentDatabase() {
        agentList = new TreeMap<>();
    }

    @Override
    public void init() {
        readData();
    }

    public static AgentDatabase getInstance() {
        return instance;
    }

    @Override
    public Agent searchByID(int id) {
        return agentList.get(id);
    }

    @Override
    public Agent searchUser(String userName) {
        for (Agent agent : agentList.values())
            if (agent.getUserName().equals(userName))
                return agent;
        return null;
    }

    @Override
    public int getNewID() {
        return agentList.lastKey() + 1;
    }

    //CRUD
    @Override
    public void create(Agent agent) {
        agentList.put(agent.getId(), agent);
        appendData(agent);
    }

    @Override
    public TreeMap<Integer, Agent> read() {
        return agentList;
    }

    @Override
    public void update(Agent agent) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Agent agent) {
        agentList.remove(agent.getId());
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

            Agent agent = new Agent(id, userName, password, phone);
            agentList.put(agent.getId(), agent);
        }
    }

    private ArrayList<String> rawAgent(Agent agent) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(agent.getId())); //id
        result.add(agent.getUserName()); //userName
        result.add(agent.getPassword());
        result.add(agent.getPhone().getNumber()); //phone

        return result;
    }

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Agent agent : agentList.values())
            rawData.add(rawAgent(agent));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Agent agent) {
        CSV.appendCSV(rawAgent(agent), FILE_PATH);
    }
}