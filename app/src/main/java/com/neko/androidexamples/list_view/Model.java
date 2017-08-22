package com.neko.androidexamples.list_view;

/**
 * Created by N3K0 on 8/22/17.
 */

public class Model {
    private int image;
    private String name;
    private String description;

    public Model(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
