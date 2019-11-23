package com.put.buildinginfo.applicationArchitecture;

import java.util.ArrayList;

public class Composite<T extends Immovable> extends Immovable{
    private ArrayList<T> immoveables;

    public Composite(int id, String name, ArrayList<T> immoveables) {
        super(id, name);
        this.immoveables = immoveables;
    }

    public void add(T t) {
        if(immoveables == null){
            immoveables = new ArrayList<>();
        }
        immoveables.add(t);
    }

    public void remove(T t) {
        if(immoveables == null){
            return;
        }
        immoveables.remove(t.getId());
    }

    public void modify(T t) {
        immoveables.remove(t.getId());
        immoveables.add(t);
    }

    public ArrayList<T> getImmoveables() {
        return immoveables;
    }

    public void setImmoveables(ArrayList<T> immoveables) {
        this.immoveables = immoveables;
    }

    public float calculateSurface(){
        float surfaceSum = 0f;
        for(T o : immoveables){
            surfaceSum += o.calculateSurface();
        }
        return surfaceSum;
    }
    public float calculateCubature(){
        float cubatureSum = 0f;
        for(T o : immoveables){
            cubatureSum += o.calculateCubature();
        }
        return cubatureSum;
    }
    public float calculateLighting(){
        float lighting = 0f;
        for(T o : immoveables){
            lighting += o.calculateLighting();
        }
        return lighting;
    }
    public float calculateHeating(){
        float heating = 0f;
        for(T o : immoveables){
            heating += o.calculateHeating();
        }
        return heating;
    }
}
