package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxNumInLocation, int speed, double currentFoodForSatiety) {
        super(weight, maxNumInLocation, speed, currentFoodForSatiety);
    }

}
