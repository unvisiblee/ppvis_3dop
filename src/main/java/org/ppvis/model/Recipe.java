package org.ppvis.model;

import java.util.List;


public interface Recipe {
    List<RecipeStep> getRecipeSteps();

    void addRecipeStep(RecipeStep step);

    void removeRecipeStep(RecipeStep step);

    MealTime getMealTime();

    void setMealTime(MealTime mealTime);

    RecipeInstruction getInstruction();

    void setInstruction(RecipeInstruction instruction);
}
