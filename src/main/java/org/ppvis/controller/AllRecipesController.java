package org.ppvis.controller;

import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;

import java.util.List;

public class AllRecipesController {
    private final RecipesRepository repository;

    public AllRecipesController(RecipesRepository repository) {
        this.repository = repository;
    }

    public String getAllRecipes() {
        List<Recipe> allRecipes = repository.getAllRecipes();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < allRecipes.size(); i++) {
            Recipe recipe = allRecipes.get(i);
            result.append(i).append(") ").append(recipe.toString()).append('\n');
        }
        return result.toString();
    }
}
