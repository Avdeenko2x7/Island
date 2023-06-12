package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.predators.Wolf;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Buffalo extends Herbivore{
    public Buffalo() {
        super(700, 	10,	3,	100);
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {

    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Buffalo && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Buffalo newBuffalo = new Buffalo();
            location.addAnimal(newBuffalo);
        }
    }

}
