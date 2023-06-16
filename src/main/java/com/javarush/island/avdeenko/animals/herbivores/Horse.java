package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Horse extends Herbivore{
    public Horse() {
        super(400, 20, 4, 60);
        this.icon = "\uD83D\uDC0E";
    }

    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Horse && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() < 0.9) {
            Horse newHorse = new Horse();
            location.addAnimal(newHorse);
        }
    }

}
