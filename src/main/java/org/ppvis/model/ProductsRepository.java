package org.ppvis.model;

import java.util.List;

public interface ProductsRepository {
    List<FoodProduct> getAllProducts();

    Integer getProductsCount(FoodProduct product);

    void addProduct(FoodProduct product, Integer count);

    void removeProduct(FoodProduct product, Integer count);
}
