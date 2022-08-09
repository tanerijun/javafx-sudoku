package com.tanerijun.javafxsudoku.computationlogic;

import com.tanerijun.javafxsudoku.constants.GameState;
import com.tanerijun.javafxsudoku.constants.Rows;
import com.tanerijun.javafxsudoku.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tanerijun.javafxsudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    public static SudokuGame getNewGame() {
        return new SudokuGame(GameState.NEW, GameGenerator.getNewGameGrid());
    }

    public static GameState checkForCompletion(int[][] grid) {
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if (tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        return (rowsAreInvalid(grid) || columnsAreInvalid(grid) || squaresAreInvalid(grid));
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            List<Integer> column = new ArrayList<>();
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                column.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(column)) return true;
        }

        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        return (rowOfSquaresAreInvalid(Rows.TOP, grid) ||
                rowOfSquaresAreInvalid(Rows.MIDDLE, grid) ||
                rowOfSquaresAreInvalid(Rows.BOTTOM, grid));
    }

    private static boolean rowOfSquaresAreInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP -> {
                return (squareIsInvalid(0, 0, grid) ||
                        squareIsInvalid(3, 0, grid) ||
                        squareIsInvalid(6, 0, grid));
            }
            case MIDDLE -> {
                return (squareIsInvalid(0, 3, grid) ||
                        squareIsInvalid(3, 3, grid) ||
                        squareIsInvalid(6, 3, grid));
            }
            case BOTTOM -> {
                return (squareIsInvalid(0, 6, grid) ||
                        squareIsInvalid(3, 6, grid) ||
                        squareIsInvalid(6, 6, grid));
            }
            default -> {
                return false;
            }
        }
    }

    private static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {
            while (xIndex < xIndexEnd) {
                square.add(grid[xIndex][yIndex]);
                xIndex++;
            }

            xIndex -= 3;
            yIndex++;
        }

        return (collectionHasRepeats(square));
    }

    private static boolean collectionHasRepeats(List<Integer> collection) {
        for (int index = 1; index <= GRID_BOUNDARY; index++) {
            if (Collections.frequency(collection, index) > 1) return true;
        }

        return false;
    }

    private static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if(grid[xIndex][yIndex] == 0) return true;
            }
        }

        return false;
    }
}
