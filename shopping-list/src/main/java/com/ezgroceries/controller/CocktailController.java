package com.ezgroceries.controller;;
import com.ezgroceries.services.CocktailResponse;
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
    public List<CocktailResponse> get(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
    }

}

