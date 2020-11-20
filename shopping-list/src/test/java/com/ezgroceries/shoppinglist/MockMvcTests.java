package com.ezgroceries.shoppinglist;

import com.ezgroceries.client.CocktailDBClient;
import com.ezgroceries.client.CocktailDBResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.parseMediaType("application/json")))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].cocktailName").value("Black Russian"))
                .andExpect(jsonPath("$[0].cocktailGlass").value("Old-fashioned glass"))
                .andExpect(jsonPath("$[0].instruction").value("Pour the ingredients into an old fashioned glass filled with ice cubes. Stir gently"))
                .andExpect(jsonPath("$[0].image").value("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"))
                .andExpect(jsonPath("$[0].ingredients.length()").value(2))
                .andExpect(jsonPath("$[0].ingredients[0]").value("Coffee liqueur"))
                .andExpect(jsonPath("$[0].ingredients[1]").value("Vodka"))
                .andExpect(jsonPath("$[1].cocktailName").value("White Russian"))
                .andExpect(jsonPath("$[1].cocktailGlass").value("Old-fashioned glass"))
                .andExpect(jsonPath("$[1].instruction").value("instructies"))
                .andExpect(jsonPath("$[1].image").value("https://www.thecocktaildb.com/images/media/drink/vsrupw1472405732.jpg"))
                .andExpect(jsonPath("$[1].ingredients.length()").value(2))
                .andExpect(jsonPath("$[1].ingredients[0]").value("Vodka"))
                .andExpect(jsonPath("$[1].ingredients[1]").value("Water"))
                ;
    }

    private CocktailDBResponse getMockedCocktails() {
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