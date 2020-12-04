package com.ezgroceries.services;

import com.ezgroceries.persistence.entities.CocktailEntity;
import com.ezgroceries.persistence.entities.ShoppingListEntity;
import com.ezgroceries.persistence.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListResource shoppingListResource = new ShoppingListResource();
    private final CocktailService cocktailService;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
    }

    public ShoppingListResource create(String shoppingListName) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity(shoppingListName);
        shoppingListRepository.save(shoppingListEntity);
        return transformToResource(shoppingListEntity);
    }

    public List<ShoppingListResource> findAllShoppingLists() {
        List<ShoppingListResource> resources = new ArrayList<>();
        List<ShoppingListEntity> shoppingLists = shoppingListRepository.findAll();
        for(ShoppingListEntity x : shoppingLists) {
            resources.add(transformToResourceIngredients(x));
        }
        return resources;
    }

    private Set<String> findIngredients(ShoppingListEntity shoppingListEntity) {
        Set<String> allIngredients = new HashSet<String>();
        Set<CocktailEntity> cocktailEntitySet = shoppingListEntity.getCocktails();
        List<CocktailEntity> cocktailList = new ArrayList<CocktailEntity>();
        cocktailList.addAll(cocktailEntitySet);
        if (cocktailList != null) {
            for (CocktailEntity cocktailEntity : cocktailList) {
                Set<String> ingredients = cocktailEntity.getIngredients();
                for (String ingredient : ingredients)
                    if (ingredient != null)
                        allIngredients.add(ingredient);
            }
        }
        return allIngredients;
    }

    public ShoppingListResource findShoppingList(UUID shoppingListId){
        ShoppingListEntity entity = shoppingListRepository.findById(shoppingListId);
        return transformToResource(entity);
    }
    public List<ShoppingListEntity> getShoppingList(String shoppingListName){
        return shoppingListRepository.findByName(shoppingListName);
    }

    public ShoppingListResource addCocktails(UUID shoppingListId, List<String> cocktails){
        List<CocktailEntity> cocktailList = cocktailService.findByCocktailId(cocktails);
        Set<CocktailEntity> cocktailSet = new HashSet<CocktailEntity>(cocktailList);
        cocktailSet.addAll(cocktailList);
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(shoppingListId);
        shoppingListEntity.setCocktails(cocktailSet);
        shoppingListRepository.save(shoppingListEntity);
        return transformToResourceIngredients(shoppingListEntity);

    }

    private ShoppingListResource transformToResource(ShoppingListEntity shoppingListEntity){
        return new ShoppingListResource(shoppingListEntity.getShoppingListId(), shoppingListEntity.getName());
    }

    private ShoppingListResource transformToResourceIngredients(ShoppingListEntity shoppingListEntity){
        Set <String> ingredients = findIngredients(shoppingListEntity);
        return new ShoppingListResource(shoppingListEntity.getShoppingListId(), shoppingListEntity.getName(), ingredients);
    }


}
