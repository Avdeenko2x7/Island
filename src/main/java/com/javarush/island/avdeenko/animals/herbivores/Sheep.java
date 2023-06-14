package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sheep extends Animal{
    public Sheep() {
        super(70, 140,	3,	15);
        this.maxFoodForSatiety = 15;
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        List<Plant> copyOfPlants = new CopyOnWriteArrayList<>(plants);
        List<Animal> copyOfAnimals = new CopyOnWriteArrayList<>(animals);
        // Eat plants
        if (!plants.isEmpty()) {
            Iterator<Plant> plantIterator = copyOfPlants.iterator();
            Plant plant = plantIterator.next();
            while (plantIterator.hasNext()) {
                if (isDead()) {
                    copyOfAnimals.remove(this);
                    location.removeAnimal(this);
                    continue;
                }
                if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    plantIterator.remove();
                    location.removePlant(plant);
                    increaseSatiety(25);
                }
            }
        }
        animals.clear();
        animals.addAll(copyOfAnimals);
        plants.clear();
        plants.addAll(copyOfPlants);
    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Sheep && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Sheep newSheep = new Sheep();
            location.addAnimal(newSheep);
        }
    }

}
