package Utils;

import Property.PropertyType;
import javafx.util.StringConverter;

public class PropertyTypeStringConverter extends StringConverter<PropertyType> {
    @Override
    public String toString(PropertyType x) {
        return x == null ? null : Utils.getPropertyTypeTxt(x);
    }

    @Override
    public PropertyType fromString(String s) {
        return null;
    }
}