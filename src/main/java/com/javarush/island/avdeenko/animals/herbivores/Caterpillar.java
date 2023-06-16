package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;

import java.util.List;

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

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Caterpillar newCaterpillar = new Caterpillar();
            location.addAnimal(newCaterpillar);
        }
    }

}
