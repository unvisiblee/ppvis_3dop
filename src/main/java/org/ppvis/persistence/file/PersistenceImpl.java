package org.ppvis.persistence.file;

import org.ppvis.persistence.Persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenceImpl<T> implements Persistence<T> {
    private final File file;

    public PersistenceImpl(File file) {
        this.file = file;
    }

    @Override
    public T load() {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            T obj = (T) objectIn.readObject();
            objectIn.close();
            return obj;
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public void save(T t) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(t);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
