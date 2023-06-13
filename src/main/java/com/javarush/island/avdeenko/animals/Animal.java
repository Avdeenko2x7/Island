package com.javarush.island.avdeenko.animals;

import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class Animal {
    protected double weight;
    protected int maxNumInLocation;
    protected int speed;
    protected double maxFoodForSatiety;
    protected double currentFoodForSatiety;

    public Animal(double weight, int maxNumInLocation, int speed, double currentFoodForSatiety) {
        this.weight = weight;
        this.maxNumInLocation = maxNumInLocation;
        this.speed = speed;
        this.currentFoodForSatiety = currentFoodForSatiety;
    }

    public Animal() {
    }

    public void move(Location location) {
        // Get a random neighboring location
        Location newLocation = getRandomNeighboringLocation(location);

        // Move the wolf to the new location
        location.removeAnimal(this);
        newLocation.addAnimal(this);
    }
    public abstract void eat(Location location, List<Animal> animals, List<Plant> plants);
    protected Location getRandomNeighboringLocation(Location location) {
        // Get the current location's coordinates

        if (location == null) {
            return null; // Или выберите другое значение по умолчанию
        }


        int x = location.getX();
        int y = location.getY();

        // Generate random offsets for x and y
        Random random = new Random();
        int xOffset = random.nextInt(speed) - 1; // -1, 0, or 1
        int yOffset = random.nextInt(speed) - 1; // -1, 0, or 1

        // Calculate the new coordinates
        int newX = x + xOffset;
        int newY = y + yOffset;

        // Make sure the new coordinates are within the valid range of locations on the island
        newX = Math.max(0, Math.min(location.getIsland().getIslandWidth() - 1, newX));
        newY = Math.max(0, Math.min(location.getIsland().getIslandHeight() - 1, newY));

        // Get the neighboring location
        return location.getIsland().getLocation(newX, newY);
    }

    protected boolean chanceToEat(int chance){
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        return chance > randomNumber;
    }

    protected void increaseSatiety(double percentage) {
        currentFoodForSatiety += (maxFoodForSatiety * percentage / 100.0);
    }

    protected boolean isDead(){
        return currentFoodForSatiety <= 0;
    }

    public abstract void reproduce(Location location);

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxNumInLocation() {
        return maxNumInLocation;
    }

    public void setMaxNumInLocation(int maxNumInLocation) {
        this.maxNumInLocation = maxNumInLocation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMaxFoodForSatiety() {
        return maxFoodForSatiety;
    }

    public void setMaxFoodForSatiety(double maxFoodForSatiety) {
        this.maxFoodForSatiety = maxFoodForSatiety;
    }

    public double getCurrentFoodForSatiety() {
        return currentFoodForSatiety;
    }

    public void setCurrentFoodForSatiety(double currentFoodForSatiety) {
        this.currentFoodForSatiety = currentFoodForSatiety;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0 && maxNumInLocation == animal.maxNumInLocation && speed == animal.speed && Double.compare(animal.maxFoodForSatiety, maxFoodForSatiety) == 0 && Double.compare(animal.currentFoodForSatiety, currentFoodForSatiety) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, maxNumInLocation, speed, maxFoodForSatiety, currentFoodForSatiety);
    }

}
