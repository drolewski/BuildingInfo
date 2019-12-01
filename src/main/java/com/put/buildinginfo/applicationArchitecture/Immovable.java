package com.put.buildinginfo.applicationArchitecture;

/**
 * The type Immovable.
 */
public abstract class Immovable {
    private int id;
    private  String name;

    /**
     * Calculate the cubature.
     *
     * @return the float
     */
    public abstract float calculateCubature();

    /**
     * Calculate the surface.
     *
     * @return the float
     */
    public abstract float calculateSurface();

    /**
     * Calculate the lighting.
     *
     * @return the float
     */
    public abstract float calculateLighting();

    /**
     * Calculate the heating.
     *
     * @return the float
     */
    public abstract float calculateHeating();

    /**
     * Instantiates a new Immovable.
     */
    public Immovable() {
    }

    /**
     * Instantiates a new Immovable.
     *
     * @param id   the id
     * @param name the name
     */
    public Immovable(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the immovable identifier.
     *
     * @return the immovable identifier
     */
    public int getId(){
        return  id;
    }

    /**
     * Sets the immovable identifier.
     *
     * @param id the the immovable identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the immovable name.
     *
     * @return the immovable name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the immovable name.
     *
     * @param name the immovable name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
