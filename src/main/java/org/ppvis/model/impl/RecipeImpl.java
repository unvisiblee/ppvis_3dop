package org.ppvis.model.impl;

import org.ppvis.model.MealTime;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipeInstruction;
import org.ppvis.model.RecipeStep;
import org.ppvis.model.Savable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeImpl implements Recipe, Savable {
    private final List<RecipeStep> steps = new ArrayList<>();
    private MealTime mealTime;
    private RecipeInstruction instruction;

    public RecipeImpl(MealTime mealTime, RecipeInstruction instruction) {
        this.mealTime = mealTime;
        this.instruction = instruction;
    }

    @Override
    public List<RecipeStep> getRecipeSteps() {
        return Collections.unmodifiableList(steps);
    }

    @Override
    public void addRecipeStep(RecipeStep step) {
        steps.add(step);
    }

    @Override
    public void removeRecipeStep(RecipeStep step) {
        steps.remove(step);
    }

    @Override
    public MealTime getMealTime() {
        return mealTime;
    }

    @Override
    public void setMealTime(MealTime mealTime) {
        this.mealTime = mealTime;
    }

    @Override
    public RecipeInstruction getInstruction() {
        return instruction;
    }

    @Override
    public void setInstruction(RecipeInstruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Meal for ").append(getMealTime().toString().toLowerCase()).append("\n");
        result.append("Instruction:\n").append(getInstruction().toString()).append("\n");
        result.append("Required products:\n");
        List<RecipeStep> recipeSteps = getRecipeSteps();
        for (int i = 0; i < recipeSteps.size(); i++) {
            RecipeStep recipeStep = recipeSteps.get(i);
            result.append(i).append(") ").append(recipeStep.toString()).append("\n");
        }
        return result.toString();
    }
}
