package model.function;

public class Tree<T extends Node<?>> {
    private T root;

    public Tree(T root) {
        this.root = root;
    }

    public T getRoot() {
        return root;
    }
    
}
