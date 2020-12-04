package com.ezgroceries.controller;

import com.ezgroceries.services.ShoppingListResource;
import com.ezgroceries.services.ShoppingListService;
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
    public List<ShoppingListResource> getAllShoppingLists(){
        return shoppingListService.findAllShoppingLists();
    }

    @GetMapping(value= "/shopping-lists/{shoppingListId}")
    public ShoppingListResource getShoppingList(@PathVariable UUID shoppingListId){
        return shoppingListService.findShoppingList(shoppingListId);
    }

    @PostMapping(value="/shopping-lists")
    public ShoppingListResource createShoppingList(@RequestBody Map<String,String> shoppingListName){
        return shoppingListService.create(shoppingListName.get("name"));
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails")
    public ShoppingListResource addCocktail(@PathVariable UUID shoppingListId, @RequestBody List<Map<String,String>> cocktails) {
        List<String> cocktailIDs = cocktails.stream()
                .map(stringStringEntry -> stringStringEntry.get("cocktailId"))
                .collect(Collectors.toList());
        return shoppingListService.addCocktails(shoppingListId,cocktailIDs);

    }
}
