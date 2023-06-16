package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;

import java.util.List;

public class Buffalo extends Herbivore{
    public Buffalo() {
        super(700, 	10,	3,	100);
        this.icon = "\uD83D\uDC03";
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

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Buffalo newBuffalo = new Buffalo();
            location.addAnimal(newBuffalo);
        }
    }

}
