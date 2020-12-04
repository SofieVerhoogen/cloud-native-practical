package com.ezgroceries.controller;
 import com.ezgroceries.services.CocktailResource;
 import com.ezgroceries.services.CocktailService;
 import org.springframework.web.bind.annotation.*;
 import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class CocktailController {

    private CocktailService cocktailService;

    private CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(value= "/cocktails")
    public List<CocktailResource> get(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
    }


}

