package model;

import model.function.Assessable;

public abstract class AbstractTicTacToeFunction implements Assessable<TicTacToeNode> {
    public final TicTacToeNode initial;

    public AbstractTicTacToeFunction(TicTacToeNode initial) {
        this.initial = initial;
    }   
}