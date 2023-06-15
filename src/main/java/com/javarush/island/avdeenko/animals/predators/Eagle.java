package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Eagle extends Animal{
    public Eagle() {
        super(6, 20, 3, 1);
        this.maxFoodForSatiety = 1;
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
                    case "Fox":
                        if (chanceToEat(10)) {
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Rabbit":
                    case "Mouse":
                        if (chanceToEat(90)) {
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(80)) {
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
            if (animal instanceof Eagle && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() < 0.9) {
            Eagle newEagle = new Eagle();
            location.addAnimal(newEagle);
        }
    }

}
