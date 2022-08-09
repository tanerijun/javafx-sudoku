package com.tanerijun.javafxsudoku.buildlogic;

import com.tanerijun.javafxsudoku.computationlogic.GameLogic;
import com.tanerijun.javafxsudoku.persistence.LocalStorageImpl;
import com.tanerijun.javafxsudoku.problemdomain.IStorage;
import com.tanerijun.javafxsudoku.problemdomain.SudokuGame;
import com.tanerijun.javafxsudoku.userinterface.IUserInterfaceContract;
import com.tanerijun.javafxsudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
