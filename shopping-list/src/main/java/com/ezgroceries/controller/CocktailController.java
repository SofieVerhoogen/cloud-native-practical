package com.ezgroceries.controller;
 import com.ezgroceries.client.CocktailDBClient;
 import com.ezgroceries.client.CocktailDBResponse;
 import com.ezgroceries.service.CocktailResource;
 import com.ezgroceries.service.ShoppingList;
 import io.micrometer.core.instrument.util.StringUtils;
 import org.springframework.web.bind.annotation.*;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.UUID;
 import java.util.stream.Collectors;
 import java.util.stream.Stream;

@RestController
@RequestMapping(produces = "application/json")
public class CocktailController {

    private List<CocktailResource> cocktailResources = new ArrayList<>();
    private CocktailDBClient cocktailDBClient;
    private List<ShoppingList> shoppingLists = new ArrayList<>();
    private ShoppingList shoppingList;

    private CocktailController(CocktailDBClient cocktailDBClient) { this.cocktailDBClient = cocktailDBClient;}

    @GetMapping(value= "/cocktails")
    public List<CocktailResource> get(@RequestParam String search) {
        return convertCocktails(cocktailDBClient.searchCocktails(search));
        //*cocktailResources = getDummyResources();
        //return cocktailResources;
    }

    @GetMapping(value= "/cocktails/{cocktailId}")
    public CocktailResource getCocktail(@PathVariable UUID cocktailId) {
        System.out.println("get cocktail: " + cocktailId);
        for (CocktailResource resource : cocktailResources){
            if (resource.getCocktailId().equals(cocktailId)){
                return resource;
            }
        }
        return null;
    }

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

    private List<CocktailResource> convertCocktails(CocktailDBResponse cocktailDBResponse) {
        return cocktailDBResponse.getDrinks().stream()
                .map(drinkResource -> new CocktailResource(
                UUID.randomUUID(),
                drinkResource.getStrDrink(),
                drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(),
                drinkResource.getStrDrinkThumb(),
                Stream.of(
                        drinkResource.getStrIngredient1(),
                        drinkResource.getStrIngredient2(),
                        drinkResource.getStrIngredient3(),
                        drinkResource.getStrIngredient4()
                        ).filter(StringUtils::isNotBlank).collect(Collectors.toList()))
                ).collect(Collectors.toList());
    }

    private List<CocktailResource> getDummyResources() {
        return Arrays.asList(
                new CocktailResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new CocktailResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }
}

