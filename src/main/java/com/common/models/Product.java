package com.common.models;

import javafx.scene.image.Image;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String title;
    private String description;
    transient private Image image;
    private int categoryID;
    private int price;
    private int quentity;

    public Product(int id, String title, String description, Image image, int categoryID, int price, int quentity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quentity = quentity;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }
    public int getPrice() {return price;}
    public int getCategoryID() {
        return categoryID;
    }

    public int getQuentity() {
        return quentity;
    }


    private void writeObject(ObjectOutputStream os) {
        try {
            os.defaultWriteObject();
            if (image != null) {
                SerializableRenderedImage serializableImage = new SerializableRenderedImage();
                serializableImage.setImage(image);
                os.writeObject(serializableImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream is) {
        try {
            is.defaultReadObject();
            this.image = ((SerializableRenderedImage) is.readObject()).getImage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "description='" + description + '\'' + "\n" +
                "image=" + image + '\'' + "\n" +
                "categoryID='" + categoryID + '\'' + "\n" +
                "price='" + price + '\'' + "\n" +
                "quentity='" + quentity + '\'' + "\n";
    }
}
