package com.javarush.island.avdeenko.animals.omnivores;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.herbivores.Buffalo;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import java.util.List;

public class Boar extends Omnivore{
    public Boar() {
        super(400,	50,	2,	50);
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

        if (Count < maxNumInLocation) {
            Boar newBoar = new Boar();
            location.addAnimal(newBoar);
        }
    }

    @Override
    public void eat(Location location, List<Animal> animals, List<Plant> plants) {
        // Eat plants
        if (!plants.isEmpty()) {
            for (Plant plant : plants) {
                if (isDead()) {
                    animals.remove(this);
                    break;
                } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                    plants.remove(plant);
                    increaseSatiety(25);
                } else if (this.currentFoodForSatiety == this.maxFoodForSatiety) {
                    break;
                }
            }
        }

        // Eat animals
        for (Animal animal : animals) {
            if (isDead()) {
                animals.remove(this);
                break;
            } else if (this.currentFoodForSatiety < this.maxFoodForSatiety && this.currentFoodForSatiety > 0) {
                switch (animal.getClass().getSimpleName()){
                    case "Mouse" -> {
                        if(chanceToEat(50)){
                            animals.remove(animal);
                            increaseSatiety(25);
                        }}
                    case "Caterpillar" ->{
                        if(chanceToEat(90)){
                            animals.remove(animal);
                            increaseSatiety(25);
                        }
                    }
                    }
                }else if (this.currentFoodForSatiety == this.maxFoodForSatiety){
                break;
            }
        }
    }
}
