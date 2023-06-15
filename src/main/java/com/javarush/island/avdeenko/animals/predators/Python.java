package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Python extends Animal{
    public Python() {
        super(15, 30, 1, 3);
        this.maxFoodForSatiety = 3;
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        if (isDead()) {
            location.removeAnimal(this);
        } else {
            for (Animal animal : animals) {
                if (this.equals(animal)) continue;
                if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    switch (animal.getClass().getSimpleName()) {
                        case "Fox":
                            if (chanceToEat(15)) {
                                location.removeAnimal(animal);
                                increaseSatiety(25);
                            }
                            break;
                        case "Rabbit":
                            if (chanceToEat(20)) {
                                location.removeAnimal(animal);
                                increaseSatiety(25);
                            }
                            break;
                        case "Mouse":
                            if (chanceToEat(40)) {
                                location.removeAnimal(animal);
                                increaseSatiety(25);
                            }
                            break;
                        case "Duck":
                            if (chanceToEat(10)) {
                                location.removeAnimal(animal);
                                increaseSatiety(25);
                            }
                            break;
                    }
                }
            }
        }
    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Python && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() < 0.9) {
            Python newPython = new Python();
            location.addAnimal(newPython);
        }
    }
}
