package model;


public class TicTacToeFunction extends AbstractTicTacToeFunction{
    public TicTacToeFunction(TicTacToeNode initial) {
        super(initial);
    }

    @Override
    public double evaluate(TicTacToeNode node) {
        if (TicTacToeGame.findWinner(node.getState()) == null) return 0;
        return (node.isMax) ? 1 : -1;
    }
}
