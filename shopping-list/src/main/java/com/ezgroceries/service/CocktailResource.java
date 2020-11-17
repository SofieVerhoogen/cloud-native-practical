package com.ezgroceries.service;

<<<<<<< HEAD
=======
import com.ezgroceries.model.Cocktail;

import java.util.ArrayList;
>>>>>>> 0967b01d99a6d74b06ae53f9bd744aa642c7bc1c
import java.util.List;
import java.util.UUID;

public class CocktailResource {

<<<<<<< HEAD
    private UUID cocktailId;
    private String cocktailName;
    private String cocktailGlass;
    private String instructions;
    private String image;
    private List<String> ingredients;

    public CocktailResource(UUID cocktailId, String cocktailName, String cocktailGlass, String instructions, String image, List<String> ingredients) {

        this.cocktailId = cocktailId;
        this.cocktailName = cocktailName;
        this.cocktailGlass = cocktailGlass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getCocktailGlass() {
        return cocktailGlass;
    }

    public void setCocktailGlass(String cocktailGlass) {
        this.cocktailGlass = cocktailGlass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

=======
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
>>>>>>> 0967b01d99a6d74b06ae53f9bd744aa642c7bc1c
}
