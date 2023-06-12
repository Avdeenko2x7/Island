package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Eagle extends Predator{
    public Eagle() {
        super(6, 20, 3, 1);
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
                        if (chanceToEat(10)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Rabbit":
                    case "Mouse":
                        if (chanceToEat(90)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(80)) {
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
            if (animal instanceof Eagle && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Eagle newEagle = new Eagle();
            location.addAnimal(newEagle);
        }
    }

}
