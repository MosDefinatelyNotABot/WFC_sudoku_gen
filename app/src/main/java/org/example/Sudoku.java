package org.example;

public class Sudoku {
    // java class that holds information for one sudoku puzzle.
    private int[][] puzzle;

    public Sudoku() {
        // constructor
        this.puzzle = new int[9][9];
        this.clear();
    }

    public void clear() {
        // called as part of the constructor.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.setNum(row, col, 0);
            }
        }
    }

    public String toString() {
        // does a pretty print of the puzzle to terminal.
        String line0 = "╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗\n";
        String line1 = "╟───┼───┼───╫───┼───┼───╫───┼───┼───╢\n";
        String line2 = "╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣\n";
        String line3 = "╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝";

        StringBuilder output = new StringBuilder();
        output.append(line0);

        for (int row = 0; row < 9; row++) {
            output.append("║");

            for (int col = 0; col < 9; col++) {
                if (this.puzzle[row][col] == 0) {
                    output.append("   ");
                } else {
                    output.append(" " + this.puzzle[row][col] + " ");
                }

                if (col == 8) {
                    output.append("║\n");
                } else if (col % 3 == 2) {
                    output.append("║");
                } else {
                    output.append("│");
                }
            }

            if (row == 8) {
                output.append(line3);
            } else if (row % 3 == 2) {
                output.append(line2);
            } else {
                output.append(line1);
            }
        }

        return output.toString();

    }

    public int getNum(int row, int col) throws ArrayIndexOutOfBoundsException {
        // returns the specified value
        return this.puzzle[row][col];
    }

    public int[] getRow(int row) throws ArrayIndexOutOfBoundsException {
        // returns the specified row
        return this.puzzle[row];
    }

    public int[] getCol(int col) throws ArrayIndexOutOfBoundsException {
        // returns the specified column.
        int[] out = new int[9];

        for (int row = 0; row < 9; row++) {
            out[row] = this.puzzle[row][col];
        }

        return out;
    }

    public int[] getBlock(int row, int col) throws ArrayIndexOutOfBoundsException {
        // returns the specified 3x3 block.

        int[] out = new int[9];
        int b_row = row / 3; // some num between 0 and 2
        int b_col = col / 3;
        int k = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                out[k] = this.puzzle[3 * b_row + i][3 * b_col + j];
                k++;
            }
        }

        return out;
    }

    public void setNum(int row, int col, int val) {
        this.puzzle[row][col] = val;
    }

}
