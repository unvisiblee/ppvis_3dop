package org.ppvis.feature3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitchenApplianceRepositoryImpl implements KitchenApplianceRepository {
    private final Map<KitchenAppliance, Integer> applianceIntegerMap = new HashMap<>();

    @Override
    public List<KitchenAppliance> getAll() {
        return new ArrayList<>(applianceIntegerMap.keySet());
    }

    @Override
    public Integer getAppliancesCount(KitchenAppliance appliance) {
        Integer result = applianceIntegerMap.get(appliance);
        if (result == null)
            return 0;
        return result;
    }


    @Override
    public void add(KitchenAppliance appliance, Integer count) {
        applianceIntegerMap.merge(appliance, count, Integer::sum);
    }

    @Override
    public void remove(KitchenAppliance appliance, Integer count) {
        Integer currentCount = applianceIntegerMap.get(appliance);
        if (count > currentCount)
            throw new RuntimeException("Try te delete more products than exist");
        else if (count.equals(currentCount))
            applianceIntegerMap.remove(appliance);
        applianceIntegerMap.put(appliance, currentCount - count);
    }
}
