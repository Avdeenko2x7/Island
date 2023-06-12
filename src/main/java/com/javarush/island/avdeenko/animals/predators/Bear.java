package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Bear extends Predator{
    public Bear() {
        super(500, 5, 2, 80);
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        for (Animal animal : animals) {
            if (isDead()) {
                animals.remove(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Python":
                    case "Deer":
                    case "Rabbit":
                        if (chanceToEat(80)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Horse":
                        if (chanceToEat(40)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Mouse":
                        if (chanceToEat(90)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Goat":
                    case "Sheep":
                        if (chanceToEat(70)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Boar":
                        if (chanceToEat(50)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Buffalo":
                        if (chanceToEat(20)) {
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
            if (animal instanceof Bear && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Bear newBear = new Bear();
            location.addAnimal(newBear);
        }
    }
}
