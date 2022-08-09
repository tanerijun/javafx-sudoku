package com.tanerijun.javafxsudoku.problemdomain;

import com.tanerijun.javafxsudoku.computationlogic.SudokuUtilities;
import com.tanerijun.javafxsudoku.constants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable  {
    private final GameState gameState;
    private final int[][] gridState; // represents the Sudoku grid
    public static final int GRID_BOUNDARY = 9; // 9 x 9 Sudoku board

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int[][] getGridStateCopy() {
        // Return a copy of the grid to prevent mutation
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
