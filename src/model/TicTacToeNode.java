package model;

import java.util.Collection;
import java.util.LinkedList;

import model.function.Assessable;
import model.function.Node;


public final class TicTacToeNode extends Node<TicTacToeState> {
    private final TicTacToePlayer player;

    private TicTacToeNode(TicTacToeState state, Node<TicTacToeState> parent, TicTacToePlayer player) {
        super(state, parent);
        children = new LinkedList<>();
        evaluation = (this.player = player) == TicTacToePlayer.MAX ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
    }

    public TicTacToeNode(TicTacToeState state, TicTacToePlayer player) {
        this(state, null, player);
    }
    
    /* GETTERS */
    @Override
    public TicTacToeNode getParent() { return (TicTacToeNode) super.getParent(); }
    public TicTacToePlayer getPlayer() { return player; }
    
    @Override @SuppressWarnings ("unchecked")
    public LinkedList<TicTacToeNode> getChildren() {
        return (LinkedList<TicTacToeNode>) super.getChildren();
    }

    /* ADD METHODS */
    @Override
    public boolean addChild(TicTacToeState state) {
        return children.add(new TicTacToeNode(state, this, (this.isMax() ? TicTacToePlayer.MIN : TicTacToePlayer.MAX)));
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

    
    /* IS METHODS */
    public boolean isMax() { return player == TicTacToePlayer.MAX; }

    public double evaluate(Assessable<TicTacToeNode> assessable) {
        return evaluation = assessable.evaluate(this);
    }

    public TicTacToeNode findChild(TicTacToeState state) {
        for (var child : getChildren())
            if (child.getState().equals(state)) return child;
        return null;
    }

    /*public TicTacToeNode getBestChild() {
        if (children == null || children.isEmpty()) return null;
        var bestChild = getChildren().getFirst();
        for (var child : children) {

        }

        return bestChild;
    }*/
}
