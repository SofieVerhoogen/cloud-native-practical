package com.ezgroceries.shoppinglist;

import com.ezgroceries.client.CocktailDBClient;
import com.ezgroceries.client.CocktailDBResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ComponentScan("com.ezgroceries.controller")

public class MockMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailDBClient cocktailDBClient;

    @Test
    public void getTest() throws Exception {

       given(cocktailDBClient.searchCocktails("Russian")).willReturn(getMockedCocktails());

        mockMvc
                .perform(get("/cocktails").param("search", "Russian")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$[0].cocktailName").value("Black Russian"))
                .andExpect(jsonPath("$[0].cocktailGlass").value("Old-fashioned glass"))
                .andExpect(jsonPath("$[1].ingredients[0]").value("Vodka"))
                .andExpect(jsonPath("$..instructions").exists())
                .andExpect(jsonPath("$..ingredients").exists())
        ;
    }

    private CocktailDBResponse getMockedCocktails() {
        System.out.println("test cockatils");
        List<CocktailDBResponse.DrinkResource> cocktails = new ArrayList<>();
        CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
        drinkResource.setStrDrink("Black Russian");
        drinkResource.setStrGlass("Old-fashioned glass");
        drinkResource.setStrInstructions("Pour the ingredients into an old fashioned glass filled with ice cubes. Stir gently");
        drinkResource.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");
        drinkResource.setStrIngredient1("Coffee liqueur");
        drinkResource.setStrIngredient2("Vodka");
        cocktails.add(drinkResource);
        drinkResource = new CocktailDBResponse.DrinkResource();
        drinkResource.setStrDrink("White Russian");
        drinkResource.setStrGlass("Old-fashioned glass");
        drinkResource.setStrInstructions("instructies");
        drinkResource.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/vsrupw1472405732.jpg");
        drinkResource.setStrIngredient1("Vodka");
        drinkResource.setStrIngredient2("Water");
        cocktails.add(drinkResource);
        CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
        cocktailDBResponse.setDrinks(cocktails);
        return cocktailDBResponse;
    }
}