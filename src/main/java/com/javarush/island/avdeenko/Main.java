package com.javarush.island.avdeenko;

import com.javarush.island.avdeenko.island.Island;
import com.javarush.island.avdeenko.view.IslandGameGUI;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IslandGameGUI gameGUI = new IslandGameGUI();
            gameGUI.setVisible(true);
        });
    }
}
