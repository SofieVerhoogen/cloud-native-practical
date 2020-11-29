package com.ezgroceries.service;

import com.ezgroceries.entities.CocktailEntity;
import com.ezgroceries.entities.ShoppingListEntity;
import com.ezgroceries.repositories.CocktailRepository;
import com.ezgroceries.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
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
        shoppingListResource.setShoppingListId(shoppingListEntity.getShoppingListId());
        shoppingListResource.setName(shoppingListEntity.getName());
        return shoppingListResource;
    }

    public List<ShoppingListResource> findAllShoppingLists() {
        List<ShoppingListResource> resources = new ArrayList<>();
        List<ShoppingListEntity> shoppingLists = shoppingListRepository.findAll();
        for(ShoppingListEntity x : shoppingLists) {
            ShoppingListResource resource = new ShoppingListResource(x.getShoppingListId(), x.getName());
            resource.setShoppingItems(findIngredients(x));
            resources.add(resource);
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
    private List<CocktailEntity> convertSettoList(Set<CocktailEntity> cocktailEntities){
        if (cocktailEntities.isEmpty())
            return new ArrayList<>();
        return new ArrayList<>(cocktailEntities);
    }

    public ShoppingListResource findShoppingList(UUID shoppingListId){
        ShoppingListEntity entity = shoppingListRepository.findById(shoppingListId);
        return new ShoppingListResource(entity.getShoppingListId(), entity.getName());
    }
    public List<ShoppingListEntity> getShoppingList(String shoppingListName){
        return shoppingListRepository.findByName(shoppingListName);
    }

    public ShoppingListEntity addCocktails(UUID shoppingListId, List<String> cocktails){
        List<CocktailEntity> cocktailList = cocktailService.findByCocktailId(cocktails);
        Set<CocktailEntity> cocktailSet = new HashSet<CocktailEntity>(cocktailList);
        cocktailSet.addAll(cocktailList);
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findById(shoppingListId);
        shoppingListEntity.setCocktails(cocktailSet);
        shoppingListRepository.save(shoppingListEntity);
        return shoppingListEntity;

    }

    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListResource> resources = new ArrayList<>();
        List<ShoppingListEntity> shoppingLists = shoppingListRepository.findAll();
        for(ShoppingListEntity x : shoppingLists){
            ShoppingListResource resource = new ShoppingListResource(x.getShoppingListId(), x.getName());
            resources.add(resource);
        }
        return resources;
    }
}
