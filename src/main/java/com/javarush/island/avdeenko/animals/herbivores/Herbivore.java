package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxNumInLocation, int speed, double currentFoodForSatiety) {
        super(weight, maxNumInLocation, speed, currentFoodForSatiety);
    }

    public Herbivore() {
    }

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
    }

}
