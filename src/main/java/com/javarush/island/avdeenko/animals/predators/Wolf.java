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
        this.maxFoodForSatiety = 8;
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        Iterator<Animal> animalIterator = animals.iterator();
        while (animalIterator.hasNext()) {
            Animal animal = animalIterator.next();
            if (isDead()) {
                animals.remove(this);
                location.removeAnimal(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Horse":
                    case "Buffalo":
                        if (chanceToEat(10)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Deer":
                    case "Boar":
                        if (chanceToEat(15)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Rabbit":
                    case "Goat":
                        if (chanceToEat(60)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Mouse":
                        if (chanceToEat(80)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Sheep":
                        if (chanceToEat(70)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(40)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
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
