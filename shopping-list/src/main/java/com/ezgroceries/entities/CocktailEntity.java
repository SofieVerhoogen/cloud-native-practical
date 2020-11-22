package com.ezgroceries.entities;

import com.ezgroceries.converter.StringSetConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    private UUID cocktailId;

    @Column(name = "NAME")
    private String cocktailName;

    private String cocktailGlass;
    private String instructions;
    private String image;
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailEntity(){}

    public CocktailEntity(UUID cocktailId, String cocktailName, String cocktailGlass, String instructions, String image, Set<String> ingredients) {
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

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
