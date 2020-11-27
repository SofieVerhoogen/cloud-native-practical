package com.ezgroceries.service;

import com.ezgroceries.client.CocktailDBClient;
import com.ezgroceries.client.CocktailDBResponse;
import com.ezgroceries.entities.CocktailEntity;
import com.ezgroceries.repositories.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient cocktailDBClient;

    public CocktailService(CocktailRepository cocktailRepository, CocktailDBClient cocktailDBClient) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailDBClient = cocktailDBClient;
    }

    public CocktailEntity create(CocktailEntity cocktailEntity) {
        return cocktailRepository.save(cocktailEntity);

    }

    public List<CocktailEntity> findByCocktailId(List<String> cocktailIds) {
        return cocktailRepository.findByCocktailIdIn(cocktailIds.stream()
                .map(UUID::fromString).collect(Collectors.toList()));
    }
    public List<CocktailEntity> findByIdDrinkIn(List<String> cocktailIds){
        return cocktailRepository.findByIdDrinkIn(cocktailIds);
    }
    public CocktailDBResponse searchCocktails(String search) {
        CocktailDBResponse lists = cocktailDBClient.searchCocktails(search);
        List<CocktailResource> cocktailResources = mergeCocktails(lists.getDrinks());
        return lists;
    }

    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
       Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setCocktailId(UUID.randomUUID());
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setCocktailName(drinkResource.getStrDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink())
                .getCocktailId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getIngredients(drinkResource)))
                .collect(Collectors.toList());
    }

    private List<String> getIngredients(CocktailDBResponse.DrinkResource drinkResource) {
        List<String> list = new ArrayList<String>();
        list.add(drinkResource.getStrIngredient1());
        list.add(drinkResource.getStrIngredient2());
        list.add(drinkResource.getStrIngredient3());
        list.add(drinkResource.getStrIngredient4());
        return list;
    }

}