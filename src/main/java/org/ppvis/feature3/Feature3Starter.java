package org.ppvis.feature3;

import org.ppvis.MainStarter;
import org.ppvis.controller.AddProductController;
import org.ppvis.controller.AllRecipesController;
import org.ppvis.controller.CheckMissedProductsForRecipeController;
import org.ppvis.controller.StartWindowController;
import org.ppvis.feature3.controller.AddKitchenApplianceController;
import org.ppvis.feature3.controller.GetRecipeByMealTimeAndRequiredComponentWithKitchenApplianceController;
import org.ppvis.feature3.model.KitchenApplianceImpl;
import org.ppvis.feature3.model.KitchenApplianceRepository;
import org.ppvis.feature3.model.KitchenApplianceRepositoryImpl;
import org.ppvis.feature3.view.AddKitchenApplianceCommand;
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

public class Feature3Starter {
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

        KitchenApplianceRepository repository = new KitchenApplianceRepositoryImpl();
        repository.add(new KitchenApplianceImpl("someApp"), 10);

        MainLoop mainLoop = new MainLoopImpl(new StartQuestionCommand(new StartWindowController(lastRecipe)));
        mainLoop.addCommand(new GetRecipesCommand(new GetRecipeByMealTimeAndRequiredComponentWithKitchenApplianceController(loadedRecipesRepository, loadedProductsRepository, repository)));
        mainLoop.addCommand(new AddKitchenApplianceCommand(new AddKitchenApplianceController(repository)));
        mainLoop.addCommand(new DisplayAllRecipesCommand(new AllRecipesController(loadedRecipesRepository)));
        mainLoop.addCommand(new GetMissedProductsByProductIdCommand(new CheckMissedProductsForRecipeController(loadedRecipesRepository, loadedProductsRepository)));
        mainLoop.addCommand(new AddProductsCommand(new AddProductController(loadedProductsRepository)));

        mainLoop.run();
    }
}
