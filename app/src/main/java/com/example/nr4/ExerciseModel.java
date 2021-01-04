package com.example.nr4;

public class ExerciseModel {
    private int id;
    private String Name;


    public ExerciseModel(int id, String name) {
        this.id = id;
        Name = name;
    }

    public ExerciseModel() {
    }

    @Override
    public String toString() {
        return Name ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
