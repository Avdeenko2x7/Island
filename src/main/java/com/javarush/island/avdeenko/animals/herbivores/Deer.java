package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;

public class Deer extends Animal{


    public Deer() {
        super(300, 20, 4, 50);
        this.maxFoodForSatiety = 50;
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        if (!plants.isEmpty()) {
            Iterator<Plant> iterator = plants.iterator();
            Plant plant = iterator.next();
            while (iterator.hasNext()) {
                if (isDead()) {
                    animals.remove(this);
                    location.removeAnimal(this);
                    break;
                } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    iterator.remove();
                    location.removePlant(plant);
                    increaseSatiety(25);
                } else if (this.currentFoodForSatiety == this.maxFoodForSatiety) {
                    break;
                }
            }
        }
    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Deer && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Deer newDeer = new Deer();
            location.addAnimal(newDeer);
        }
    }

}
