package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Mouse extends Omnivore{
    public Mouse() {
        super(0.05,	500,	1,	0.01);
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

        if (Count < maxNumInLocation) {
            Mouse newMouse = new Mouse();
            location.addAnimal(newMouse);
        }
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        // Eat plants
        if (!plants.isEmpty()) {
            for (Plant plant : plants) {
                if (isDead()) {
                    animals.remove(this);
                    break;
                } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    plants.remove(plant);
                    increaseSatiety(25);
                } else if (this.currentFoodForSatiety == this.maxFoodForSatiety) {
                    break;
                }
            }
        }

        // Eat animals
        for (Animal animal : animals) {
            if (isDead()) {
                animals.remove(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                if ("Caterpillar".equals(animal.getClass().getSimpleName())) {
                    if (chanceToEat(90)) {
                        animals.remove(animal);
                        increaseSatiety(25);
                    }
                }
            }else if (this.currentFoodForSatiety == this.maxFoodForSatiety){
                break;
            }
        }
    }
}
