package org.ppvis.model.impl;

import org.ppvis.model.FoodProduct;
import org.ppvis.model.Savable;

import java.io.Serializable;
import java.util.Objects;

public class FoodProductImpl implements FoodProduct, Savable {
    private final String productName;

    public FoodProductImpl(String productName) {
        this.productName = productName;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FoodProductImpl that = (FoodProductImpl) o;
        return Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    @Override
    public String toString() {
        return productName;
    }
}
