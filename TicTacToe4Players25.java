import java.util.Scanner;

public class TicTacToe4Players25 {
    private static char[][] board = new char[5][5];
    private static char[] players = {'*', '0', 'A', 'B'};
    private static int currentPlayer = 0;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        boolean isGameOver = false;

        while (!isGameOver) {
            playerMove();
            printBoard();
            if (checkWinner(players[currentPlayer])) {
                System.out.println("Player " + (currentPlayer + 1) + " (" + players[currentPlayer] + ") wins!");
                isGameOver = true;
            } else if (isBoardFull()) {
                System.out.println("It's a tie!");
                isGameOver = true;
            } else {
                currentPlayer = (currentPlayer + 1) % 4; // Rotate between 4 players
            }
        }
    }

    // Initialize the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Get the current player's move
    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean validMove = false;
        do {
            System.out.println("Player " + (currentPlayer + 1) + " (" + players[currentPlayer] + ")'s turn.");
            System.out.print("Enter row (1-5): ");
            row = scanner.nextInt() - 1;
            System.out.print("Enter column (1-5): ");
            col = scanner.nextInt() - 1;
            if (row >= 0 && row < 5 && col >= 0 && col < 5 && board[row][col] == '-') {
                board[row][col] = players[currentPlayer];
                validMove = true;
            } else {
                System.out.println("Invalid move, try again.");
            }
        } while (!validMove);
    }

    // Check if the current player has won by getting 3 symbols in a row
    private static boolean checkWinner(char player) {
        // Check rows, columns, and diagonals for a winning condition (3 in a row)
        for (int i = 0; i < 5; i++) {
            if (checkRow(i, player) || checkColumn(i, player)) {
                return true;
            }
        }
        return checkDiagonals(player);
    }

    // Check if there are 3 consecutive symbols in a row for the player
    private static boolean checkRow(int row, char player) {
        for (int col = 0; col < 3; col++) { // check every set of 3 consecutive spots in a row
            if (board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == player) {
                return true;
            }
        }
        return false;
    }

    // Check if there are 3 consecutive symbols in a column for the player
    private static boolean checkColumn(int col, char player) {
        for (int row = 0; row < 3; row++) { // check every set of 3 consecutive spots in a column
            if (board[row][col] == player && board[row + 1][col] == player && board[row + 2][col] == player) {
                return true;
            }
        }
        return false;
    }

    // Check both diagonals for 3 consecutive symbols
    private static boolean checkDiagonals(char player) {
        // Check top-left to bottom-right diagonal
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player) {
                    return true;
                }
            }
        }

        // Check top-right to bottom-left diagonal
        for (int i = 0; i < 3; i++) {
            for (int j = 2; j < 5; j++) {
                if (board[i][j] == player && board[i + 1][j - 1] == player && board[i + 2][j - 2] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    // Check if the board is full
    private static boolean isBoardFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}