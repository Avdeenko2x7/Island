package com.javarush.island.avdeenko.island;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int x;
    private int y;
    private List<Animal> animals;
    private List<Plant> plants;
    private Island island;

    public Location(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public Island getIsland() {
        return island;
    }
}
