package com.ezgroceries.repositories;

import com.ezgroceries.entities.CocktailEntity;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {


}
