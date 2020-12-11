package com.ezgroceries.service;

import com.ezgroceries.client.CocktailDBClient;
import com.ezgroceries.client.CocktailDBResponse;
import com.ezgroceries.entities.CocktailEntity;
import com.ezgroceries.repositories.CocktailRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    public List<CocktailResponse> searchCocktails(String search) {
        CocktailDBResponse lists = cocktailDBClient.searchCocktails(search);
        return mergeCocktails(lists.getDrinks());
    }

    public List<CocktailResponse> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
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
                newCocktailEntity.setIngredients(getSetIngredients(drinkResource));
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResponse> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResponse(allEntityMap.get(drinkResource.getIdDrink())
                .getCocktailId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getListIngredients(drinkResource)))
                .collect(Collectors.toList());
    }

    private List<String> getListIngredients(CocktailDBResponse.DrinkResource drinkResource) {
        return Stream.of(
                drinkResource.getStrIngredient1(),
                drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(),
                drinkResource.getStrIngredient4()
        ).filter(StringUtils::isNotBlank).collect(Collectors.toList());

        /**List<String> list = new ArrayList<>();
        if (drinkResource.getStrIngredient1() != null)
            list.add(drinkResource.getStrIngredient1());
        return list;*/
    }

    private Set<String> getSetIngredients(CocktailDBResponse.DrinkResource drinkResource) {
        return Stream.of(
                drinkResource.getStrIngredient1(),
                drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(),
                drinkResource.getStrIngredient4()
        ).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        /**List<String> list = new ArrayList<>();
        if (drinkResource.getStrIngredient1() != null)
            list.add(drinkResource.getStrIngredient1());
        return new HashSet<>(list);*/

    }
}