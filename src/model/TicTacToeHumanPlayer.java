package model;

import java.util.Scanner;

public class TicTacToeHumanPlayer extends TicTacToePlayer {
    private Scanner sc = new Scanner(System.in);
    
    public TicTacToeHumanPlayer(TicTacToeTile tile) {
        super(tile);
    }

    @Override
    public TicTacToeState getMove(TicTacToeState currentState) {
        System.out.print("¿Donde quieres poner tu ficha? (" + tile.toChar() + ")" + TicTacToeGame.availableMoves(currentState)+ ": ");
        var opc = sc.nextInt();
        var gameboard = currentState.getGameboard();
        gameboard[(opc-1) / 3][(opc-1) % 3] = tile;
        return new TicTacToeState(gameboard);
    }
}
