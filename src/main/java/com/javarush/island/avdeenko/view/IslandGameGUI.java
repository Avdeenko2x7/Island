package com.javarush.island.avdeenko.view;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Island;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IslandGameGUI extends JFrame {
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JTextField maxAnimalsTextField;
    private JTextField maxPlantsTextField;

    public IslandGameGUI() {
        setTitle("Island Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel widthLabel = new JLabel("Island Width:");
        widthTextField = new JTextField();
        JLabel heightLabel = new JLabel("Island Height:");
        heightTextField = new JTextField();
        JLabel maxAnimalsLabel = new JLabel("Max Animals per Location:");
        maxAnimalsTextField = new JTextField();
        JLabel maxPlantsLabel = new JLabel("Max Plants per Location:");
        maxPlantsTextField = new JTextField();

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = Integer.parseInt(widthTextField.getText());
                int height = Integer.parseInt(heightTextField.getText());
                int maxAnimals = Integer.parseInt(maxAnimalsTextField.getText());
                int maxPlants = Integer.parseInt(maxPlantsTextField.getText());

                Island island = new Island(width, height, maxAnimals, maxPlants);
                island.startGame();
            }
        });

        mainPanel.add(widthLabel);
        mainPanel.add(widthTextField);
        mainPanel.add(heightLabel);
        mainPanel.add(heightTextField);
        mainPanel.add(maxAnimalsLabel);
        mainPanel.add(maxAnimalsTextField);
        mainPanel.add(maxPlantsLabel);
        mainPanel.add(maxPlantsTextField);
        mainPanel.add(new JLabel());
        mainPanel.add(startButton);

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
}
