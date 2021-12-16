package org.ppvis.model.impl;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.MealTime;
import org.ppvis.model.Recipe;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByMealTime implements FilterComponent {
    private final FilterComponent filter;
    private final MealTime mealTime;

    public FilterByMealTime(FilterComponent filter, MealTime mealTime) {
        this.filter = filter;
        this.mealTime = mealTime;
    }

    @Override
    public List<Recipe> getFilteredElements() {
        return filter.getFilteredElements()
                .stream()
                .filter(e -> e.getMealTime().equals(mealTime))
                .collect(Collectors.toList());
    }
}
