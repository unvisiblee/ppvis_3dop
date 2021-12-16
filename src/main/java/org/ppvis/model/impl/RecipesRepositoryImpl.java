package org.ppvis.model.impl;

import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;
import org.ppvis.model.Savable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipesRepositoryImpl implements RecipesRepository, Savable {
    private final List<Recipe> recipes = new ArrayList<>();

    @Override
    public List<Recipe> getAllRecipes() {
        return Collections.unmodifiableList(recipes);
    }

    @Override
    public void createNewRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

}
