package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.lang.Integer;

import org.example.Sudoku;

public class Entropy implements Comparable<Entropy> {
    // holds entropy value for one block in the puzzle
    private int row;
    private int col;
    private int entropy;
    public ArrayList<Integer> possibilityList;

    public Entropy(int row, int col) {
        // constructor
        this.row = row;
        this.col = col;
        this.entropy = 9;
        possibilityList = new ArrayList<Integer>(9);
        Collections.addAll(possibilityList, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Override
    public int compareTo(Entropy other) {
        return Integer.compare(this.get_entropy(), other.get_entropy());
    }

    @Override
    public String toString() {
        return "(" + this.row + "," + this.col + ") ent: " + this.entropy;
    }

    public int get_row() {
        return this.row;
    }

    public int get_col() {
        return this.col;
    }

    public int get_entropy() {
        return this.entropy;
    }

    public int set_entropy(Sudoku puzzle) {

        // check if this block is already assigned.
        if (puzzle.getNum(this.row, this.col) != 0) {
            this.entropy = 0;
            this.possibilityList = new ArrayList<Integer>();
            return 0;
        }

        // check rows
        int[] row = puzzle.getRow(this.row);
        for (int puzzle_num : row) {
            for (int i = 0; i < possibilityList.size(); i++) {
                if (puzzle_num == possibilityList.get(i)) {
                    this.possibilityList.remove(possibilityList.get(i));
                }
            }
        }
        row = null; // release array memory

        // now check columns
        int[] col = puzzle.getCol(this.col);
        for (int puzzle_num : col) {
            for (int i = 0; i < possibilityList.size(); i++) {
                if (puzzle_num == possibilityList.get(i)) {
                    this.possibilityList.remove(possibilityList.get(i));
                }
            }
        }
        col = null; // release array memory

        // finally check the block
        int[] block = puzzle.getBlock(this.row, this.col);
        for (int puzzle_num : block) {
            for (int i = 0; i < possibilityList.size(); i++) {
                if (puzzle_num == possibilityList.get(i)) {
                    this.possibilityList.remove(possibilityList.get(i));
                }
            }
        }
        block = null;

        this.entropy = this.possibilityList.size();

        return this.entropy;
    }

    public int get_ran_value(Random RNG) throws IllegalArgumentException {
        Random rand = RNG;
        // System.out.println(this.possibilityList.toString() + " " + this.row + "," +
        // this.col);
        if (this.possibilityList.size() == 0) {
            return 0;
        }
        int ran_idx = rand.nextInt(this.possibilityList.size());

        int ran_val = this.possibilityList.get(ran_idx);
        this.possibilityList.remove(ran_idx);
        this.entropy = this.possibilityList.size();

        return ran_val;
    }

    public int get_ran_value() throws IllegalArgumentException {
        Random rand = new Random();

        int ran_idx = rand.nextInt(this.possibilityList.size());
        int ran_val = this.possibilityList.get(ran_idx);
        this.possibilityList.remove(ran_idx);
        this.entropy = this.possibilityList.size();

        return ran_val;
    }
}