package com.ezgroceries.service;

import com.ezgroceries.entities.CocktailEntity;
import com.ezgroceries.entities.ShoppingListEntity;
import com.ezgroceries.repositories.CocktailRepository;
import com.ezgroceries.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    public List<ShoppingListEntity> findAllShoppingLists() {
        return shoppingListRepository.findAll();
    }

    public ShoppingListEntity findShoppingList(UUID shoppingListId){
        return shoppingListRepository.findById(shoppingListId);
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

        /**

        for (CocktailEntity x : cocktailList)
            System.out.println(x.getCocktailName());
        //    cocktailSet.add(x);
        for (CocktailEntity x : cocktailSet)
            System.out.println(x);

        System.out.println("Set" + cocktailSet);
        shoppingList.setCocktails(cocktailSet);
        shoppingListRepository.save(shoppingList);
        return shoppingList;**/

    }
}
