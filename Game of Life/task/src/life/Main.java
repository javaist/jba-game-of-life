package life;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
//    public static char[][] current;
//    public static char[][] next;
//    public static int border;
//    public static Random random;
//    static final char inhabited = 'O';
//    static final char empty = ' ';
//    static int population = 0;
//    static int generation = 0;


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Generation generation = new Generation(scanner.nextInt(), new Random());
        // world creation function
        generation.generate();
        generation.draw();
        Thread.sleep(1000);

        for (int i = 0; i < 10; i++) {
            generation.evolve();
            generation.draw();
            Thread.sleep(1000L);
            System.out.println("Generation: " + i);
        }
    }

}

class Generation {
    private char[][] current;
    private char[][] next;
    private final int border;
    private final Random seed;
    final char inhabited = 'O';
    final char empty = ' ';
    private int population = 0;
    private int generation = 0;
    private final GameOfLife field = new GameOfLife();

    public Generation(int border, Random seed) {
        this.border = border;
        this.seed = seed;

    }

    private int leap(int direction) {
        if (direction == border) {
            direction = 0;
        } else if (direction == -1) {
            direction = border - 1;
        }
        return direction;
    }


    public void evolve() {
        int north;
        int east;
        int south;
        int west;
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                int counter = 0;
                north = leap(i - 1);
                south = leap(i + 1);
                west = leap(j - 1);
                east = leap(j + 1);
                counter = current[north][j] == inhabited ? counter + 1 : counter;
                counter = current[north][west] == inhabited ? counter + 1 : counter;
                counter = current[north][east] == inhabited ? counter + 1 : counter;
                counter = current[i][west] == inhabited ? counter + 1 : counter;
                counter = current[i][east] == inhabited ? counter + 1 : counter;
                counter = current[south][j] == inhabited ? counter + 1 : counter;
                counter = current[south][west] == inhabited ? counter + 1 : counter;
                counter = current[south][east] == inhabited ? counter + 1 : counter;
                if (counter == 3) {
                    next[i][j] = inhabited;
                    population = current[i][j] == inhabited ? population : population + 1;
                } else if (counter < 2 || counter > 3) {
                    next[i][j] = empty;
                    population = current[i][j] == inhabited ? population - 1 : population;
                } else {
                    next[i][j] = current[i][j];
                }
            }
        }
//        population = 0;
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                current[i][j] = next[i][j];
//                population = current[i][j] == inhabited ? population + 1 : population;
            }
        }
        generation += 1;
    }

    public void generate() {
        current = new char[border][border];
        next = new char[border][border];
        boolean alive;

        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                alive = seed.nextBoolean();
                current[i][j] = alive ? inhabited : empty;
                population = alive ? population + 1 : population;
            }
        }
        generation += 1;
    }

    public void draw() {
        field.draw(generation, population, border, current);
    }

//    public void print() {
//        StringBuilder string = new StringBuilder();
//        string.append("Generation #");
//        string.append(generation);
//        string.append('\n');
//        string.append("Alive: ");
//        string.append(population);
//        string.append("\n\n");
//        for (int i = 0; i < border; i++) {
//            for (int j = 0; j < border; j++) {
//                string.append(current[i][j]);
////                System.out.print(current[i][j]);
//            }
////            System.out.println();
//            string.append('\n');
//        }
//        System.out.println(string);
}
