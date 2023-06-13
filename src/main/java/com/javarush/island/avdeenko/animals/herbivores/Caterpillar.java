package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;

public class Caterpillar extends Animal{
    public Caterpillar() {
        super(0.01,	1000,	0,	0);
        this.maxFoodForSatiety = 0;
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
            if (animal instanceof Caterpillar && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Caterpillar newCaterpillar = new Caterpillar();
            location.addAnimal(newCaterpillar);
        }
    }

}
