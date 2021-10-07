package model;

import model.function.Assessable;

public class TicTacToeFunction implements Assessable<TicTacToeNode> {
    public final TicTacToeNode initial;


    public TicTacToeFunction(TicTacToeNode initial) {
        this.initial = initial;
    }

    @Override
    public double evaluate(TicTacToeNode node) {
        return node.getState().possibleWinsOf(node.getPlayer()) - 
        node.getState().possibleWinsOf((node.isMax()) ? TicTacToePlayer.MIN : TicTacToePlayer.MAX);
    }
    
}
