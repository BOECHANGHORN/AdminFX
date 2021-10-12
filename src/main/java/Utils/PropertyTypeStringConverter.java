package Utils;

import Property.PropertyType;
import javafx.util.StringConverter;

/**
 * <h1>PropertyTypeStringConverter</h1>
 * The PropertyTypeStringConverter class is a class that store data fields
 * of PropertyTypeStringConverter
 *
 * @author Chan Yun Hong
 * @version 1.0
 * @since 2021-10-11
 */
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
