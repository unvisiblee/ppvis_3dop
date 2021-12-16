package org.ppvis.model.impl;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;

import java.util.List;

public class OriginalSourceComponent implements FilterComponent {
    private final RecipesRepository recipesRepository;

    public OriginalSourceComponent(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    @Override
    public List<Recipe> getFilteredElements() {
        return recipesRepository.getAllRecipes();
    }
}
