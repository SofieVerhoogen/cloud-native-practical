package com.ezgroceries.model;

import java.util.ArrayList;

public class Shoppinglist {

    private long id;
    private String name;
    private ArrayList shoppinglist = new ArrayList();

    public Shoppinglist(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getShoppinglist() {
        return shoppinglist;
    }

    public void setShoppinglist(ArrayList shoppinglist) {
        this.shoppinglist = shoppinglist;
    }
}
