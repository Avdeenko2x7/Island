package com.javarush.island.avdeenko.island;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.herbivores.*;
import com.javarush.island.avdeenko.animals.omnivores.Boar;
import com.javarush.island.avdeenko.animals.omnivores.Duck;
import com.javarush.island.avdeenko.animals.omnivores.Mouse;
import com.javarush.island.avdeenko.animals.predators.*;
import com.javarush.island.avdeenko.plant.Flower;
import com.javarush.island.avdeenko.plant.Grass;
import com.javarush.island.avdeenko.plant.Plant;
import com.javarush.island.avdeenko.plant.Tree;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {
    private int islandWidth;
    private int islandHeight;
    private Location[][] locations;
    private ScheduledExecutorService scheduledExecutor;
    private ExecutorService executor;
    private Random random;
    private int maxAnimalsInLocation = 50;
    private int maxPlantsInLocation = 50;


    public Island(int islandWidth, int islandHeight) {
        this.islandWidth = islandWidth;
        this.islandHeight = islandHeight;
        this.locations = new Location[islandWidth][islandHeight];
        this.scheduledExecutor = Executors.newScheduledThreadPool(1);
        this.executor = Executors.newFixedThreadPool(10);
        this.random = new Random();
    }


    // Инициализация локаций на острове
    private void initializeIsland(){
        for (int x = 0; x < islandWidth; x++) {
            for (int y = 0; y < islandHeight; y++) {
                Location location = new Location(x, y, this);
                locations[x][y] = location;

                // Добавление животных и растений в локацию
                addInitialAnimals(location);
                addInitialPlants(location);
            }
        }
    }

    public void startGame(){
        startSimulation();
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

    // Метод для добавления начальных растений в локацию
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

    public void startSimulation() {
        initializeIsland();
        scheduledExecutor.scheduleAtFixedRate(this::growPlants, 0, 1, TimeUnit.SECONDS);
        scheduledExecutor.scheduleAtFixedRate(this::animalLifeCycle, 0, 1, TimeUnit.SECONDS);
        executor.execute(this::printStats); // Исправленный вызов execute
    }

    private void growPlants() {
        for (int x = 0; x < islandWidth; x++) {
            for (int y = 0; y < islandHeight; y++) {
                Location location = locations[x][y];
                List<Plant> plants = location.getPlants();

                for (Plant plant : plants) {
                    plant.grow(location);
                }
            }
        }
    }

    private void animalLifeCycle() {
            for (int x = 0; x < islandWidth; x++) {
                for (int y = 0; y < islandHeight; y++) {
                    Location location = locations[x][y];
                    List<Animal> animals = location.getAnimals();

                    for (Animal animal : animals) {
                        animal.move(location);
                        animal.reproduce(location);
                        animal.eat(location, animals, location.getPlants());

                        // Обновление текущей насыщенности животного
                       double currentFood = animal.getCurrentFoodForSatiety();
                        currentFood -= animal.getMaxFoodForSatiety() * 0.25; // Уменьшение на 25%
                        if (currentFood < 0) {
                            currentFood = 0;
                        }
                        animal.setCurrentFoodForSatiety(currentFood);
                    }
                }
            }
    }

    private void printStats() {
        System.out.println("Статистика острова:");

        while (!Thread.currentThread().isInterrupted()) {
            int totalAnimals = 0;
            int totalPlants = 0;
            int remainingAnimals = 0;
            int remainingPlants = 0;

            for (int x = 0; x < islandWidth; x++) {
                for (int y = 0; y < islandHeight; y++) {
                    Location location = locations[x][y];
                    totalAnimals += location.getAnimals().size();
                    totalPlants += location.getPlants().size();
                }
            }

            remainingAnimals = totalAnimals;
            remainingPlants = totalPlants;

            System.out.println("Общее количество животных: " + totalAnimals);
            System.out.println("Общее количество растений: " + totalPlants);
            System.out.println("Осталось животных на острове: " + remainingAnimals);
            System.out.println("Осталось растений на острове: " + remainingPlants);

            if (remainingAnimals == 0 && remainingPlants == 0) {
                break; // Exit the loop if there are no animals and plants
            }

            try {
                Thread.sleep(1000); // Wait for 1 second before updating the statistics
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
