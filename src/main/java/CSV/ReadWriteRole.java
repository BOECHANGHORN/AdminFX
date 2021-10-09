package CSV;

import Role.Role;

/**
 * <h1>ReadWriteRole&lt;T extends Role&gt; Class</h1>
 * The ReadWriteRole interface that inherit from ReadWrite interface
 * in order to implement a searchUser method for Database for Role
 * subclass
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public interface ReadWriteRole<T extends Role> extends ReadWrite<T> {
    //search user by user name

    /**
     * get Role subclass object from user name
     *
     * @param username the user name to be search
     * @return the Role subclass object if found else null
     */
    T searchUser(String username);
}