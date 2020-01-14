package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;


/**
 * The type Composite.
 *
 * @param <T> the type parameter - it can be any immoveable
 */
public class Composite<T extends Immovable> extends Immovable{
    private ArrayList<T> immoveables;

    /**
     * Instantiates a new Composite.
     *
     * @param id          the identifier of new composite
     * @param name        the name of new composite
     * @param immoveables the Arraylist of immoveables
     */
    public Composite(int id, String name, ArrayList<T> immoveables) {
        super(id, name);
        this.immoveables = immoveables;
    }


    /**
     * Add.
     *
     * @param t the immoveable of any type
     */
    public void add(T t) {
        if(immoveables == null){
            immoveables = new ArrayList<>();
        }
        immoveables.add(t);
    }


    /**
     * Remove.
     *
     * @param t the immoveables of any type
     */
    public void remove(T t) {
        if(immoveables == null){
            return;
        }
        immoveables.remove(t.getId());
    }


    /**
     * Modify. (It actually replace the old immovables.)
     *
     * @param t the immoveables of any type
     */
    public void modify(T t) {
        immoveables.remove(t.getId());
        immoveables.add(t);
    }

    /**
     * Gets immoveables.
     *
     * @return the immoveables of any type
     */
    public ArrayList<T> getImmoveables() {
        return immoveables;
    }

    /**
     * Sets immoveables.
     *
     * @param immoveables the immoveables of any type to set
     */
    public void setImmoveables(ArrayList<T> immoveables) {
        this.immoveables = immoveables;
    }


    /**
     * Calculate the surface.
     *
     * @return the float number which is the sum of surface
     */
    public float calculateSurface(){
        float surfaceSum = 0f;
        if(immoveables == null){
            return 0f;
        }
        for(T o : immoveables){
            surfaceSum += o.calculateSurface();
        }
        return surfaceSum;
    }


    /**
     * Calculate the cubature float.
     *
     * @return the float number which is the sum of cubature
     */
    public float calculateCubature(){
        float cubatureSum = 0f;
        if(immoveables == null){
            return 0f;
        }
        for(T o : immoveables){
            cubatureSum += o.calculateCubature();
        }
        return cubatureSum;
    }

    /**
     * Calculate the lighting.
     *
     * @return the float number which is the sum of lighting
     */
    public float calculateLighting(){
        float lighting = 0f;
        if(immoveables == null){
            return 0f;
        }
        for(T o : immoveables){
            lighting += o.calculateLighting();
        }
        return lighting;
    }


    /**
     * Calculate the heating.
     *
     * @return The float number which is the sum of heating. The value will always be non-negative.
     */
    public float calculateHeating(){
        float heating = 0f;
        if(immoveables == null){
            return 0f;
        }
        for(T o : immoveables){
            heating += o.calculateHeating();
        }
        return heating;
    }
}
