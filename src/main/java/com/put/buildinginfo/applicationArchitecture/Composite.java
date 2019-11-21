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
        return Float.parseFloat(null);
    }
    public float calculateCubature(){
        return Float.parseFloat(null);
    }
    public float calculateLighting(){
        return Float.parseFloat(null);
    }
    public float calculateHeating(){
        return Float.parseFloat(null);
    }
}
