package com.javarush.island.avdeenko;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.animals.herbivores.*;
import com.javarush.island.avdeenko.animals.omnivores.Duck;
import com.javarush.island.avdeenko.animals.omnivores.Mouse;
import com.javarush.island.avdeenko.animals.predators.Bear;
import com.javarush.island.avdeenko.animals.predators.Eagle;
import com.javarush.island.avdeenko.animals.predators.Predator;
import com.javarush.island.avdeenko.animals.predators.Wolf;
import com.javarush.island.avdeenko.island.Island;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;
import com.javarush.island.avdeenko.plant.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Island island = new Island(100, 20);
        island.startGame();
    }


}
