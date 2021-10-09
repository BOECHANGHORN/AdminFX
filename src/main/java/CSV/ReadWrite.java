package CSV;

import java.util.TreeMap;

/**
 * <h1>ReadWrite&lt;T&gt; Class</h1>
 * The ReadWrite interface is served as an interface for all the necessary
 * methods for all the Database (DAO) classes
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public interface ReadWrite<T> {
    //Initialization

    /**
     * Method to initialize Database
     */
    void init();

    /**
     * Method to search object by ID
     *
     * @param id the target object id
     * @return the object if found else null
     */
    T searchByID(int id);

    /**
     * Method to get new ID for object T
     *
     * @return new ID for object T
     */
    int getNewID();

    //CRUD

    /**
     * Create object T and write to CSV
     *
     * @param t object t to be created
     */
    void create(T t);

    /**
     * return all T objects
     *
     * @return all T objects in TreeMap with ID as key
     */
    TreeMap<Integer, T> read();

    /**
     * update object T in CSV
     *
     * @param t object to be update
     */
    void update(T t);

    /**
     * delete object T in CSV
     *
     * @param t object to be delete
     */
    void delete(T t);

    //Read write into DB

    /**
     * read data from CSV and convert into TreeMap T
     */
    void readData();

    /**
     * write data from TreeMap T into CSV
     */
    void writeData();
}