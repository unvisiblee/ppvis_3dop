package org.ppvis.model.impl;

import org.ppvis.model.FoodProduct;
import org.ppvis.model.ProductsRepository;
import org.ppvis.model.Savable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsRepositoryImpl implements ProductsRepository, Savable {

    private final Map<FoodProduct, Integer> products = new HashMap<>();

    @Override
    public List<FoodProduct> getAllProducts() {
        return new ArrayList<>(products.keySet());
    }

    @Override
    public Integer getProductsCount(FoodProduct product) {
        Integer result = products.get(product);
        if (result == null) return 0;
        return result;
    }

    @Override
    public void addProduct(FoodProduct product, Integer count) {
        products.merge(product, count, Integer::sum);
    }

    @Override
    public void removeProduct(FoodProduct product, Integer count) {
        Integer currentCount = products.get(product);
        if (count > currentCount)
            throw new RuntimeException("Try te delete more products than exist");
        else if (count.equals(currentCount))
            products.remove(product);
        products.put(product, currentCount - count);
    }
}
