package org.ppvis;

import org.ppvis.controller.AddProductController;
import org.ppvis.controller.AllRecipesController;
import org.ppvis.controller.CheckMissedProductsForRecipeController;
import org.ppvis.controller.GetRecipesByMealTimeAndRequiredComponentController;
import org.ppvis.controller.StartWindowController;
import org.ppvis.model.MealTime;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;
import org.ppvis.model.impl.FilterByMealTime;
import org.ppvis.model.impl.FilterByRequiredProduct;
import org.ppvis.model.impl.FoodProductImpl;
import org.ppvis.model.impl.OriginalSourceComponent;
import org.ppvis.model.impl.ProductsRepositoryImpl;
import org.ppvis.model.impl.RecipeImpl;
import org.ppvis.model.impl.RecipeInstructionImpl;
import org.ppvis.model.impl.RecipeStepImpl;
import org.ppvis.model.impl.RecipesRepositoryImpl;
import org.ppvis.persistence.Persistence;
import org.ppvis.persistence.file.PersistenceImpl;
import org.ppvis.view.AddProductsCommand;
import org.ppvis.view.DisplayAllRecipesCommand;
import org.ppvis.view.GetMissedProductsByProductIdCommand;
import org.ppvis.view.GetRecipesCommand;
import org.ppvis.view.MainLoop;
import org.ppvis.view.MainLoopImpl;
import org.ppvis.view.StartQuestionCommand;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class MainStarter {
    public static void main(String[] args) throws URISyntaxException {
        RecipesRepository repository = new RecipesRepositoryImpl();
        repository.createNewRecipe(new RecipeImpl(MealTime.BREAKFAST, new RecipeInstructionImpl("Meal 1")));

        Recipe teaRecipe = new RecipeImpl(MealTime.BREAKFAST, new RecipeInstructionImpl("Pour the tea with boiling water. Wait three to five minutes. Add sugar to taste."));
        FoodProductImpl tea = new FoodProductImpl("tea");
        FoodProductImpl water = new FoodProductImpl("water");
        FoodProductImpl sugar = new FoodProductImpl("sugar");
        teaRecipe.addRecipeStep(new RecipeStepImpl(tea, 1));
        teaRecipe.addRecipeStep(new RecipeStepImpl(water, 9));
        teaRecipe.addRecipeStep(new RecipeStepImpl(sugar, 2));
        repository.createNewRecipe(teaRecipe);

        Recipe recipeWithMissedProducts = new RecipeImpl(MealTime.LUNCH, new RecipeInstructionImpl("Some other instruction"));
        recipeWithMissedProducts.addRecipeStep(new RecipeStepImpl(new FoodProductImpl("sugar"), 100));

        repository.createNewRecipe(recipeWithMissedProducts);
        repository.createNewRecipe(new RecipeImpl(MealTime.DINNER, new RecipeInstructionImpl("And one more instruction")));

        ProductsRepository productsRepository = new ProductsRepositoryImpl();
        productsRepository.addProduct(tea, 10);
        productsRepository.addProduct(water, 10);
        productsRepository.addProduct(sugar, 4);

        URI uri = MainStarter.class.getResource("/RecipesDatabase.bin").toURI();
        File file = new File(uri);
        Persistence<RecipesRepository> recipesRepositoryPersistence = new PersistenceImpl<>(file);
        recipesRepositoryPersistence.save(repository);

        URI lastStateUri = MainStarter.class.getResource("/LastUsedRecipe.bin").toURI();
        File fileWithState = new File(lastStateUri);
        Persistence<Recipe> lastRecipe = new PersistenceImpl<>(fileWithState);
        lastRecipe.save(teaRecipe);


        MainLoop mainLoop = new MainLoopImpl(new StartQuestionCommand(new StartWindowController(lastRecipe)));
        mainLoop.addCommand(new GetRecipesCommand(new GetRecipesByMealTimeAndRequiredComponentController(repository, productsRepository)));
        mainLoop.addCommand(new DisplayAllRecipesCommand(new AllRecipesController(repository)));
        mainLoop.addCommand(new GetMissedProductsByProductIdCommand(new CheckMissedProductsForRecipeController(repository, productsRepository)));
        mainLoop.addCommand(new AddProductsCommand(new AddProductController(productsRepository)));

        mainLoop.run();
    }
}
