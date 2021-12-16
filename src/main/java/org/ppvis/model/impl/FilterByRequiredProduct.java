package org.ppvis.model.impl;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.FoodProduct;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipeStep;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterByRequiredProduct implements FilterComponent {
    private final FilterComponent filter;
    private final FoodProduct requiredProduct;

    public FilterByRequiredProduct(FilterComponent filter, FoodProduct requiredProduct) {
        this.filter = filter;
        this.requiredProduct = requiredProduct;
    }

    @Override
    public List<Recipe> getFilteredElements() {
        return filter.getFilteredElements()
                .stream()
                .filter(e -> e.getRecipeSteps()
                        .stream()
                        .anyMatch(k -> k.getProduct().equals(requiredProduct)))
                .collect(Collectors.toList());
    }


}
