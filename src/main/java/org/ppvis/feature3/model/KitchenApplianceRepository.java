package org.ppvis.feature3.model;

import java.util.List;

public interface KitchenApplianceRepository {
    List<KitchenAppliance> getAll();
    Integer getAppliancesCount(KitchenAppliance appliance);
    void add(KitchenAppliance appliance, Integer count);
    void remove(KitchenAppliance appliance, Integer count);}
