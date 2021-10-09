package Property.PropertySearch;

import Agent.Agent;
import Owner.Owner;
import Property.Property;
import Property.PropertyAddress;
import Property.PropertyDatabase;
import Property.PropertyType;
import Tenant.Tenant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * <h1>PropertyFilter Class</h1>
 * The PropertyFilter class is a class that use for searching and filter the
 * attributes(data fields) of property and return the list of properties that
 * matches the criteria as an arrayList
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class PropertyFilter {
    private final LinkedList<Property> properties;

    /**
     * Instantiates a new Property filter from PropertyFilter builder class
     * If the data field from the builder class is set (not null), it will
     * filter and remove the not fulfilled properties from the temporary linked list
     *
     * @param psb the Property Filter Builder to search and filter
     */
    public PropertyFilter(PropertyFilterBuilder psb) {
        Collection<Property> getData = PropertyDatabase.getInstance().read().values();
        properties = new LinkedList<>(getData);

        if (psb.getType() != null) filterType(psb.getType());
        if (psb.getName() != null) filterName(psb.getName());
        if (psb.getOwner() != null) filterOwner(psb.getOwner());
        if (psb.getAgent() != null) filterAgent(psb.getAgent());
        if (psb.getTenant() != null) filterTenant(psb.getTenant());
        if (psb.getTenantOpt() != null) filterTenantOpt(psb.getTenantOpt());
        if (psb.isAvailability() != null) filterStatus(psb.isAvailability());
        if (psb.isComment() != null) filterComment(psb.isComment());
        if (psb.getFacilitiesPicker() != null) filterFacilitiesPicker(psb.getFacilitiesPicker());
        if (psb.getPropertyAddress() != null) filterAddress(psb.getPropertyAddress());
        if (psb.getRentalLowBound() != null) filterLowBound(psb.getRentalLowBound());
        if (psb.getRentalUpBound() != null) filterUpBound(psb.getRentalUpBound());
        if (psb.getPublished() != null) filterPublished(psb.getPublished());
        if (psb.getAscending() != null) filterAscending(psb.getAscending());
    }

    /**
     * Gets the matches properties as an arraylist
     *
     * @return the matches properties as an arraylist
     */
    public ArrayList<Property> getResult() {
        return new ArrayList<>(properties);
    }

    /**
     * filter properties by type
     *
     * @param type target type
     */
    private void filterType(PropertyType type) {
        properties.removeIf(p -> !p.getType().equals(type));
    }

    /**
     * filter properties by name
     *
     * @param name target name
     */
    private void filterName(String name) {
        properties.removeIf(p -> !p.getName().contains(name));
    }

    /**
     * filter properties by owner
     *
     * @param owner target owner
     */
    private void filterOwner(Owner owner) {
        properties.removeIf(p -> !p.getOwner().equals(owner));
    }

    /**
     * filter properties by agent
     *
     * @param agent target agent
     */
    private void filterAgent(Agent agent) {
        properties.removeIf(p -> !p.getAgent().equals(agent));
    }

    /**
     * filter properties by tenant
     *
     * @param tenant target tenant
     */
    private void filterTenant(Tenant tenant) {
        properties.removeIf(p -> (p.getTenant() == null || !p.getTenant().equals(tenant)));
    }

    /**
     * filter properties by tenant but keep the unrented
     *
     * @param tenantOpt target tenant
     */
    private void filterTenantOpt(Tenant tenantOpt) {
        properties.removeIf(p -> p.getTenant() != null && !p.getTenant().equals(tenantOpt));
    }

    /**
     * filter properties by status of availability (rent status)
     *
     * @param availability target availability
     */
    private void filterStatus(Boolean availability) {
        properties.removeIf(p -> (p.getTenant() != null) == availability);
    }

    /**
     * filter properties by comment status (is comment)
     *
     * @param commentStatus target comment status
     */
    private void filterComment(Boolean commentStatus) {
        properties.removeIf(p -> (p.getComment() == null || p.getComment().isEmpty()) == commentStatus);
    }

    /**
     * filter properties by facilities
     *
     * @param fp target facilities picker
     */
    private void filterFacilitiesPicker(FacilitiesPicker fp) {
        if (fp.isAirCond())
            properties.removeIf(p -> p.getFacilities().getAirCond() == 0);
        if (fp.isFridge())
            properties.removeIf(p -> p.getFacilities().getFridge() == 0);
        if (fp.isSwimmingPool())
            properties.removeIf(p -> !p.getFacilities().isSwimmingPool());
        if (fp.isWifi())
            properties.removeIf(p -> !p.getFacilities().isWifi());
        if (fp.isTv())
            properties.removeIf(p -> p.getFacilities().getTv() == 0);
        if (fp.isWaterHeater())
            properties.removeIf(p -> p.getFacilities().getWaterHeater() == 0);
    }

    /**
     * filter properties by address
     *
     * @param adrs target address
     */
    private void filterAddress(PropertyAddress adrs) {
        if (adrs.getDetailAddress() != null && !adrs.getDetailAddress().isEmpty())
            properties.removeIf(p -> !p.getAddress().getDetailAddress().contains(adrs.getDetailAddress()));
        if (adrs.getState() != null && !adrs.getState().isEmpty())
            properties.removeIf(p -> !p.getAddress().getState().contains(adrs.getState()));
        if (adrs.getPostalCode() != null && !adrs.getPostalCode().isEmpty())
            properties.removeIf(p -> !p.getAddress().getPostalCode().contains(adrs.getPostalCode()));
    }

    /**
     * filter properties by limit lowest price
     *
     * @param lowBound target lowest price
     */
    private void filterLowBound(Double lowBound) {
        properties.removeIf(p -> p.getRate() < lowBound);
    }

    /**
     * filter properties by limit highest price
     *
     * @param upBound target highest price
     */
    private void filterUpBound(Double upBound) {
        properties.removeIf(p -> p.getRate() > upBound);
    }

    /**
     * filter properties by publish status
     *
     * @param published target publish status
     */
    private void filterPublished(Boolean published) {
        properties.removeIf(p -> p.isPublished() != published);
    }

    /**
     * sort properties by price
     *
     * @param ascending true for ascending else descending
     */
    private void filterAscending(Boolean ascending) {
        int isPositive = ascending ? 1 : -1;
        properties.sort(
                Comparator.comparingDouble((Property p) -> p.getRate() * isPositive)
        );
    }


}