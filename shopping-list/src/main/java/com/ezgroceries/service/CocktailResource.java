package com.ezgroceries.service;

import com.ezgroceries.model.Cocktail;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CocktailResource {

    private Cocktail cocktail;
    public CocktailResource(){

    }
    public CocktailResource(UUID uuid, String cocktailName, String cocktail_glass, String instructions, String image, List<String> ingredients) {
        cocktail.setCocktailID(uuid);
        cocktail.setName(cocktailName);
        cocktail.setGlass(cocktail_glass);
        cocktail.setInstructions(instructions);
        cocktail.setInstructions(image);
        cocktail.setIngredients((ArrayList) ingredients);
    }
}
