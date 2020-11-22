package com.ezgroceries.service;

import com.ezgroceries.repositories.CocktailRepository;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public CocktailResource create(CocktailResource cocktailResource) {
        return cocktailResource;

    }
}