package com.common.models;

import javafx.collections.ObservableList;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" +
                "name='" + name + '\'' + "\n";
    }
}
