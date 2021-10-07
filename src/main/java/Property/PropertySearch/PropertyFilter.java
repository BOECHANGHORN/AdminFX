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

public class PropertyFilter {
    private final LinkedList<Property> properties;

    public PropertyFilter(PropertyFilterBuilder psb) {
        Collection<Property> getData = PropertyDatabase.getInstance().read().values();
        properties = new LinkedList<>(getData);

        if (psb.getType() != null) filterType(psb.getType());
        if (psb.getName() != null) filterName(psb.getName());
        if (psb.getOwner() != null) filterOwner(psb.getOwner());
        if (psb.getAgent() != null) filterAgent(psb.getAgent());
        if (psb.getTenant() != null) filterTenant(psb.getTenant());
        if (psb.getTenatOpt() != null) filterTenantOpt(psb.getTenatOpt());
        if (psb.isAvailability() != null) filterStatus(psb.isAvailability());
        if (psb.isComment() != null) filterComment(psb.isComment());
        if (psb.getFacilitiesPicker() != null) filterFacilitiesPicker(psb.getFacilitiesPicker());
        if (psb.getPropertyAddress() != null) filterAddress(psb.getPropertyAddress());
        if (psb.getRentalLowBound() != null) filterLowBound(psb.getRentalLowBound());
        if (psb.getRentalUpBound() != null) filterUpBound(psb.getRentalUpBound());
        if (psb.getPublished() != null) filterPublished(psb.getPublished());
        if (psb.getSorted() != null) filterSorted(psb.getSorted());
    }

    public ArrayList<Property> getResult() {
        return new ArrayList<>(properties);
    }

    private void filterType(PropertyType type) {
        properties.removeIf(p -> !p.getType().equals(type));
    }

    private void filterName(String name) {
        properties.removeIf(p -> !p.getName().contains(name));
    }

    private void filterOwner(Owner owner) {
        properties.removeIf(p -> !p.getOwner().equals(owner));
    }

    private void filterAgent(Agent agent) {
        properties.removeIf(p -> !p.getAgent().equals(agent));
    }

    private void filterTenant(Tenant tenant) {
        properties.removeIf(p -> (p.getTenant() == null || !p.getTenant().equals(tenant)));
    }

    private void filterTenantOpt(Tenant tenatOpt) {
        properties.removeIf(p -> p.getTenant() != null && !p.getTenant().equals(tenatOpt));
    }


    private void filterStatus(Boolean availability) {
        properties.removeIf(p -> (p.getTenant() != null) == availability);
    }

    private void filterComment(Boolean commentStatus) {
        properties.removeIf(p -> (p.getComment() == null || p.getComment().isEmpty()) == commentStatus);
    }


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


    private void filterAddress(PropertyAddress adrs) {
        if (adrs.getDetailAddress() != null && !adrs.getDetailAddress().isEmpty())
            properties.removeIf(p -> !p.getAddress().getDetailAddress().contains(adrs.getDetailAddress()));
        if (adrs.getState() != null && !adrs.getState().isEmpty())
            properties.removeIf(p -> !p.getAddress().getState().contains(adrs.getState()));
        if (adrs.getPostalCode() != null && !adrs.getPostalCode().isEmpty())
            properties.removeIf(p -> !p.getAddress().getPostalCode().contains(adrs.getPostalCode()));
    }

    private void filterLowBound(Double lowBound) {
        properties.removeIf(p -> p.getRate() < lowBound);
    }

    private void filterUpBound(Double upBound) {
        properties.removeIf(p -> p.getRate() > upBound);
    }

    private void filterPublished(Boolean published) {
        properties.removeIf(p -> p.isPublished() != published);
    }

    private void filterSorted(Boolean sorted) {
        int isPositive = sorted ? 1 : -1;
        properties.sort(
                Comparator.comparingDouble((Property p) -> p.getRate() * isPositive)
        );
    }


}