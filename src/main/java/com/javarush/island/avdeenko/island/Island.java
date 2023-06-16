package com.javarush.island.avdeenko.island;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.herbivores.*;
import com.javarush.island.avdeenko.animals.omnivores.Boar;
import com.javarush.island.avdeenko.animals.omnivores.Duck;
import com.javarush.island.avdeenko.animals.omnivores.Mouse;
import com.javarush.island.avdeenko.animals.predators.*;
import com.javarush.island.avdeenko.constants.Constants;
import com.javarush.island.avdeenko.plant.Flower;
import com.javarush.island.avdeenko.plant.Grass;
import com.javarush.island.avdeenko.plant.Plant;
import com.javarush.island.avdeenko.plant.Tree;

import java.util.*;
import java.util.concurrent.*;

public class Island {
    private int islandWidth;
    private int islandHeight;
    private Location[][] locations;
    private ScheduledExecutorService scheduledExecutor;
    private ExecutorService executor;
    private Random random;
    private int maxAnimalsInLocation;
    private int maxPlantsInLocation;


    public Island(int islandWidth, int islandHeight, int maxAnimalsInLocation, int maxPlantsInLocation) {
        this.islandWidth = islandWidth;
        this.islandHeight = islandHeight;
        this.maxAnimalsInLocation = maxAnimalsInLocation;
        this.maxPlantsInLocation = maxPlantsInLocation;
        this.locations = new Location[islandWidth][islandHeight];
        this.scheduledExecutor = Executors.newScheduledThreadPool(1);
        this.executor = Executors.newFixedThreadPool(10);
        this.random = new Random();
    }



    public void initializeIsland(){
        for (int x = 0; x < islandWidth; x++) {
            for (int y = 0; y < islandHeight; y++) {
                Location location = new Location(x, y, this);
                locations[x][y] = location;


                addInitialAnimals(location);
                addInitialPlants(location);
            }
        }
    }

    private void addInitialAnimals(Location location) {
        for (int i = 0; i < maxAnimalsInLocation; i++) {
            Animal animal = null;
            int animalType = random.nextInt(15);

            switch (animalType) {
                case 0 -> animal = new Wolf();
                case 1 -> animal = new Python();
                case 2 -> animal = new Fox();
                case 3 -> animal = new Eagle();
                case 4 -> animal = new Bear();
                case 5 -> animal = new Boar();
                case 6 -> animal = new Duck();
                case 7 -> animal = new Mouse();
                case 8 -> animal = new Buffalo();
                case 9 -> animal = new Caterpillar();
                case 10 -> animal = new Deer();
                case 11 -> animal = new Goat();
                case 12 -> animal = new Horse();
                case 13 -> animal = new Rabbit();
                case 14 -> animal = new Sheep();
                default -> {
                }
            }

            location.addAnimal(animal);
        }
    }


    private void addInitialPlants(Location location) {
        for (int i = 0; i < maxPlantsInLocation; i++) {
            Plant plant = null;
            int plantType = random.nextInt(3);

            switch (plantType) {
                case 0 -> plant = new Grass();
                case 1 -> plant = new Tree();
                case 2 -> plant = new Flower();
                default -> {
                }
            }

            location.addPlant(plant);
        }
    }

    public void startGame() {
        initializeIsland();
        scheduledExecutor.scheduleAtFixedRate(this::animalLifeCycle, 0, 1, TimeUnit.SECONDS);
        executor.submit(this::printStats);
    }


    public void animalLifeCycle() {
        try {
            for (int x = 0; x < islandWidth; x++) {
                for (int y = 0; y < islandHeight; y++) {
                    Location location = locations[x][y];
                    List<Animal> animals = location.getAnimals();

                    for (Animal animal : animals) {
                        animal.move(location);
                        animal.reproduce(location);
                        animal.eat(location, animals, location.getPlants());

                        double currentFood = animal.getCurrentFoodForSatiety();
                        currentFood -= animal.getMaxFoodForSatiety() * Constants.REDUCE_Satiety;
                        if (currentFood < 0) {
                            currentFood = 0;
                        }
                        animal.setCurrentFoodForSatiety(currentFood);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int countAnimalsByType(String animalType) {
        int count = 0;

        for (int x = 0; x < islandWidth; x++) {
            for (int y = 0; y < islandHeight; y++) {
                Location location = locations[x][y];
                List<Animal> animals = location.getAnimals();

                for (Animal animal : animals) {
                    if (animal.getClass().getSimpleName().equals(animalType)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int countPlantsByType(String plantType) {
        int count = 0;

        for (int x = 0; x < islandWidth; x++) {
            for (int y = 0; y < islandHeight; y++) {
                Location location = locations[x][y];
                List<Plant> plants = location.getPlants();

                for (Plant plant : plants) {
                    if (plant.getClass().getSimpleName().equals(plantType)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public synchronized void printStats() {
        System.out.println("Статистика острова:");

        while (!Thread.currentThread().isInterrupted()) {
            int totalAnimals = 0;
            int totalPlants = 0;

            for (int x = 0; x < islandWidth; x++) {
                for (int y = 0; y < islandHeight; y++) {
                    Location location = locations[x][y];
                    List<Animal> animals = location.getAnimals();
                    List<Plant> plants = location.getPlants();

                    totalAnimals += animals.size();
                    totalPlants += plants.size();


                }
            }


            System.out.print(new Wolf().getIcon() + "= " + countAnimalsByType("Wolf") + " ");
            System.out.print(new Python().getIcon() + "= " + countAnimalsByType("Python") + " ");
            System.out.print(new Fox().getIcon() + "= " + countAnimalsByType("Fox") + " ");
            System.out.print(new Eagle().getIcon() + "= " + countAnimalsByType("Eagle") + " ");
            System.out.print(new Bear().getIcon() + "= " + countAnimalsByType("Bear") + " ");
            System.out.print(new Boar().getIcon() + "= " + countAnimalsByType("Boar") + " ");
            System.out.print(new Duck().getIcon() + "= " + countAnimalsByType("Duck") + " ");
            System.out.print(new Mouse().getIcon() + "= " + countAnimalsByType("Mouse") + " ");
            System.out.print(new Sheep().getIcon() + "= " + countAnimalsByType("Sheep") + " ");
            System.out.print(new Rabbit().getIcon() + "= " + countAnimalsByType("Rabbit") + " ");
            System.out.print(new Horse().getIcon() + "= " + countAnimalsByType("Horse") + " ");
            System.out.print(new Goat().getIcon() + "= " + countAnimalsByType("Goat") + " ");
            System.out.print(new Deer().getIcon() + "= " + countAnimalsByType("Deer") + " ");
            System.out.print(new Caterpillar().getIcon() + "= " + countAnimalsByType("Caterpillar") + " ");
            System.out.println(new Buffalo().getIcon() + "= " + countAnimalsByType("Buffalo") + " ");

            System.out.print(new Tree().getIcon() + "= " + countPlantsByType("Tree") + " ");
            System.out.print(new Grass().getIcon() + "= " + countPlantsByType("Grass") + " ");
            System.out.print(new Flower().getIcon() + "= " + countPlantsByType("Flower") + " ");
            System.out.println();


            System.out.println("Общее количество животных: " + totalAnimals);
            System.out.println("Общее количество растений: " + totalPlants);

            if (totalAnimals == 0) {
                Thread.currentThread().interrupt();
                stopSimulation();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stopSimulation() {
        scheduledExecutor.shutdown();
        executor.shutdownNow();
    }



    public int getIslandWidth() {
        return islandWidth;
    }

    public int getIslandHeight() {
        return islandHeight;
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < islandWidth && y >= 0 && y < islandHeight) {
            return locations[x][y];
        }
        return null;
    }

}
