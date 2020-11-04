package com.ezgroceries.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class Cocktail {

    private UUID cocktailID;
    private String name;
    private String glass;
    private String instructions;
    private Image image;
    private List ingredients = new List<String>();

    public long getCocktailID() {
        return cocktailID;
    }

    public void setCocktailID(UUID cockttailID) {
        this.cocktailID = cockttailID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }

    public Cocktail(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
