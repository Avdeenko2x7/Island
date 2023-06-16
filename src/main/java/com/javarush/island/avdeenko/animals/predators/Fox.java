package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Fox extends Animal{
    public Fox() {
        super(8, 30, 2, 2);
        this.icon = " \uD83E\uDD8A";
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
                    case "Rabbit":
                        if (chanceToEat(70)) {
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
                    case "Duck":
                        if (chanceToEat(60)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Caterpillar":
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
            if (animal instanceof Fox && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Fox newFox = new Fox();
            location.addAnimal(newFox);
        }
    }

}
