package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.herbivores.Buffalo;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Boar extends Animal{
    public Boar() {
        super(400,	50,	2,	50);
        this.maxFoodForSatiety = 50;
    }

    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Boar && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Boar newBoar = new Boar();
            location.addAnimal(newBoar);
        }
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {

        List<Animal> copyOfAnimals = new CopyOnWriteArrayList<>(animals);
        List<Plant> copyOfPlants = new CopyOnWriteArrayList<>(plants);
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

        // Eat animals
        Iterator<Animal> animalIterator = copyOfAnimals.iterator();
        while (animalIterator.hasNext()) {
            Animal animal = animalIterator.next();
            if (isDead()) {
                copyOfAnimals.remove(this);
                location.removeAnimal(this);
                continue;
            }
            if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Mouse":
                        if (chanceToEat(50)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Caterpillar":
                        if (chanceToEat(90)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                }
            }
        }
        animals.clear();
        animals.addAll(copyOfAnimals);
        plants.clear();
        plants.addAll(copyOfPlants);
    }
}
