package model.function;

import java.util.Collection;


public class Node<T> implements Comparable<Node<T>>{
    protected Node<T> parent;
    protected Collection<Node<T>> children;
    
    protected T state;
    protected double evaluation = Double.NaN;
    
    protected Node(T state, Node<T> parent) {
        this.parent = parent;
        this.state = state;
    }

    public Node(T state) {
        this(state, null);
    }

    public boolean addChild(T state) { return children.add(new Node<T>(state, this)); }
    public boolean addChildren(Collection<T> children) {
        if (children.isEmpty()) return false;
        for (T state : children) addChild(state);
        return true;
        /*this.children.addAll(
         children.stream().map(
            state -> new Node<T>(state, this)
        ).collect(Collectors.toList()));*/
}

    /* GETTERS */
    public double getEvaluation() { return evaluation; }
    public T getState() { return state; }
    public Node<T> getParent() { return parent; }
    public Collection<? extends Node<T>> getChildren() { return children; }
    public int getLevel() { return getLevel(parent); }
    private int getLevel(Node<T> node) {
        if (node == null) return 0;
        return getLevel(node.parent) + 1;
    }
    
    /* IS METHODS */
    public boolean isEvaluated() { return evaluation != Double.NaN; }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Node<?>)) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return state.equals(node.state);
    }

    @Override
    public int compareTo(Node<T> o) {
        return Double.compare(evaluation, o.evaluation);
    }
}
