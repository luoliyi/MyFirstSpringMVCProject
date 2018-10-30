package com.nf.entities;

public class Size {
    private String length;
    private String height;
    private int weight;
    public Size(){}

    public Size(String length, String height, int weight) {
        this.length = length;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Size{" +
                "length='" + length + '\'' +
                ", height='" + height + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
