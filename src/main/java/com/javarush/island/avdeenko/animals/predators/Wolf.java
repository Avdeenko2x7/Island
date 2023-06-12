package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.herbivores.*;
import com.javarush.island.avdeenko.animals.omnivores.Boar;
import com.javarush.island.avdeenko.animals.omnivores.Duck;
import com.javarush.island.avdeenko.animals.omnivores.Mouse;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.*;

public class Wolf extends Predator{

    public Wolf() {
        super(50, 30, 3, 8);
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        for (Animal animal : animals) {
            if (isDead()) {
                animals.remove(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Horse", "Buffalo":
                        if (chanceToEat(10)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Deer", "Boar":
                        if (chanceToEat(15)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Rabbit", "Goat":
                        if (chanceToEat(60)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Mouse":
                        if (chanceToEat(80)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Sheep":
                        if (chanceToEat(70)) {
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(40)) {
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
            if (animal instanceof Wolf && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Wolf newWolf = new Wolf();
            location.addAnimal(newWolf);
        }
    }



}
