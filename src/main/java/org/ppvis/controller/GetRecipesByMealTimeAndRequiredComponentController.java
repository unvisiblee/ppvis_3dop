package org.ppvis.controller;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.FoodProduct;
import org.ppvis.model.MealTime;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipeStep;
import org.ppvis.model.RecipesRepository;
import org.ppvis.model.impl.FilterByExistedProducts;
import org.ppvis.model.impl.FilterByMealTime;
import org.ppvis.model.impl.FilterByRequiredProduct;
import org.ppvis.model.impl.FoodProductImpl;
import org.ppvis.model.impl.OriginalSourceComponent;

import java.util.List;

public class GetRecipesByMealTimeAndRequiredComponentController {
    protected final RecipesRepository repository;
    protected final ProductsRepository productsRepository;

    public GetRecipesByMealTimeAndRequiredComponentController(RecipesRepository repository, ProductsRepository productsRepository) {
        this.repository = repository;
        this.productsRepository = productsRepository;
    }

    public List<Recipe> findRecipes(String mealTimeName, String requiredProductName) {
        MealTime mealTime;
        try {
            mealTime = MealTime.valueOf(mealTimeName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
        FoodProduct requiredProduct = new FoodProductImpl(requiredProductName);
        FilterComponent searcher = new FilterByRequiredProduct(
                new FilterByMealTime(
                        new FilterByExistedProducts(
                                new OriginalSourceComponent(repository),
                                productsRepository),
                        mealTime),
                requiredProduct);
        return searcher.getFilteredElements();
    }

    public void removeUsedProducts(Recipe recipe) {
        for (RecipeStep recipeStep : recipe.getRecipeSteps()) {
            productsRepository.removeProduct(recipeStep.getProduct(), recipeStep.getProductsCount());
        }
    }
}
