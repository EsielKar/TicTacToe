package model.function;

import java.util.Collection;

public abstract class Node<T> implements Comparable<Node<T>>{
    private Node<T> parent;
    protected Collection<? extends Node<T>> children;
    
    protected T state;
    protected double evaluation = Double.NaN;
    
    protected Node(T state, Node<T> parent) {
        this.parent = parent;
        this.state = state;
    }

    public Node(T state) {
        this(state, null);
    }

    /* Abstract Method */
    public abstract void generateChildren();

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
    
    public boolean isEvaluated() { return evaluation != Double.NaN; }

    @SuppressWarnings ("unchecked")
    public <E extends Node<T>> double evaluate(Assessable<E> assessable) {
        return evaluation = assessable.evaluate((E)this);
    }

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
