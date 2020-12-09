package com.ezgroceries.persistence.entities;

import com.ezgroceries.services.converter.StringSetConverter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    private UUID cocktailId;

    @Column(name = "ID_DRINK")
    private String idDrink;

    @Column(name = "NAME")
    private String cocktailName;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    private String glass;
    private String instructions;
    private String image;


    public CocktailEntity(){}

    public CocktailEntity(String idDrink, String cocktailName, String glass, String instructions, String image) {
        this.cocktailId = UUID.randomUUID();
        this.idDrink = idDrink;
        this.cocktailName = cocktailName;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
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

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
