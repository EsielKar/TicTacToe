package model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import model.function.Node;


public final class TicTacToeNode extends Node<TicTacToeState> {
    private final TicTacToePlayer player;

    private TicTacToeNode(TicTacToeState state, Node<TicTacToeState> parent, TicTacToePlayer player) {
        super(state, parent);
        evaluation = (this.player = player) == TicTacToePlayer.MAX ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
    }

    public TicTacToeNode(TicTacToeState state, TicTacToePlayer player) {
        this(state, null, player);
    }

    public TicTacToePlayer getPlayer() { return player; }
    public boolean isMax() { return player == TicTacToePlayer.MAX; }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public void generateChildren() {
        children = generateChildrenOf(this);
    }

    @SuppressWarnings ("unchecked")
    @Override
    public Collection<TicTacToeNode> getChildren() {
        return (Collection<TicTacToeNode>) super.getChildren();
    }


    public static List<TicTacToeNode> generateChildrenOf(TicTacToeNode node) {
        return node.state.generateNextStates(
            (node.isMax() ? TicTacToePlayer.MAX : TicTacToePlayer.MIN)
        ).stream().map(
            state -> new TicTacToeNode(state, node, (node.isMax() ? TicTacToePlayer.MIN : TicTacToePlayer.MAX))
        ).collect(Collectors.toList());
    }
}
