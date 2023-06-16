package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Caterpillar extends Herbivore{
    public Caterpillar() {
        super(0.01,	1000,	0,	0.01);
        this.icon = "\uD83D\uDC1B";
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

        if (Count < maxNumInLocation && Math.random() < 0.9) {
            Caterpillar newCaterpillar = new Caterpillar();
            location.addAnimal(newCaterpillar);
        }
    }

}
