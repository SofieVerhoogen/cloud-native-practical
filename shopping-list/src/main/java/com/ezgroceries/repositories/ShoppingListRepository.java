package com.ezgroceries.repositories;

import com.ezgroceries.entities.ShoppingListEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {

    ShoppingListEntity save(ShoppingListEntity shoppingListEntity);

    List<ShoppingListEntity> findAll();

    List<ShoppingListEntity> findByName(String shoppingListName);

    ShoppingListEntity findById(UUID shoppingListId);
}
