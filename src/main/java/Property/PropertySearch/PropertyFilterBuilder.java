package Property.PropertySearch;

import Agent.Agent;
import Owner.Owner;
import Property.PropertyAddress;
import Property.PropertyType;
import Tenant.Tenant;

public class PropertyFilterBuilder {
    private PropertyType type;
    private String name;
    private Owner owner;
    private Agent agent;
    private Tenant tenant;
    private Tenant tenantOpt;
    private Boolean availability;
    private Boolean comment;
    private FacilitiesPicker facilitiesPicker;
    private PropertyAddress propertyAddress;
    private Double rentalLowBound;
    private Double rentalUpBound;
    private Boolean published;
    private Boolean ascending;

    public PropertyType getType() {
        return type;
    }

    public PropertyFilterBuilder setType(PropertyType type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public PropertyFilterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public PropertyFilterBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public Agent getAgent() {
        return agent;
    }

    public PropertyFilterBuilder setAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public PropertyFilterBuilder setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public Tenant getTenantOpt() {
        return tenantOpt;
    }

    public PropertyFilterBuilder setTenantOpt(Tenant tenantOpt) {
        this.tenantOpt = tenantOpt;
        return this;
    }

    public Boolean isAvailability() {
        return availability;
    }

    public PropertyFilterBuilder setAvailability(Boolean availability) {
        this.availability = availability;
        return this;
    }

    public Boolean isComment() {
        return comment;
    }

    public PropertyFilterBuilder setComment(boolean comment) {
        this.comment = comment;
        return this;
    }

    public FacilitiesPicker getFacilitiesPicker() {
        return facilitiesPicker;
    }

    public PropertyFilterBuilder setFacilitiesPicker(FacilitiesPicker facilitiesPicker) {
        this.facilitiesPicker = facilitiesPicker;
        return this;
    }

    public PropertyAddress getPropertyAddress() {
        return propertyAddress;
    }

    public PropertyFilterBuilder setPropertyAddress(PropertyAddress propertyAddress) {
        this.propertyAddress = propertyAddress;
        return this;
    }

    public Double getRentalLowBound() {
        return rentalLowBound;
    }

    public PropertyFilterBuilder setRentalLowBound(double rentalLowBound) {
        this.rentalLowBound = rentalLowBound;
        return this;
    }

    public Double getRentalUpBound() {
        return rentalUpBound;
    }

    public PropertyFilterBuilder setRentalUpBound(double rentalUpBound) {
        this.rentalUpBound = rentalUpBound;
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public PropertyFilterBuilder setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public PropertyFilterBuilder setAscending(Boolean ascending) {
        this.ascending = ascending;
        return this;
    }

    public PropertyFilter build() {
        return new PropertyFilter(this);
    }
}