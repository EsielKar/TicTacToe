package model;

import model.function.Tree;

public class MinimaxTicTacToeStrategy implements TicTacToeStrategy {
    private int deepLimit;

    public MinimaxTicTacToeStrategy (int deepLimit) {
        this.deepLimit = deepLimit;
    }

    @Override
    public Tree<TicTacToeNode> execute(TicTacToeFunction function) {
        Tree<TicTacToeNode> tree = new Tree<>(function.initial);      
        //long initTime = System.currentTimeMillis();
        minimax(tree.getRoot(), function, Math.min(tree.getRoot().getState().remainingMoves(), deepLimit));
        //System.out.println("Tiempo total: " + (System.currentTimeMillis() - initTime) + " milisegundos");
        return tree;
    }

    private void minimax(TicTacToeNode node, TicTacToeFunction function, int limit) {
        if (node.getLevel() < limit) {
            node.addChildren(node.getState().generateNextStatesFiltered(node.tile));
            for (TicTacToeNode child : node.getChildren()) {
                TicTacToeTile winner = TicTacToeGame.findWinner(child.getState());
                if (winner == null) minimax(child, function, limit);
                else
                    if (child.isMax)
                        child.setEvaluation((child.tile == winner) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
                    else
                        child.setEvaluation((child.tile == winner) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY); 
            }

            for (TicTacToeNode child : node.getChildren()) {
                if (node.isMax) node.setEvaluation(Math.max(node.getEvaluation(), child.getEvaluation()));   
                else node.setEvaluation(Math.min(node.getEvaluation(), child.getEvaluation()));
            }
        } else node.evaluate(function);
    }    
}
