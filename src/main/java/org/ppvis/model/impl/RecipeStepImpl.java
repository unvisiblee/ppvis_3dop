package org.ppvis.model.impl;

import org.ppvis.model.FoodProduct;
import org.ppvis.model.RecipeStep;
import org.ppvis.model.Savable;

public class RecipeStepImpl implements RecipeStep, Savable {
    private FoodProduct product;
    private Integer productCount;

    public RecipeStepImpl(FoodProduct product, Integer productCount) {
        this.product = product;
        this.productCount = productCount;
    }

    @Override
    public FoodProduct getProduct() {
        return product;
    }

    @Override
    public void setProduct(FoodProduct product) {
        this.product = product;
    }

    @Override
    public Integer getProductsCount() {
        return productCount;
    }

    @Override
    public void setProductCount(Integer count) {
        this.productCount = count;
    }

    @Override
    public String toString() {
        return "product - " + product + "; count - " + productCount;
    }
}
