package model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

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
    
    @Override
    public TicTacToeNode getParent() { return (TicTacToeNode) super.getParent(); }
    public TicTacToePlayer getPlayer() { return player; }

    @Override
    public boolean addChild(TicTacToeState state) {
        return children.add(new TicTacToeNode(state, this, (this.isMax() ? TicTacToePlayer.MIN : TicTacToePlayer.MAX)));
    }

    @Override
    public boolean addChildren(Collection<TicTacToeState> children) {
        if (children.isEmpty()) return false;
        for (TicTacToeState state : children) addChild(state);
        return true;
        /*this.children.addAll(children.stream().map(
            state -> new TicTacToeNode(state, this, (this.isMax() ? TicTacToePlayer.MIN : TicTacToePlayer.MAX))
        ).collect(Collectors.toList()));*/
    }
    
    public boolean isMax() { return player == TicTacToePlayer.MAX; }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    /*@Override
    public void generateChildren() {
        children = generateChildrenOf(this);
    }*/

    @SuppressWarnings ("unchecked")
    @Override
    public Collection<TicTacToeNode> getChildren() {
        return (Collection<TicTacToeNode>) super.getChildren();
    }


    public static Collection<Node<TicTacToeState>> generateChildrenOf(TicTacToeNode node) {
        return node.state.generateNextStates(
            (node.isMax() ? TicTacToePlayer.MAX : TicTacToePlayer.MIN)
        ).stream().map(
            state -> new TicTacToeNode(state, node, (node.isMax() ? TicTacToePlayer.MIN : TicTacToePlayer.MAX))
        ).collect(Collectors.toList());
    }

    public double evaluate(Assessable<TicTacToeNode> assessable) {
        return evaluation = assessable.evaluate(this);
    }
}
