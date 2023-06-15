package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Duck extends Animal{
    public Duck() {
        super(1,	200,	4,	0.15);
        this.maxFoodForSatiety = 0.15;
    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Duck && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Duck newDuck = new Duck();
            location.addAnimal(newDuck);
        }
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        if (isDead()) {
            location.removeAnimal(this);
        }else {
            for (Plant plant : plants) {
                if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    location.removePlant(plant);
                    increaseSatiety(25);
                }
            }
        }

        // Eat animals
        if (isDead()) {
            location.removeAnimal(this);
        } else {
            for (Animal animal : animals) {
                if (this.equals(animal)) continue;
                if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    if ("Caterpillar".equals(animal.getClass().getSimpleName())) {
                        if (chanceToEat(90)) {
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                    }
                }
            }
        }
    }
}
