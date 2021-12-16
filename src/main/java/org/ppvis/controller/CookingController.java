package org.ppvis.controller;

import org.ppvis.model.RecipesRepository;

public class CookingController {
    private final RecipesRepository recipesRepository;

    public CookingController(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public String getInstructionForMealBuId(){
        return "";
    }
}
