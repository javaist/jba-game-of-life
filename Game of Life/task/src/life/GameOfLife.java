package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    public static final int EVOLVE = 1;
    public static final int PAUSE = 2;
    public static final int RESET = 3;
    private int alive;
    private int generation;
    private final JPanel dataPanel = new JPanel();
    private final JPanel fieldPanel = new JPanel();
    private final JLabel generationLabel = new JLabel();
    private final JLabel populationLabel = new JLabel();
    private int status = EVOLVE;


    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        setLayout(new BorderLayout());
        dataPanel.setLayout(new GridLayout(4, 1));

        fieldPanel.setLayout(new GridLayout());

        add(dataPanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);

        generationLabel.setName("GenerationLabel");
        populationLabel.setName("AliveLabel");
        dataPanel.add(generationLabel);

        generationLabel.setText("Generation #" + generation);
        populationLabel.setText("Alive: " + alive);
        dataPanel.add(populationLabel);

        JToggleButton playToggleButton = new JToggleButton("START / PAUSE");
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.addActionListener(actionEvent -> status = status == PAUSE ? EVOLVE : PAUSE);
        dataPanel.add(playToggleButton);

        JButton resetButton = new JButton("RESET");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(actionEvent -> status = RESET);
        dataPanel.add(resetButton);

        setVisible(true);
    }

    public void draw(int generation, int alive, int border, char[][] current) {
        this.generation = generation;
        this.alive = alive;


        generationLabel.setText("Generation #" + generation);
        populationLabel.setText("Alive: " + alive);
        dataPanel.updateUI();

        fieldPanel.setLayout(new GridLayout(border, border, 1, 1));

        if (fieldPanel.getComponents().length > 0) {
            for (int i = 0; i < border; i++) {
                for (int j = 0; j < border; j++) {
                    Component square = fieldPanel.getComponent(j + border * i);
                    square.setBackground(
                            current[i][j] == 'O' ? Color.BLACK : Color.WHITE
                    );
                }
            }
        } else {
            for (int i = 0; i < border; i++) {
                for (int j = 0; j < border; j++) {
                    JPanel square = new JPanel();
                    square.setBackground(
                            current[i][j] == 'O' ? Color.BLACK : Color.WHITE
                    );

                    fieldPanel.add(square);
                }
            }
        }
        fieldPanel.updateUI();


    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
