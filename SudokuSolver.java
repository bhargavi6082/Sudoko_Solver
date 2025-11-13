import java.util.Scanner;

public class SudokuSolver {

    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");

        for (int i = 0; i < SIZE; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\nSolving Sudoku...\n");

        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }

        scanner.close();
    }

    // Solves the Sudoku using backtracking
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // puzzle solved
    }

    // Checks if placing num at board[row][col] is valid
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num)
                return false;
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num)
                return false;
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    // Print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int r = 0; r < SIZE; r++) {
            for (int d = 0; d < SIZE; d++) {
                System.out.print(board[r][d] + " ");
            }
            System.out.println();
        }
    }
}