package com.ezgroceries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;

    public ShoppingListResource(){}

    public ShoppingListResource(UUID shoppingListId , String name){
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String shoppingListName) {
        this.name = shoppingListName;
    }

}
