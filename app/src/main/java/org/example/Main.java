package org.example;

import java.io.File;

public class Main {

    public static long num_puzzles;

    public static void main(String[] args) {

        // get inputs to set static variables.
        if (args.length == 1) {
            // number of puzzles to generate
            if (Integer.parseInt(args[0]) > 0) {
                num_puzzles = Integer.parseInt(args[0]);
            } else {

            }
        }

        new File("output_puzzles/").mkdirs();

        // start timer
        final long startTime = System.currentTimeMillis();

        for (int i = 0; i < num_puzzles; i++) {
            Sudoku_Generator sg = new Sudoku_Generator(i);
            sg.run();
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
        System.out.println("To generate " + num_puzzles + " sudoku puzzles.");

        System.exit(0);
    }
}
