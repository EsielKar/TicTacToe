package model;

import java.util.Collection;
import java.util.LinkedList;

import model.function.Assessable;
import model.function.Node;


public final class TicTacToeNode extends Node<TicTacToeState> {
    public final TicTacToeTile tile;
    public final boolean isMax;

    private TicTacToeNode(TicTacToeState state, Node<TicTacToeState> parent, TicTacToeTile tile, boolean isMax) {
        super(state, parent);
        children = new LinkedList<>();
        this.tile = tile;
        evaluation = (this.isMax = isMax) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
    }

    public TicTacToeNode(TicTacToeState state, TicTacToeTile tile, boolean isMax) {
        this(state, null, tile, isMax);
    }

    public TicTacToeNode(TicTacToeState state, TicTacToeTile tile) {
        this(state, null, tile, true);
    }
    
    /* GETTERS */
    @Override
    public TicTacToeNode getParent() { return (TicTacToeNode) super.getParent(); }
    
    @Override @SuppressWarnings ("unchecked")
    public LinkedList<TicTacToeNode> getChildren() {
        return (LinkedList<TicTacToeNode>) super.getChildren();
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /* ADD METHODS */
    @Override
    public boolean addChild(TicTacToeState state) {
        return children.add(new TicTacToeNode(state, this, tile.getOppositeTile(), !isMax));
    }
    @Override
    public boolean addChildren(Collection<TicTacToeState> children) {
        if (children.isEmpty()) return false;
        for (TicTacToeState state : children) addChild(state);
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TicTacToeNode)) {
            return false;
        }
        TicTacToeNode ticTacToeNode = (TicTacToeNode) o;
        return this.state.equals(ticTacToeNode.state);
    }

    public double evaluate(Assessable<TicTacToeNode> assessable) {
        return evaluation = assessable.evaluate(this);
    }

    public TicTacToeNode findChild(TicTacToeState state) {
        for (var child : getChildren())
            if (child.getState().equals(state)) return child;
        return null;
    }

    public TicTacToeNode getBestChild() {
        if (children == null || children.isEmpty()) return null;
        var bestChild = getChildren().getFirst();
        if (children.size() > 1)
            for (int i = 1 ; i < children.size() ; i++) {
                if (getChildren().get(i).getEvaluation() == 0) return getChildren().get(i);
                if (getChildren().get(i).evaluation > bestChild.getEvaluation())
                    bestChild = getChildren().get(i);
            }
        return bestChild;
    }
}
