package model;

import model.function.Assessable;

public class TicTacToeFunction implements Assessable<TicTacToeNode> {
    public final TicTacToeNode initial;


    public TicTacToeFunction(TicTacToeNode initial) {
        this.initial = initial;
    }

    @Override
    public double evaluate(TicTacToeNode node) {
        return TicTacToeGame.possibleWins(node.tile, node.getState()) - 
        TicTacToeGame.possibleWins(node.tile.getOppositeTile(), node.getState());
    }
    
}
