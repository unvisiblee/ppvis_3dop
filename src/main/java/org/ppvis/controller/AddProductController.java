package org.ppvis.controller;

import org.ppvis.model.ProductsRepository;
import org.ppvis.model.impl.FoodProductImpl;

public class AddProductController {
    private final ProductsRepository repository;

    public AddProductController(ProductsRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String productName, Integer count){
        repository.addProduct(new FoodProductImpl(productName), count);
    }
}
