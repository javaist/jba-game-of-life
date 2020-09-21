package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    private int alive;
    private int generation;
    private final JPanel dataPanel = new JPanel();
    private final JPanel fieldPanel = new JPanel();
    private final JLabel generationLabel = new JLabel();
    private final JLabel populationLabel = new JLabel();


    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        setLayout(new BorderLayout());
        dataPanel.setLayout(new GridLayout(2, 1));
        fieldPanel.setLayout(new GridLayout());

        add(dataPanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);

        generationLabel.setName("GenerationLabel");
        populationLabel.setName("AliveLabel");
        dataPanel.add(generationLabel);

        generationLabel.setText("Generation #" + generation);
        populationLabel.setText("Alive: " + alive);
        dataPanel.add(populationLabel);

        setVisible(true);
    }

    public void draw(int generation, int alive, int border, char[][] current) {
        this.generation = generation;
        this.alive = alive;


        generationLabel.setText("Generation #" + generation);
        populationLabel.setText("Alive: " + alive);
        dataPanel.updateUI();

        fieldPanel.setLayout(new GridLayout(border, border, 2, 2));

        if (fieldPanel.getComponents().length > 0) {
            for (int i = 0; i < border; i++) {
                for (int j = 0; j < border; j++) {
                    Component square = fieldPanel.getComponent(j + border * i);
                    System.out.print(current[i][j]);
                    System.out.print(" ");
                    square.setBackground(
                            current[i][j] == 'O' ? Color.BLACK : Color.WHITE
                    );
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i < border; i++) {
                for (int j = 0; j < border; j++) {
                    JPanel square = new JPanel();
                    System.out.print(j + border * i);
                    System.out.print(" ");
                    square.setBackground(
                            current[i][j] == 'O' ? Color.BLACK : Color.WHITE
                    );

                    fieldPanel.add(square);
                }
            }
        }
        System.out.println();
        fieldPanel.updateUI();


    }


}
