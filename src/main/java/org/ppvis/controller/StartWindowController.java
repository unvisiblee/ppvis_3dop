package org.ppvis.controller;

import org.ppvis.model.Recipe;
import org.ppvis.persistence.Persistence;

public class StartWindowController {
    private final Persistence<Recipe> lastRecipe;

    public StartWindowController(Persistence<Recipe> lastRecipe) {
        this.lastRecipe = lastRecipe;
    }

    public String loadLastRecipe() {
        Recipe load = lastRecipe.load();
        if (load == null) {
            return "Last state not found";
        }
        return load.toString();
    }

}
