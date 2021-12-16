package org.ppvis.model.impl;

import org.ppvis.model.RecipeInstruction;
import org.ppvis.model.Savable;

import java.io.Serializable;

public class RecipeInstructionImpl implements RecipeInstruction, Savable {
    private String text;

    public RecipeInstructionImpl(String text) {
        this.text = text;
    }

    @Override
    public String getInstructionText() {
        return text;
    }

    @Override
    public void setInstructionText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
