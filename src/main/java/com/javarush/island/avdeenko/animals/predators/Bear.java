package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;
import java.util.List;

public class Bear extends Animal{
    public Bear() {
        super(500, 5, 2, 80);
        this.icon = "\uD83D\uDC3B";
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
                    case "Python":
                    case "Deer":
                    case "Rabbit":
                        if (chanceToEat(80)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Horse":
                        if (chanceToEat(40)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Mouse":
                        if (chanceToEat(90)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Goat":
                    case "Sheep":
                        if (chanceToEat(70)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Boar":
                        if (chanceToEat(50)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Buffalo":
                        if (chanceToEat(20)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(10)) {
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
            if (animal instanceof Bear && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Bear newBear = new Bear();
            location.addAnimal(newBear);
        }
    }
}
