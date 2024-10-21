import java.util.Scanner;
import java.util.Arrays;

public class Game2048 {
    private static final int SIZE = 4;
    private int[][] board;
    private boolean moved;

    public Game2048() {
        board = new int[SIZE][SIZE];
        addNewTile();
        addNewTile();
    }

    private void addNewTile() {
        int row, col;
        do {
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);
        } while (board[row][col] != 0);
        board[row][col] = Math.random() < 0.9 ? 2 : 4;
    }

    public void moveLeft() {
        moved = false;
        for (int i = 0; i < SIZE; i++) {
            int[] newRow = new int[SIZE];
            int pos = 0;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    newRow[pos++] = board[i][j];
                }
            }
            for (int j = 0; j < SIZE - 1; j++) {
                if (newRow[j] != 0 && newRow[j] == newRow[j + 1]) {
                    newRow[j] *= 2;
                    newRow[j + 1] = 0;
                    moved = true;
                }
            }
            int[] finalRow = new int[SIZE];
            pos = 0;
            for (int j = 0; j < SIZE; j++) {
                if (newRow[j] != 0) {
                    finalRow[pos++] = newRow[j];
                }
            }
            if (!Arrays.equals(board[i], finalRow)) {
                moved = true;
            }
            board[i] = finalRow;
        }
        if (moved) {
            addNewTile();
        }
    }

    public void moveRight() {
        moved = false;
        for (int i = 0; i < SIZE; i++) {
            int[] newRow = new int[SIZE];
            int pos = SIZE - 1;
            for (int j = SIZE - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    newRow[pos--] = board[i][j];
                }
            }
            for (int j = SIZE - 1; j > 0; j--) {
                if (newRow[j] != 0 && newRow[j] == newRow[j - 1]) {
                    newRow[j] *= 2;
                    newRow[j - 1] = 0;
                    moved = true;
                }
            }
            int[] finalRow = new int[SIZE];
            pos = SIZE - 1;
            for (int j = SIZE - 1; j >= 0; j--) {
                if (newRow[j] != 0) {
                    finalRow[pos--] = newRow[j];
                }
            }
            if (!Arrays.equals(board[i], finalRow)) {
                moved = true;
            }
            board[i] = finalRow;
        }
        if (moved) {
            addNewTile();
        }
    }

    public void moveUp() {
        moved = false;
        for (int j = 0; j < SIZE; j++) {
            int[] newCol = new int[SIZE];
            int pos = 0;
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != 0) {
                    newCol[pos++] = board[i][j];
                }
            }
            for (int i = 0; i < SIZE - 1; i++) {
                if (newCol[i] != 0 && newCol[i] == newCol[i + 1]) {
                    newCol[i] *= 2;
                    newCol[i + 1] = 0;
                    moved = true;
                }
            }
            int[] finalCol = new int[SIZE];
            pos = 0;
            for (int i = 0; i < SIZE; i++) {
                if (newCol[i] != 0) {
                    finalCol[pos++] = newCol[i];
                }
            }
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != finalCol[i]) {
                    moved = true;
                }
                board[i][j] = finalCol[i];
            }
        }
        if (moved) {
            addNewTile();
        }
    }

    public void moveDown() {
        moved = false;
        for (int j = 0; j < SIZE; j++) {
            int[] newCol = new int[SIZE];
            int pos = SIZE - 1;
            for (int i = SIZE - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    newCol[pos--] = board[i][j];
                }
            }
            for (int i = SIZE - 1; i > 0; i--) {
                if (newCol[i] != 0 && newCol[i] == newCol[i - 1]) {
                    newCol[i] *= 2;
                    newCol[i - 1] = 0;
                    moved = true;
                }
            }
            int[] finalCol = new int[SIZE];
            pos = SIZE - 1;
            for (int i = SIZE - 1; i >= 0; i--) {
                if (newCol[i] != 0) {
                    finalCol[pos--] = newCol[i];
                }
            }
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != finalCol[i]) {
                    moved = true;
                }
                board[i][j] = finalCol[i];
            }
        }
        if (moved) {
            addNewTile();
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) return false;
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) return false;
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) return false;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + "\t| ");
            }
            System.out.println();
            if (i < SIZE - 1) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print("--------");
                }
                System.out.println();
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Game2048 game = new Game2048();
        game.printBoard();
        Scanner scanner = new Scanner(System.in);
        while (!game.isGameOver()) {
            System.out.println("Enter a move (ZQSD): ");
            char move = scanner.next().charAt(0);
            switch (move) {
                case 'z':
                    game.moveUp();
                    break;
                case 'q':
                    game.moveLeft();
                    break;
                case 's':
                    game.moveDown();
                    break;
                case 'd':
                    game.moveRight();
                    break;
                default:
                    System.out.println("Invalid move. Please enter Z, Q, S, or D.");
                    continue;
            }
            game.printBoard();
        }
    }
}