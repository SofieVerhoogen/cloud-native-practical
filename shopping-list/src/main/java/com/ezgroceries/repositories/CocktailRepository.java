package com.ezgroceries.repositories;

import com.ezgroceries.entities.CocktailEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {

    public CocktailEntity save(CocktailEntity newCocktailEntity);

    public List<CocktailEntity> findByIdDrinkIn(List<String> ids);
    public List<CocktailEntity> findByCocktailIdIn(List<UUID> ids);
}
