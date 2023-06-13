package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.Iterator;
import java.util.List;

public class Mouse extends Animal{
    public Mouse() {
        super(0.05,	500,	1,	0.01);
        this.maxFoodForSatiety = 0.01;
    }



    @Override
    public void reproduce(Location location) {
        List<Animal> animals = location.getAnimals();
        int Count = 0;
        for (Animal animal : animals) {
            if (animal instanceof Mouse && animal != this) {
                Count++;
            }
        }

        if (Count < maxNumInLocation) {
            Mouse newMouse = new Mouse();
            location.addAnimal(newMouse);
        }
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        // Eat plants
        if (!plants.isEmpty()) {
            Iterator<Plant> plantIterator = plants.iterator();
            Plant plant = plantIterator.next();
            while (plantIterator.hasNext()) {
                if (isDead()) {
                    animals.remove(this);
                    location.removeAnimal(this);
                    break;
                } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    plantIterator.remove();
                    location.removePlant(plant);
                    increaseSatiety(25);
                } else if (this.currentFoodForSatiety == this.maxFoodForSatiety) {
                    break;
                }
            }
        }

        // Eat animals
        Iterator<Animal> animalIterator = animals.iterator();
        while (animalIterator.hasNext()) {
            Animal animal = animalIterator.next();
            if (isDead()) {
                animals.remove(this);
                location.removeAnimal(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                if ("Caterpillar".equals(animal.getClass().getSimpleName())) {
                    if (chanceToEat(90)) {
                        animalIterator.remove();
                        location.removeAnimal(animal);
                        increaseSatiety(25);
                    }
                }
            } else if (this.currentFoodForSatiety == this.maxFoodForSatiety) {
                break;
            }
        }
    }
}
