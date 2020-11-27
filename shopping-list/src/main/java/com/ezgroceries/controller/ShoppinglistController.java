package com.ezgroceries.controller;

import com.ezgroceries.entities.ShoppingListEntity;
import com.ezgroceries.service.CocktailResource;
import com.ezgroceries.service.ShoppingListResource;
import com.ezgroceries.service.ShoppingListService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = "application/json")
public class ShoppinglistController {

    private ShoppingListService shoppingListService;

    private ShoppinglistController(ShoppingListService shoppingListService){
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(value="/shopping-lists")
    public List<ShoppingListEntity> getShoppingLists(){
        return shoppingListService.findAllShoppingLists();
    }

    @GetMapping(value= "/shopping-lists/{shoppingListId}")
    public ShoppingListEntity getShoppingList(@PathVariable UUID shoppingListId){
        return shoppingListService.findShoppingList(shoppingListId);
    }

    @PostMapping(value="/shopping-lists")
    public ShoppingListResource createShoppingList(@RequestBody String shoppingListName){
        return shoppingListService.create(shoppingListName);
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    public ShoppingListEntity addCocktail(@PathVariable UUID shoppingListId, @RequestBody List<Map<String,String>> cocktails) {
        ShoppingListEntity shoppingList = shoppingListService.findShoppingList(shoppingListId);
        List<String> cocktailIDs = cocktails.stream()
                .map(stringStringEntry -> stringStringEntry.get("cocktailId"))
                .collect(Collectors.toList());
        return shoppingListService.addCocktails(shoppingListId,cocktailIDs);
       // for (CocktailResource resource : cocktailResource) {
       //     if (resource.getCocktailId().equals(cocktailResource.getCocktailId())) {
       //         shoppingList.setShoppingItems(resource.getIngredients());
       //     }
        //}
        //return cocktailResource.getCocktailId();
    }
}
