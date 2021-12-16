package org.ppvis.persistence;

public interface Persistence <T>{
    T load();
    void save(T t);
}
