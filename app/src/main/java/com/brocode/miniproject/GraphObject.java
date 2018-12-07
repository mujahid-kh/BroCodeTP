package com.brocode.miniproject;

public class GraphObject {

    private String title;
    private float time;
    private float budget;
    private int[][] coordinates;

    public GraphObject() {
    }

    public String getTitle() {
        return title;
    }

    public float getTime() {
        return time;
    }

    public float getBudget() {
        return budget;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }
}
