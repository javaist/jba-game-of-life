package life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] field;
    public static int border;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        border = scanner.nextInt();
        long seed = scanner.nextLong();
        boolean alive;
        Random random = new Random(seed);
        field = new char[border][border];

        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                alive = random.nextBoolean();
                field[i][j] = alive ? 'O' : ' ';
            }
        }
        print();
    }

    public static void print() {
        for (int i = 0; i < border; i++) {
            for (int j = 0; j < border; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
