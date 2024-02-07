public class Sudoku {
    /**
     * Checks if a number can be placed in a specific cell without violating the Sudoku rules.
     *
     * @param board   The Sudoku board
     * @param row     The row index of the cell
     * @param col     The column index of the cell
     * @param number  The number to be placed in the cell
     * @return        True if the number can be placed, false otherwise
     */
    public boolean isSafe(char[][] board, int row, int col, int number) {
        // Check row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false;
            }
            if (board[row][i] == (char) (number + '0')) {
                return false;
            }
        }

        // Check grid
        int startingRow = (row / 3) * 3;
        int startingCol = (col / 3) * 3;

        for (int i = startingRow; i < startingRow + 3; i++) {
            for (int j = startingCol; j < startingCol + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Recursive helper function that uses backtracking to solve the Sudoku puzzle.
     *
     * @param board  The Sudoku board
     * @param row    The row index of the cell
     * @param col    The column index of the cell
     * @return       True if the Sudoku puzzle is solved, false otherwise
     */
    public boolean helper(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }

        int newRow = row;
        int newCol = col + 1;
        if (newCol == board.length) {
            newRow++;
            newCol = 0;
        }

        if (board[row][col] != '.') {
            return helper(board, newRow, newCol);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');
                    if (helper(board, newRow, newCol)) {
                        return true;
                    } else {
                        board[row][col] = '.';
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Sudoku sudokuSolver = new Sudoku();

        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println("Original Sudoku:");
        printSudoku(board);

        sudokuSolver.helper(board, 0, 0);

        System.out.println("\nSolved Sudoku:");
        printSudoku(board);
    }

    /**
     * Prints the Sudoku board.
     *
     * @param board  The Sudoku board
     */
    public static void printSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}