package com.ezgroceries.controller;

import com.ezgroceries.service.CocktailResource;
import com.ezgroceries.service.ShoppingListResource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppinglistController {

    private ShoppingListResource shoppingList;
    private List<ShoppingListResource> shoppingLists = new ArrayList<>();
    private CocktailResource cocktailService;
/**

    @GetMapping(value="/shopping-lists")
    public List<ShoppingList> getShoppingLists(){
        System.out.println("getShoppingLists");
        return shoppingLists;
    }
    @GetMapping(value= "/shopping-lists/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId){
        shoppingList.setShoppingListId(shoppingListId);
        return shoppingList;
    }

    @PostMapping(value="/shopping-lists")
    public ShoppingList createShoppingList(@RequestBody ShoppingList newShoppingList){
        shoppingList = new ShoppingList(UUID.randomUUID(), newShoppingList.getName());
        shoppingLists.add(shoppingList);
        return shoppingList;
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    public UUID addCocktail(@PathVariable UUID shoppingListId, @RequestBody CocktailResource cocktailResource) {
        for (ShoppingList list : shoppingLists) {
            if (list.getShoppingListId().equals(shoppingListId)) {
                shoppingList = list;
            }
        }
        for (CocktailResource resource : cocktailResources) {
            if (resource.getCocktailId().equals(cocktailResource.getCocktailId())) {
                shoppingList.setShoppingItems(resource.getIngredients());
            }
        }
        return cocktailResource.getCocktailId();
    }
    **/
}
