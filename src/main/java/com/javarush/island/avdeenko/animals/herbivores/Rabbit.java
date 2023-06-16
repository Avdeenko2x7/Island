package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;

import java.util.List;

public class Rabbit extends Herbivore{
    public Rabbit() {
        super(2, 150, 2, 0.45);
        this.icon = "\uD83D\uDC07";
    }


    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Rabbit && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Rabbit newRabbit = new Rabbit();
            location.addAnimal(newRabbit);
        }
    }

}
