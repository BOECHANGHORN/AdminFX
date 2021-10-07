package CSV;

import java.util.TreeMap;

public interface ReadWrite<T> {
    //Initialization
    void init();

    T searchByID(int id);

    int getNewID();

    //CRUD
    void create(T t);

    TreeMap<Integer, T> read();

    void update(T t);

    void delete(T t);

    //Read write into DB
    void readData();

    void writeData();
}