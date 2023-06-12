package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;

import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxNumInLocation, int speed, double currentFoodForSatiety) {
        super(weight, maxNumInLocation, speed, currentFoodForSatiety);
        this.maxFoodForSatiety = currentFoodForSatiety;
    }

}
