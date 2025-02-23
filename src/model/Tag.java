package model;

import java.util.UUID;

public class Tag {
    private String id;
    private String name;
    private String color;

    public Tag(String name, String color) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.color = color;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
