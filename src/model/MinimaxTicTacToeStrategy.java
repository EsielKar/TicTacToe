package model;

import model.function.Tree;

public class MinimaxTicTacToeStrategy implements TicTacToeStrategy {
    private int deepLimit;

    public MinimaxTicTacToeStrategy (int deepLimit) {
        this.deepLimit = deepLimit;
    }

    @Override
    public Tree<TicTacToeNode> execute(TicTacToeFunction function) {
        TicTacToeNode initialNode = function.initial;        
        long initTime = System.currentTimeMillis();
        minimax(initialNode, function, Math.min(initialNode.getState().remainingMoves(), deepLimit));
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - initTime) + " milisegundos");
        return new Tree<TicTacToeNode>(initialNode);
    }

    private void minimax(TicTacToeNode node, TicTacToeFunction function, int limit) {
        if (node.getLevel() < limit) {
            node.addChildren(node.getState().generateNextStates((node.isMax() ? TicTacToePlayer.MAX : TicTacToePlayer.MIN)));
            /*for (var s : node.getState().generateNextStates((node.isMax() ? TicTacToePlayer.MAX : TicTacToePlayer.MIN))){
                node.addChild(s);
            }*/
            //node.generateChildren();
            for (TicTacToeNode child : node.getChildren()) {
                TicTacToePlayer winner = child.getState().findWinner();
                if (winner == null) minimax(child, function, limit);
                else child.setEvaluation((winner == TicTacToePlayer.MAX) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
            }

            for (TicTacToeNode child : node.getChildren()) {
                if (node.isMax()) node.setEvaluation(Math.max(node.getEvaluation(), child.getEvaluation()));   
                else node.setEvaluation(Math.min(node.getEvaluation(), child.getEvaluation()));
            }
        } else node.evaluate(function);
    }

    
}
