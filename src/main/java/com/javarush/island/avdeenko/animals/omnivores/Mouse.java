package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Mouse extends Animal {
    public Mouse() {
        super(0.05, 500, 1, 0.01);
        this.icon = "\uD83D\uDC01";
    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Mouse && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Mouse newMouse = new Mouse();
            location.addAnimal(newMouse);
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
                    increaseSatiety();
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
                            increaseSatiety();
                        }
                    }
                }
            }
        }
    }
}
