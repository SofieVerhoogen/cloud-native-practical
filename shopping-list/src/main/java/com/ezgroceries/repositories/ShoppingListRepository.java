package com.ezgroceries.repositories;

import com.ezgroceries.entities.ShoppingListEntity;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {
}
