package org.ppvis.feature2.controller;

import org.ppvis.controller.GetRecipesByMealTimeAndRequiredComponentController;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;

public class GetRecipesByMealTimeAndRequiredComponentForSeveralPeopleController extends GetRecipesByMealTimeAndRequiredComponentController {

    public GetRecipesByMealTimeAndRequiredComponentForSeveralPeopleController(RecipesRepository repository,
                                                                              ProductsRepository productsRepository) {
        super(repository, productsRepository);
    }

    public void removeUsedProducts(Recipe recipe, Integer peoplesCount) {
        for (int i = 0; i < peoplesCount; i++) {
            super.removeUsedProducts(recipe);
        }
    }
}
