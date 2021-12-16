package org.ppvis.feature7.controller;

import org.ppvis.controller.GetRecipesByMealTimeAndRequiredComponentController;
import org.ppvis.feature7.model.FilterByAllergyProduct;
import org.ppvis.model.FilterComponent;
import org.ppvis.model.FoodProduct;
import org.ppvis.model.MealTime;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;
import org.ppvis.model.impl.FilterByExistedProducts;
import org.ppvis.model.impl.FilterByMealTime;
import org.ppvis.model.impl.FilterByRequiredProduct;
import org.ppvis.model.impl.FoodProductImpl;
import org.ppvis.model.impl.OriginalSourceComponent;

import java.util.List;

public class GetRecipesByMealTimeAndRequiredComponentWithoutAllergyController extends GetRecipesByMealTimeAndRequiredComponentController {

    public GetRecipesByMealTimeAndRequiredComponentWithoutAllergyController(RecipesRepository repository, ProductsRepository productsRepository) {
        super(repository, productsRepository);
    }

    public List<Recipe> findRecipes(String mealTimeName, String requiredProductName, String allergy) {
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
                                new FilterByAllergyProduct(
                                        new OriginalSourceComponent(repository),
                                        new FoodProductImpl(allergy)),
                                productsRepository),
                        mealTime),
                requiredProduct);
        return searcher.getFilteredElements();
    }
}
