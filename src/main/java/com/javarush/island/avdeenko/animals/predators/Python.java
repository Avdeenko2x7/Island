package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Python extends Predator{
    public Python() {
        super(15, 30, 1, 3);
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        for (Animal animal : animals) {
            if (isDead()) {
                animals.remove(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Fox":
                        if (chanceToEat(15)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Rabbit":
                        if (chanceToEat(20)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Mouse":
                        if (chanceToEat(40)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(10)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                }
            } else if (this.currentFoodForSatiety == this.maxFoodForSatiety) {
                break;
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

        if (Count < maxNumInLocation) {
            Python newPython = new Python();
            location.addAnimal(newPython);
        }
    }
}
