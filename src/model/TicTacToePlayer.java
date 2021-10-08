package model;

public abstract class TicTacToePlayer {
    protected final TicTacToeTile tile;

    public TicTacToePlayer(TicTacToeTile tile) {
        this.tile = tile;
    }

    public abstract TicTacToeState getMove(TicTacToeState currentState); 
}
