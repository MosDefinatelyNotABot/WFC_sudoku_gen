package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Entropy_Table {
    // holds entropy objects for each block in the puzzle
    public ArrayList<Entropy> table;
    public int num_empty = 81;

    public Entropy_Table(Sudoku puzzle) {
        this.table = new ArrayList<Entropy>(80);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Entropy block = new Entropy(row, col);

                if (block.set_entropy(puzzle) > 0) {
                    this.table.add(block);
                }
            }
        }

        Collections.sort(this.table);
    }

    public void update_table(Sudoku puzzle) {

        for (Entropy block : this.table) {
            int curr_ent = block.set_entropy(puzzle);
            if (curr_ent == 0) {
                this.table.remove(new Entropy(block.get_row(), block.get_col()));
            }
        }

        Collections.sort(this.table);

        int curr_empty = 81;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle.getNum(row, col) != 0) {
                    curr_empty -= 1;
                }
            }
        }
        this.num_empty = curr_empty;

    }

    public String toString() {
        return Arrays.toString(this.table.toArray());
    }

}
