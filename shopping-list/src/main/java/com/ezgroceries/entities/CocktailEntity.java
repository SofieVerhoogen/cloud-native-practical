package com.ezgroceries.entities;

<<<<<<< Updated upstream:shopping-list/src/main/java/com/ezgroceries/entities/CocktailEntity.java
import com.ezgroceries.client.CocktailDBResponse;
import com.ezgroceries.converter.StringSetConverter;
=======
import com.ezgroceries.services.utils.StringSetConverter;
>>>>>>> Stashed changes:shopping-list/src/main/java/com/ezgroceries/persistence/entities/CocktailEntity.java

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

    @Column(name = "ID_DRINK")
    private String idDrink;

    @Column(name = "NAME")
    private String cocktailName;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailEntity(){}

    public CocktailEntity(String idDrink, String cocktailName) {
        this.cocktailId = UUID.randomUUID();
        this.idDrink = idDrink;
        this.cocktailName = cocktailName;
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
}
