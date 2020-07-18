package tictactoe;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        IO.printMap(game.getMap());
        boolean turn = false;
        while (!game.isFinished()) {
            int[] coordinates = IO.readCoordinates(game);
            if (!turn) {
                game.updateState('X', coordinates);
            } else {
                game.updateState('O', coordinates);
            }
            IO.printMap(game.getMap());
            turn = !turn;
        }
        IO.printStatus(game.getStatus());
    }
}
