package com.ezgroceries.services.external;

import com.ezgroceries.persistence.entities.CocktailEntity;
import com.ezgroceries.persistence.repositories.CocktailRepository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailDBClient {
        @GetMapping(value = "search.php")
        CocktailDBResponse searchCocktails(@RequestParam("s") String search);
}

