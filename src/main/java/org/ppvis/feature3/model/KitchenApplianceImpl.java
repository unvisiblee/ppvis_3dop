package org.ppvis.feature3.model;

import java.util.Objects;

public class KitchenApplianceImpl implements KitchenAppliance {
    private final String name;

    public KitchenApplianceImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        KitchenApplianceImpl that = (KitchenApplianceImpl) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
