package org.ppvis.model.impl;

import org.ppvis.model.FilterComponent;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Recipe;
import org.ppvis.model.RecipeStep;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByExistedProducts implements FilterComponent {
    private final FilterComponent filter;
    private final ProductsRepository productsRepository;

    public FilterByExistedProducts(FilterComponent filter, ProductsRepository repository) {
        this.filter = filter;
        this.productsRepository = repository;
    }

    @Override
    public List<Recipe> getFilteredElements() {
        return filter.getFilteredElements().stream().filter(e -> {
            for (RecipeStep k : e.getRecipeSteps()) {
                var existCount = productsRepository.getProductsCount(k.getProduct());
                if (existCount < k.getProductsCount())
                    return false;
            }
            return true;
        }).collect(Collectors.toList());
    }
}
