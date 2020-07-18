package tictactoe;

public class Game {
    public static final int SIZE = 3;

    private char[][] map;
    private GameStatus status;
    private int[][][] translation;

    public Game() {
        this.map = new char[Game.SIZE][Game.SIZE];
        for (int i = 0; i < Game.SIZE; ++i) {
            for (int j = 0; j < Game.SIZE; ++j) {
                this.map[i][j] = ' ';
            }
        }
        this.status = GameStatus.NOT_FINISHED;

        this.translation = new int[Game.SIZE][Game.SIZE][2];
        this.translation[0][0] = new int[]{1, 3};
        this.translation[0][1] = new int[]{2, 3};
        this.translation[0][2] = new int[]{3, 3};
        this.translation[1][0] = new int[]{1, 2};
        this.translation[1][1] = new int[]{2, 2};
        this.translation[1][2] = new int[]{3, 2};
        this.translation[2][0] = new int[]{1, 1};
        this.translation[2][1] = new int[]{2, 1};
        this.translation[2][2] = new int[]{3, 1};
    }

    public int[] translateCoordinates(int[] coordinates) {
        for (int i = 0; i < Game.SIZE; ++i) {
            for (int j = 0; j < Game.SIZE; ++j) {
                if (this.translation[i][j][0] == coordinates[0] && this.translation[i][j][1] == coordinates[1]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void updateState(char c, int[] coordinates) {
        try {
            int[] translatedCoordinates = this.translateCoordinates(coordinates);
            this.map[translatedCoordinates[0]][translatedCoordinates[1]] = c;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void updateState(String moves) {
        try {
            for (int i = 0; i < Game.SIZE; ++i) {
                for (int j = 0; j < Game.SIZE; ++j) {
                    this.map[i][j] = moves.charAt(i * Game.SIZE + j);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        int numO = 0;
        int numX = 0;
        for (int i = 0; i < Game.SIZE; ++i) {
            for (int j = 0; j < Game.SIZE; ++j) {
                if (this.map[i][j] == 'O') {
                    numO++;
                } else if (this.map[i][j] == 'X') {
                    numX++;
                }
            }
        }

        /* Check impossible state */
        if (Math.abs(numO - numX) > 1) {
            this.setStatus(GameStatus.IMPOSSIBLE);
            return true;
        }

        /* Check lines and columns */
        boolean oFinished = false;
        boolean xFinished = false;
        for (int i = 0; i < Game.SIZE; ++i) {
            if ((this.map[i][0] == this.map[i][1] && this.map[i][1] == this.map[i][2])) {
                if (this.map[i][0] == 'O') {
                    oFinished = true;
                } else if (this.map[i][0] == 'X') {
                    xFinished = true;
                }
            }

            if (this.map[0][i] == this.map[1][i] && this.map[1][i] == this.map[2][i]) {
                if (this.map[0][i] == 'O') {
                    oFinished = true;
                } else if (this.map[0][i] == 'X') {
                    xFinished = true;
                }
            }
        }

        if ((this.map[0][0] == this.map[1][1] && this.map[1][1] == this.map[2][2]) ||
                (this.map[0][2] == this.map[1][1] && this.map[1][1] == this.map[2][0])) {
            if (this.map[1][1] == 'O') {
                oFinished = true;
            } else if (this.map[1][1] == 'X') {
                xFinished = true;
            }
        }

        if (oFinished && xFinished) {
            this.setStatus(GameStatus.IMPOSSIBLE);
            return true;
        }
        if (oFinished) {
            this.setStatus(GameStatus.O_WINS);
            return true;
        }
        if (xFinished) {
            this.setStatus(GameStatus.X_WINS);
            return true;
        }

        /* Check if game is finished */
        if (numO + numX < 9) {
            this.setStatus(GameStatus.NOT_FINISHED);
            return false;
        } else {
            this.setStatus(GameStatus.DRAW);
            return true;
        }
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public int[][][] getTranslation() {
        return translation;
    }

    public void setTranslation(int[][][] translation) {
        this.translation = translation;
    }

}
