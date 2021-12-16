package org.ppvis.model;

import java.util.List;


public interface RecipesRepository {
    List<Recipe> getAllRecipes();

    void createNewRecipe(Recipe recipe);

    void removeRecipe(Recipe recipe);
}
