package org.ppvis.model;

public interface RecipeStep {
    FoodProduct getProduct();

    void setProduct(FoodProduct product);

    Integer getProductsCount();

    void setProductCount(Integer count);
}
