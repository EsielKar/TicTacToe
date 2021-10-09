package model;


public class OptimalTicTacToeFunction extends AbstractTicTacToeFunction {
    public OptimalTicTacToeFunction(TicTacToeNode initial) {
        super(initial);
    }

    @Override
    public double evaluate(TicTacToeNode node) {
        return TicTacToeGame.possibleWins(node.tile, node.getState()) - 
        TicTacToeGame.possibleWins(node.tile.getOppositeTile(), node.getState());
    }
    
}