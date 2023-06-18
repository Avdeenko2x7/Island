package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Boar extends Animal{
    public Boar() {
        super(400,	50,	2,	50);
        this.icon = "\uD83D\uDC17";
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

        if (Count < maxNumInLocation && Math.random() > Constants.CHANCE_TO_REPRODUCE) {
            Boar newBoar = new Boar();
            location.addAnimal(newBoar);
        }
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        // Eat plants
        if (isDead()) {
            location.removeAnimal(this);
        }else {
            for (Plant plant : plants) {
                if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    location.removePlant(plant);
                    increaseSatiety();
                }
            }
        }

        // Eat animals
        if (isDead()) {
            location.removeAnimal(this);
        } else {
            for (Animal animal : animals) {
                if (this.equals(animal)) continue;
                if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()) {
                    case "Mouse":
                        if (chanceToEat(50)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                    case "Caterpillar":
                        if (chanceToEat(90)) {
                            location.removeAnimal(animal);
                            increaseSatiety();
                        }
                        break;
                }
            }
        }
    }
}
}
