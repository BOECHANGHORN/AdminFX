package AppHolder;

import Property.Property;
import Role.Role;

/**
 * <h1>AppHolder Class</h1>
 * The AppHolder class is a class that utilizes singleton design pattern to store
 * the data that will need to be consistent throughout the all the scenes of the application
 *
 * @author Chan Yun Hong
 * @version 1.0
 * @since 2021-10-10
 */

public class AppHolder {
    private Role user;
    private Property selectedProperty;
    private PropertyFilterHolder propertyFilterHolder;
    private final static AppHolder INSTANCE = new AppHolder();

    /**
     * A private method that initialize the singleton
     * instance of AppHolder.
     */
    private AppHolder() {
    }

    /**
     * Gets property filter holder.
     *
     * @return the property filter holder
     */
    public PropertyFilterHolder getPropertyFilterHolder() {
        return propertyFilterHolder;
    }

    /**
     * Sets property filter holder.
     *
     * @param propertyFilterHolder the PropertyFilterHolder object
     */
    public void setPropertyFilterHolder(PropertyFilterHolder propertyFilterHolder) {
        this.propertyFilterHolder = propertyFilterHolder;
    }

    /**
     * Gets the singleton instance.
     *
     * @return the singleton instance
     */
    public static AppHolder getInstance() {
        return INSTANCE;
    }

    /**
     * Sets user.
     *
     * @param role the Role object
     */
    public void setUser(Role role) {
        this.user = role;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public Role getUser() {
        return this.user;
    }

    /**
     * Sets selected property.
     *
     * @param property the Property object
     */
    public void setSelectedProperty(Property property) {
        this.selectedProperty = property;
    }

    /**
     * Gets selected property.
     *
     * @return the selected property
     */
    public Property getSelectedProperty() {
        return this.selectedProperty;
    }
}
