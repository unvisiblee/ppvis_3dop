package org.ppvis.feature3.model;

import org.ppvis.model.MealTime;
import org.ppvis.model.RecipeInstruction;
import org.ppvis.model.RecipeStep;
import org.ppvis.model.impl.RecipeImpl;

import java.util.List;

public class RecipeWithKitchenAppliance extends RecipeImpl {
    private final KitchenApplianceRepository requiredAppliance = new KitchenApplianceRepositoryImpl();

    public RecipeWithKitchenAppliance(MealTime mealTime, RecipeInstruction instruction) {
        super(mealTime, instruction);
    }

    public List<KitchenAppliance> getAllRequiredKitchenAppliance(){
        return requiredAppliance.getAll();
    }

    public Integer getRequiredKitchenApplianceCount(KitchenAppliance appliance){
        return requiredAppliance.getAppliancesCount(appliance);
    }

    public void addRequiredKitchenAppliance(KitchenAppliance appliance, Integer count) {
        requiredAppliance.add(appliance, count);
    }

    public void removeRequiredKitchenAppliance(KitchenAppliance appliance, Integer count) {
        requiredAppliance.remove(appliance, count);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Meal for ").append(getMealTime().toString().toLowerCase()).append("\n");
        result.append("Instruction:\n").append(getInstruction().toString()).append("\n");
        result.append("Required kitchen appliance:\n").append(getInstruction().toString()).append("\n");
        List<KitchenAppliance> kitchenAppliances = requiredAppliance.getAll();
        for (int i = 0; i < kitchenAppliances.size(); i++) {
            KitchenAppliance appliance = kitchenAppliances.get(i);
            result.append(i).append(") ").append(appliance.toString()).append("\n");
        }
        result.append("Required products:\n");
        List<RecipeStep> recipeSteps = getRecipeSteps();
        for (int i = 0; i < recipeSteps.size(); i++) {
            RecipeStep recipeStep = recipeSteps.get(i);
            result.append(i).append(") ").append(recipeStep.toString()).append("\n");
        }
        return result.toString();
    }
}
