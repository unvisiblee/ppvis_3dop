package org.ppvis.feature3.controller;

import org.ppvis.controller.GetRecipesByMealTimeAndRequiredComponentController;
import org.ppvis.feature3.model.FilterByExistedKitchenAppliance;
import org.ppvis.feature3.model.KitchenApplianceRepository;
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

public class GetRecipeByMealTimeAndRequiredComponentWithKitchenApplianceController extends GetRecipesByMealTimeAndRequiredComponentController {
    protected final KitchenApplianceRepository applianceRepository;

    public GetRecipeByMealTimeAndRequiredComponentWithKitchenApplianceController(RecipesRepository repository,
                                                                                 ProductsRepository productsRepository,
                                                                                 KitchenApplianceRepository applianceRepository) {
        super(repository, productsRepository);
        this.applianceRepository = applianceRepository;
    }

    @Override
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
                                new FilterByExistedKitchenAppliance(
                                        new OriginalSourceComponent(repository),
                                        applianceRepository
                                ),
                                productsRepository),
                        mealTime),
                requiredProduct);
        return searcher.getFilteredElements();
    }
}
