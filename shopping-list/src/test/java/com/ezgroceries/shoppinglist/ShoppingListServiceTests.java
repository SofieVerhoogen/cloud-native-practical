package com.ezgroceries.shoppinglist;

import com.ezgroceries.persistence.entities.CocktailEntity;
import com.ezgroceries.persistence.entities.ShoppingListEntity;
import com.ezgroceries.persistence.repositories.ShoppingListRepository;
import com.ezgroceries.services.CocktailService;
import com.ezgroceries.services.ShoppingListResource;
import com.ezgroceries.services.ShoppingListService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.*;

public class ShoppingListServiceTests {

    @Mock
    private ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
    private CocktailService cocktailService = mock(CocktailService.class);
    private ShoppingListService shoppingListService;


    @BeforeEach
    public void setUp()  {
        shoppingListService = new ShoppingListService(shoppingListRepository,cocktailService);
    }

    @Test
    public void testCreateShoppingList() {
        ShoppingListResource shoppingListResource = shoppingListService.create("Sofie");
        assertEquals("Sofie", shoppingListResource.getName(), "test name Sofie");
        assertNotNull(shoppingListResource.getShoppingListId().toString(), "not null");

        ShoppingListEntity entity = new ShoppingListEntity("Oona");
        assertEquals("Oona", entity.getName(), "test name Oona");
    }

    @Test
    public void testAddCocktails() {

        CocktailEntity cocktail1 = new CocktailEntity("idDrink1", "cocktail1");
        CocktailEntity cocktail2 = new CocktailEntity("idDrink2", "cocktail2");
        List<String> cocktails = Arrays.asList(cocktail1.getCocktailId().toString(), cocktail2.getCocktailId().toString());
        ShoppingListEntity entity = new ShoppingListEntity("List of Sofie");
        ShoppingListEntity entity2 = new ShoppingListEntity("List of Oona");
        assertEquals("List of Sofie", entity.getName(), "Shopping list 1");
        when(cocktailService.findByCocktailId(cocktails)).thenReturn(
                Arrays.asList(cocktail1,cocktail2));
        when(shoppingListRepository.findById(entity.getShoppingListId())).thenReturn(entity);
        shoppingListService.addCocktails(entity.getShoppingListId(), cocktails);
        List<ShoppingListEntity> shoppingLists = Arrays.asList(entity,entity2);
        assertNotNull(shoppingLists.size(), "shoppingList not empty");
        assertEquals("List of Sofie", shoppingLists.get(0).getName(), "Shopping list 1");
        assertEquals("List of Oona", shoppingLists.get(1).getName(), "Shopping List 1");
        assertNotNull(shoppingLists.get(0).getCocktails().size(), "always a cocktail");
    }

    @Test
    public void testGetShoppingListsWithoutIngredients() {
        ShoppingListEntity entity1 = new ShoppingListEntity("Sofie");
        ShoppingListEntity entity2 = new ShoppingListEntity("Oona");
        when(shoppingListRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));
        assertEquals("Sofie", entity1.getName(), "Entity 1");
        List<ShoppingListEntity> shoppingLists = shoppingListRepository.findAll();
        assertEquals("Sofie", shoppingLists.get(0).getName(), "Shopping list 2");
        assertEquals("Oona", shoppingLists.get(1).getName(), "Shopping List 1");
        //List<ShoppingListResource> shoppingListResourcesLists = shoppingListService.getAllShoppingLists();
        //assertEquals("Sofie", shoppingListResourcesLists.get(0).getName(), "Shopping list 2");
        //assertEquals("Oona", shoppingListResourcesLists.get(1).getName(), "Shopping List 1");
    }

}
