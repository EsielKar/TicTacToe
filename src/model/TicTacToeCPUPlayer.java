package model;

import java.util.Random;
import java.util.stream.Collectors;

public class TicTacToeCPUPlayer extends TicTacToePlayer {
    private TicTacToeStrategy strategy;
    private TicTacToeNode currentNode;

    public TicTacToeCPUPlayer(TicTacToeTile tile, TicTacToeStrategy strategy) {
        super(tile);
        this.strategy = strategy;
    }

    @Override
    public TicTacToeState getMove(TicTacToeState currentState) {
        if (currentNode == null || !currentNode.hasChildren()) {
            currentNode = strategy.execute(
                new TicTacToeFunction(
                    new TicTacToeNode(currentState, tile)
                )
            ).getRoot();
        }
        
        if (!currentNode.getState().equals(currentState)) {
            currentNode = currentNode.findChild(currentState);
            if (!currentNode.hasChildren())
                return getMove(currentState);
        }
        currentNode = currentNode.getBestChild();

        var states = TicTacToeGame.generateNextStates(tile, currentState).stream().filter(
            state -> currentNode.getState().equals(state)).collect(Collectors.toList());
        
        return states.get(new Random().nextInt(states.size()));
    }
    
}
