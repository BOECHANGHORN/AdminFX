package AppHolder;

import Property.Property;
import Role.Role;

public class AppHolder {
    private Role user;
    private Property selectedProperty;
    private PropertyFilterHolder propertyFilterHolder;

    public PropertyFilterHolder getPropertyFilterHolder() {
        return propertyFilterHolder;
    }

    public void setPropertyFilterHolder(PropertyFilterHolder propertyFilterHolder) {
        this.propertyFilterHolder = propertyFilterHolder;
    }

    private final static AppHolder INSTANCE = new AppHolder();

    private AppHolder() {
    }

    public static AppHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(Role r) {
        this.user = r;
    }

    public Role getUser() {
        return this.user;
    }

    public void setSelectedProperty(Property p) {
        this.selectedProperty = p;
    }

    public Property getSelectedProperty() {
        return this.selectedProperty;
    }
}
