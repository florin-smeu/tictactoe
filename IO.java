package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IO {
    public static final String NOT_FINISHED = "Game not finished";
    public static final String DRAW = "Draw";
    public static final String X_WINS = "X wins";
    public static final String O_WINS = "O wins";
    public static final String IMPOSSIBLE = "Impossible";

    private static final Scanner sc = new Scanner(System.in);

    public static int[] readCoordinates(Game game) {


        int[] coordinates = new int[2];
        boolean validCoordinates = false;

        while (!validCoordinates) {
            System.out.println("Enter the coordinates: ");
            boolean isInteger = true;
            for (int i = 0; i < 2; ++i) {
                if (IO.sc.hasNextInt()) {
                    coordinates[i] = IO.sc.nextInt();
                } else {
                    isInteger = false;
                    break;
                }
            }
            if (!isInteger) {
                System.out.println("You should enter numbers!");
                continue;
            }

            boolean inRange = true;
            for (int i = 0; i < 2; ++i) {
                if (coordinates[i] < 1 || coordinates[i] > 3) {
                    inRange = false;
                    break;
                }
            }
            if (!inRange) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            char[][] map = game.getMap();
            int[] translatedCoordinates = game.translateCoordinates(coordinates);
            if (map[translatedCoordinates[0]][translatedCoordinates[1]] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            validCoordinates = true;
        }
        return coordinates;
    }

    public static void printMap(char[][] map) {
        String hBorder = "---------";
        System.out.println(hBorder);
        for (int i = 0; i < Game.SIZE; ++i) {
            System.out.print("| ");
            for (int j = 0; j < Game.SIZE; ++j) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println(hBorder);
    }

    public static void printStatus(GameStatus gameStatus) {
        switch (gameStatus) {
            case NOT_FINISHED:
                System.out.println(IO.NOT_FINISHED);
                break;
            case DRAW:
                System.out.println(IO.DRAW);
                break;
            case X_WINS:
                System.out.println(IO.X_WINS);
                break;
            case O_WINS:
                System.out.println(IO.O_WINS);
                break;
            case IMPOSSIBLE:
                System.out.println(IO.IMPOSSIBLE);
        }
    }
}
