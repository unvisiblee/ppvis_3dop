package org.ppvis.controller;

import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipeStep;
import org.ppvis.model.RecipesRepository;

import java.util.ArrayList;
import java.util.List;

public class CheckMissedProductsForRecipeController {
    private final RecipesRepository recipesRepository;
    private final ProductsRepository productsRepository;

    public CheckMissedProductsForRecipeController(RecipesRepository recipesRepository, ProductsRepository productsRepository) {
        this.recipesRepository = recipesRepository;
        this.productsRepository = productsRepository;
    }

    public List<String> getMissingProductsForRecipeById(Integer id) {
        Recipe choseRecipe = recipesRepository.getAllRecipes().get(id);
        List<String> missedProductsList = new ArrayList<>();
        for (RecipeStep recipeStep : choseRecipe.getRecipeSteps()) {
            Integer productsCountInRepository = productsRepository.getProductsCount(recipeStep.getProduct());
            if (productsCountInRepository < recipeStep.getProductsCount()) {
                missedProductsList.add("You have only " + productsCountInRepository + " of " + recipeStep.getProduct() +
                        ", but " + recipeStep.getProductsCount() + " needed\n"
                );
            }
        }
        return missedProductsList;
    }

}
