package org.ppvis.feature3.controller;

import org.ppvis.feature3.model.KitchenApplianceImpl;
import org.ppvis.feature3.model.KitchenApplianceRepository;

public class AddKitchenApplianceController {
    private final KitchenApplianceRepository repository;

    public AddKitchenApplianceController(KitchenApplianceRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String applianceName, Integer count) {
        repository.add(new KitchenApplianceImpl(applianceName), count);
    }
}
