package life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] current;
    public static char[][] next;
    public static int border;
    public static Random random;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        border = scanner.nextInt();
        long seed = scanner.nextLong();
        int eons = scanner.nextInt();

        random = new Random(seed);
        // world creation function
        generate();

        for (int i = 0; i < eons; i++) {
            evolve();
        }

        print();
    }

    public static int leap(int direction) {
        if (direction == border) {
            direction = 0;
        } else if (direction == -1) {
            direction = border - 1;
        }
        return direction;
    }


    public static void evolve() {
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
                counter = current[north][j] == 'O' ? counter + 1 : counter;
                counter = current[north][west] == 'O' ? counter + 1 : counter;
                counter = current[north][east] == 'O' ? counter + 1 : counter;
                counter = current[i][west] == 'O' ? counter + 1 : counter;
                counter = current[i][east] == 'O' ? counter + 1 : counter;
                counter = current[south][j] == 'O' ? counter + 1 : counter;
                counter = current[south][west] == 'O' ? counter + 1 : counter;
                counter = current[south][east] == 'O' ? counter + 1 : counter;
                if (counter == 3) {
                    next[i][j] = 'O';
                } else if (counter < 2 || counter > 3) {
                    next[i][j] = ' ';
                } else {
                    next[i][j] = current[i][j];
                }
            }
        }
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                current[i][j] = next[i][j];
            }
        }
    }

    public static void generate() {
        current = new char[border][border];
        next = new char[border][border];
        boolean alive;

        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                alive = random.nextBoolean();
                current[i][j] = alive ? 'O' : ' ';
            }
        }
    }

    public static void print() {
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                System.out.print(current[i][j]);
            }
            System.out.println();
        }
    }
}
