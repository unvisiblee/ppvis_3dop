package org.ppvis.feature3.model;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.Recipe;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByExistedKitchenAppliance implements FilterComponent {
    private final FilterComponent filter;
    private final KitchenApplianceRepository repository;

    public FilterByExistedKitchenAppliance(FilterComponent filter, KitchenApplianceRepository repository) {
        this.filter = filter;
        this.repository = repository;
    }

    @Override
    public List<Recipe> getFilteredElements() {
        return filter.getFilteredElements().stream().filter(e -> {
            if (e instanceof RecipeWithKitchenAppliance) {
                for (KitchenAppliance k : ((RecipeWithKitchenAppliance) e).getAllRequiredKitchenAppliance()) {
                    var existCount = repository.getAppliancesCount(k);
                    if (existCount < ((RecipeWithKitchenAppliance) e).getRequiredKitchenApplianceCount(k))
                        return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}
