package Property.PropertySearch;

import Agent.Agent;
import Owner.Owner;
import Property.PropertyAddress;
import Property.PropertyType;
import Tenant.Tenant;

/**
 * <h1>PropertyFilterBuilder Class</h1>
 * The PropertyFilterBuilder class is a builder class that use for building the
 * PropertyFilter. The builder class allow customization of search and filter
 * feature base on end user requirement
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
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

    /**
     * Gets type.
     *
     * @return the type
     */
    public PropertyType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the type
     */
    public PropertyFilterBuilder setType(PropertyType type) {
        this.type = type;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public PropertyFilterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     * @return the owner
     */
    public PropertyFilterBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     * @return the agent
     */
    public PropertyFilterBuilder setAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    /**
     * Gets tenant.
     *
     * @return the tenant
     */
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * Sets tenant.
     *
     * @param tenant the tenant
     * @return the tenant
     */
    public PropertyFilterBuilder setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    /**
     * Gets tenant opt.
     *
     * @return the tenant opt
     */
    public Tenant getTenantOpt() {
        return tenantOpt;
    }

    /**
     * Sets tenant opt.
     *
     * @param tenantOpt the tenant opt
     * @return the tenant opt
     */
    public PropertyFilterBuilder setTenantOpt(Tenant tenantOpt) {
        this.tenantOpt = tenantOpt;
        return this;
    }

    /**
     * Is availability boolean.
     *
     * @return the boolean
     */
    public Boolean isAvailability() {
        return availability;
    }

    /**
     * Sets availability.
     *
     * @param availability the availability
     * @return the availability
     */
    public PropertyFilterBuilder setAvailability(Boolean availability) {
        this.availability = availability;
        return this;
    }

    /**
     * Is comment boolean.
     *
     * @return the boolean
     */
    public Boolean isComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     * @return the comment
     */
    public PropertyFilterBuilder setComment(boolean comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Gets facilities picker.
     *
     * @return the facilities picker
     */
    public FacilitiesPicker getFacilitiesPicker() {
        return facilitiesPicker;
    }

    /**
     * Sets facilities picker.
     *
     * @param facilitiesPicker the facilities picker
     * @return the facilities picker
     */
    public PropertyFilterBuilder setFacilitiesPicker(FacilitiesPicker facilitiesPicker) {
        this.facilitiesPicker = facilitiesPicker;
        return this;
    }

    /**
     * Gets property address.
     *
     * @return the property address
     */
    public PropertyAddress getPropertyAddress() {
        return propertyAddress;
    }

    /**
     * Sets property address.
     *
     * @param propertyAddress the property address
     * @return the property address
     */
    public PropertyFilterBuilder setPropertyAddress(PropertyAddress propertyAddress) {
        this.propertyAddress = propertyAddress;
        return this;
    }

    /**
     * Gets rental low bound.
     *
     * @return the rental low bound
     */
    public Double getRentalLowBound() {
        return rentalLowBound;
    }

    /**
     * Sets rental low bound.
     *
     * @param rentalLowBound the rental low bound
     * @return the rental low bound
     */
    public PropertyFilterBuilder setRentalLowBound(double rentalLowBound) {
        this.rentalLowBound = rentalLowBound;
        return this;
    }

    /**
     * Gets rental up bound.
     *
     * @return the rental up bound
     */
    public Double getRentalUpBound() {
        return rentalUpBound;
    }

    /**
     * Sets rental up bound.
     *
     * @param rentalUpBound the rental up bound
     * @return the rental up bound
     */
    public PropertyFilterBuilder setRentalUpBound(double rentalUpBound) {
        this.rentalUpBound = rentalUpBound;
        return this;
    }

    /**
     * Gets published.
     *
     * @return the published
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * Sets published.
     *
     * @param published the published
     * @return the published
     */
    public PropertyFilterBuilder setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    /**
     * Gets ascending.
     *
     * @return the ascending
     */
    public Boolean getAscending() {
        return ascending;
    }

    /**
     * Sets ascending.
     *
     * @param ascending the ascending
     * @return the ascending
     */
    public PropertyFilterBuilder setAscending(Boolean ascending) {
        this.ascending = ascending;
        return this;
    }

    /**
     * Build property filter.
     *
     * @return the property filter
     */
    public PropertyFilter build() {
        return new PropertyFilter(this);
    }
}