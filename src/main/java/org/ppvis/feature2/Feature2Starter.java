package org.ppvis.feature2;

import org.ppvis.MainStarter;
import org.ppvis.controller.AddProductController;
import org.ppvis.controller.AllRecipesController;
import org.ppvis.controller.CheckMissedProductsForRecipeController;
import org.ppvis.controller.GetRecipesByMealTimeAndRequiredComponentController;
import org.ppvis.controller.StartWindowController;
import org.ppvis.feature2.controller.GetRecipesByMealTimeAndRequiredComponentForSeveralPeopleController;
import org.ppvis.feature2.view.GetRecipesForSeveralPeopleCommand;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipesRepository;
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

public class Feature2Starter {
    public static void main(String[] args) throws URISyntaxException {
        URI recipesDatabaseURI = MainStarter.class.getResource("/RecipesDatabase.bin").toURI();
        File recipeDatabaseFile = new File(recipesDatabaseURI);
        Persistence<RecipesRepository> recipesRepositoryPersistence = new PersistenceImpl<>(recipeDatabaseFile);
        RecipesRepository loadedRecipesRepository = recipesRepositoryPersistence.load();

        URI productsDatabaseURI = MainStarter.class.getResource("/ProductsDatabase.bin").toURI();
        File productsDatabaseFile = new File(productsDatabaseURI);
        Persistence<ProductsRepository> productsRepositoryPersistence = new PersistenceImpl<>(productsDatabaseFile);
        ProductsRepository loadedProductsRepository = productsRepositoryPersistence.load();

        URI lastStateUri = MainStarter.class.getResource("/LastUsedRecipe.bin").toURI();
        File fileWithState = new File(lastStateUri);
        Persistence<Recipe> lastRecipe = new PersistenceImpl<>(fileWithState);

        MainLoop mainLoop = new MainLoopImpl(new StartQuestionCommand(new StartWindowController(lastRecipe)));
        mainLoop.addCommand(new GetRecipesCommand(new GetRecipesByMealTimeAndRequiredComponentController(loadedRecipesRepository, loadedProductsRepository)));
        mainLoop.addCommand(new DisplayAllRecipesCommand(new AllRecipesController(loadedRecipesRepository)));
        mainLoop.addCommand(new GetMissedProductsByProductIdCommand(new CheckMissedProductsForRecipeController(loadedRecipesRepository, loadedProductsRepository)));
        mainLoop.addCommand(new AddProductsCommand(new AddProductController(loadedProductsRepository)));
        mainLoop.addCommand(new GetRecipesForSeveralPeopleCommand(new GetRecipesByMealTimeAndRequiredComponentForSeveralPeopleController(loadedRecipesRepository, loadedProductsRepository)));

        mainLoop.run();
    }
}
