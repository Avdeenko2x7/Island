package com.javarush.island.avdeenko;


import com.javarush.island.avdeenko.view.IslandGUI;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            IslandGUI islandGUI = new IslandGUI();
            islandGUI.setVisible(true);

        });
    }

}
