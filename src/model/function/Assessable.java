package model.function;

public interface Assessable<T extends Node<?>> {
    public double evaluate(T node);
}
