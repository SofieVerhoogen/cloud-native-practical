package com.ezgroceries.persistence.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
    private UUID shoppingListId;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "SHOPPING_LIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "COCKTAIL_ID"))
    private Set<CocktailEntity> cocktails;

    public ShoppingListEntity(){}

    public ShoppingListEntity(String name) {
        this.shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public UUID getShoppingListId() { return shoppingListId; }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCocktails(Set<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }
    public Set<CocktailEntity> getCocktails(){
        return cocktails;
    }
}
