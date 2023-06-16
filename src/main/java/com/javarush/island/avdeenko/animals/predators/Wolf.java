package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.*;

public class Wolf extends Animal{

    public Wolf() {
        super(50, 30, 3, 8);
        this.icon = "\uD83D\uDC3A";
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
            if (isDead()) {
                location.removeAnimal(this);
            }else{
                for(Animal animal : animals){
                    if(this.equals(animal)) continue;
                    if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0){
                        switch (animal.getClass().getSimpleName()) {
                            case "Horse":
                            case "Buffalo":
                                if (chanceToEat(10)) {
                                    location.removeAnimal(animal);
                                    increaseSatiety();
                                }
                                break;
                            case "Deer":
                            case "Boar":
                                if (chanceToEat(15)) {
                                    location.removeAnimal(animal);
                                    increaseSatiety();
                                }
                                break;
                            case "Rabbit":
                            case "Goat":
                                if (chanceToEat(60)) {
                                    location.removeAnimal(animal);
                                    increaseSatiety();
                                }
                                break;
                            case "Mouse":
                                if (chanceToEat(80)) {
                                    location.removeAnimal(animal);
                                    increaseSatiety();
                                }
                                break;
                            case "Sheep":
                                if (chanceToEat(70)) {
                                    location.removeAnimal(animal);
                                    increaseSatiety();
                                }
                                break;
                            case "Duck":
                                if (chanceToEat(40)) {
                                    location.removeAnimal(animal);
                                    increaseSatiety();
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
            if (animal instanceof Wolf && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Wolf newWolf = new Wolf();
            location.addAnimal(newWolf);
        }
    }





}
