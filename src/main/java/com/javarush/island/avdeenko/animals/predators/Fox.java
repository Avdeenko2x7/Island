package com.javarush.island.avdeenko.animals.predators;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Fox extends Animal{
    public Fox() {
        super(8, 30, 2, 2);
        this.maxFoodForSatiety = 2;
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        List<Animal> copyOfAnimals = new CopyOnWriteArrayList<>(animals);
        Iterator<Animal> animalIterator = copyOfAnimals.iterator();
        while (animalIterator.hasNext()) {
            Animal animal = animalIterator.next();
            if (isDead()) {
                copyOfAnimals.remove(this);
                location.removeAnimal(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Rabbit":
                        if (chanceToEat(70)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Mouse":
                        if (chanceToEat(90)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Duck":
                        if (chanceToEat(60)) {
                            animalIterator.remove();
                            location.removeAnimal(animal);
                            increaseSatiety(25);
                        }
                        break;
                    case "Caterpillar":
                        if (chanceToEat(40)) {
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

        if (Count < maxNumInLocation) {
            Fox newFox = new Fox();
            location.addAnimal(newFox);
        }
    }

}
