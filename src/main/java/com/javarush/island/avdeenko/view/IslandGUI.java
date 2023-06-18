package com.javarush.island.avdeenko.view;

import com.javarush.island.avdeenko.animals.Animal;
import com.javarush.island.avdeenko.island.Island;
import com.javarush.island.avdeenko.island.Location;
import com.javarush.island.avdeenko.plant.Plant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class IslandGUI extends JFrame {
    private static final int CELL_SIZE = 50;
    private static final Color ANIMAL_COLOR = Color.RED;
    private static final Color PLANT_COLOR = Color.GREEN;

    private Island island;
    private JPanel islandPanel;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField numAnimalsField;
    private JTextField numPlantsField;

    public IslandGUI() {
        initializeGUI();
    }

    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Island");
        setResizable(false);

        JPanel parametersPanel = new JPanel();
        parametersPanel.setLayout(new FlowLayout());

        JLabel widthLabel = new JLabel("Width:");
        widthField = new JTextField(5);
        parametersPanel.add(widthLabel);
        parametersPanel.add(widthField);

        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField(5);
        parametersPanel.add(heightLabel);
        parametersPanel.add(heightField);

        JLabel numAnimalsLabel = new JLabel("Number of Animals:");
        numAnimalsField = new JTextField(5);
        parametersPanel.add(numAnimalsLabel);
        parametersPanel.add(numAnimalsField);

        JLabel numPlantsLabel = new JLabel("Number of Plants:");
        numPlantsField = new JTextField(5);
        parametersPanel.add(numPlantsLabel);
        parametersPanel.add(numPlantsField);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int numAnimals = Integer.parseInt(numAnimalsField.getText());
                int numPlants = Integer.parseInt(numPlantsField.getText());

                island = new Island(width, height, numAnimals, numPlants);
                island.initializeIsland();
                island.startGame();

                initializeIslandPanel();
                startGameLoop();
            }
        });
        parametersPanel.add(startButton);

        add(parametersPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void initializeIslandPanel() {
        islandPanel = new JPanel();
        islandPanel.setLayout(new GridLayout(island.getIslandHeight(), island.getIslandWidth(), 1, 1));
        add(islandPanel, BorderLayout.CENTER);

        setSize(island.getIslandWidth() * CELL_SIZE, island.getIslandHeight() * CELL_SIZE);

        updateIslandPanel();
    }

    private void updateIslandPanel() {
        islandPanel.removeAll();

        for (int y = 0; y < island.getIslandHeight(); y++) {
            for (int x = 0; x < island.getIslandWidth(); x++) {
                Location location = island.getLocation(x, y);
                JLabel cellLabel = new JLabel();
                cellLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

                List<Animal> animals = location.getAnimals();
                List<Plant> plants = location.getPlants();

                if (!animals.isEmpty()) {
                    cellLabel.setText("\uD83D\uDC3E");
                } else if (!plants.isEmpty()) {
                    cellLabel.setText("\uD83C\uDF33");
                } else {
                    cellLabel.setText("\uD83C\uDFE1");
                }

                cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cellLabel.setOpaque(true);
                cellLabel.setBackground(Color.WHITE);

                if (!animals.isEmpty()) {
                    cellLabel.setForeground(ANIMAL_COLOR);
                } else if (!plants.isEmpty()) {
                    cellLabel.setForeground(PLANT_COLOR);
                }

                islandPanel.add(cellLabel);
            }
        }

        islandPanel.revalidate();
        islandPanel.repaint();
    }


    private void startGameLoop() {
        Thread gameLoop = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                island.animalLifeCycle();

                SwingUtilities.invokeLater(this::updateIslandPanel);
            }
        });

        gameLoop.setDaemon(true);
        gameLoop.start();
    }

}