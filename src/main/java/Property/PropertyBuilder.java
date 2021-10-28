package Property;

import Agent.Agent;
import Owner.Owner;
import Tenant.Tenant;

/**
 * <h1>PropertyBuilder Class</h1>
 * The Property class is a builder class to build
 * a property
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021 -10-08
 */
public class PropertyBuilder {
    /**
     * The Id.
     */
    protected int id;
    /**
     * The Type.
     */
    protected PropertyType type;
    /**
     * The Owner.
     */
    protected Owner owner;
    /**
     * The Agent.
     */
    protected Agent agent;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Room num.
     */
    protected int roomNum;
    /**
     * The Bath room num.
     */
    protected int bathRoomNum;
    /**
     * The Address.
     */
    protected PropertyAddress address;
    /**
     * The Size.
     */
    protected int size;
    /**
     * The Facilities.
     */
    protected PropertyFacilities facilities;
    /**
     * The Tenant.
     */
    protected Tenant tenant; //available if null else n/a
    /**
     * The Comment.
     */
    protected String comment;
    /**
     * The Rate.
     */
    protected double rate;
    /**
     * The Published.
     */
    protected boolean published;

    /**
     * Instantiates a new Property builder.
     *
     * @param id   the id
     * @param name the name
     */
    public PropertyBuilder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Sets type.
     *
     * @param type the type
     * @return the PropertyBuilder
     */
    public PropertyBuilder setType(PropertyType type) {
        this.type = type;
        return this;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     * @return the PropertyBuilder
     */
    public PropertyBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     * @return the PropertyBuilder
     */
    public PropertyBuilder setAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    /**
     * Sets room num.
     *
     * @param roomNum the room num
     * @return the PropertyBuilder
     */
    public PropertyBuilder setRoomNum(int roomNum) {
        this.roomNum = roomNum;
        return this;
    }

    /**
     * Sets bath room num.
     *
     * @param bathRoomNum the bath room num
     * @return the PropertyBuilder
     */
    public PropertyBuilder setBathRoomNum(int bathRoomNum) {
        this.bathRoomNum = bathRoomNum;
        return this;
    }

    /**
     * Sets address.
     *
     * @param address the address
     * @return the PropertyBuilder
     */
    public PropertyBuilder setAddress(PropertyAddress address) {
        this.address = address;
        return this;
    }

    /**
     * Sets size.
     *
     * @param size the size
     * @return the PropertyBuilder
     */
    public PropertyBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Sets facilities.
     *
     * @param facilities the facilities
     * @return the PropertyBuilder
     */
    public PropertyBuilder setFacilities(PropertyFacilities facilities) {
        this.facilities = facilities;
        return this;
    }

    /**
     * Sets tenant.
     *
     * @param tenant the tenant
     * @return the PropertyBuilder
     */
    public PropertyBuilder setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     * @return the PropertyBuilder
     */
    public PropertyBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     * @return the PropertyBuilder
     */
    public PropertyBuilder setRate(double rate) {
        this.rate = rate;
        return this;
    }

    /**
     * Sets published.
     *
     * @param published the published
     * @return the PropertyBuilder
     */
    public PropertyBuilder setPublished(boolean published) {
        this.published = published;
        return this;
    }

    /**
     * Build property.
     *
     * @return the property
     */
    public Property build() {
        validation();
        return new Property(this);
    }

    /**
     * For input validation if require
     */
    private void validation() {

    }
}
