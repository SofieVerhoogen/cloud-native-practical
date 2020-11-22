package com.ezgroceries.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shoppinglist")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
    private UUID shoppingListId;

    private String name;

    private List<String> shoppingItems;

    public ShoppingListEntity(){}

    public ShoppingListEntity(String name) {
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

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<String> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }
}
