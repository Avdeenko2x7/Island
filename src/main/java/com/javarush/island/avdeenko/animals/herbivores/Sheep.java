package com.javarush.island.avdeenko.animals.herbivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;

import java.util.List;

public class Sheep extends Herbivore{
    public Sheep() {
        super(70, 140,	3,	15);
        this.icon = "\uD83D\uDC11";
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

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Sheep newSheep = new Sheep();
            location.addAnimal(newSheep);
        }
    }

}
