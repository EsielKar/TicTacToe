package model;

import model.function.Tree;

public interface TicTacToeStrategy {
    public Tree<TicTacToeNode> execute(TicTacToeFunction function);
}
