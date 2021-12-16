package org.ppvis.feature7.model;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.FoodProduct;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipeStep;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByAllergyProduct implements FilterComponent {
    private final FilterComponent filter;
    private final FoodProduct allergyProduct;


    public FilterByAllergyProduct(FilterComponent component, FoodProduct allergyProduct) {
        this.filter = component;
        this.allergyProduct = allergyProduct;
    }


    @Override
    public List<Recipe> getFilteredElements() {
        return filter.getFilteredElements().stream().filter(e -> {
            for (RecipeStep k : e.getRecipeSteps()) {
                if (k.getProduct().equals(allergyProduct))
                    return false;
            }
            return true;
        }).collect(Collectors.toList());
    }
}
