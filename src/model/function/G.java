package model.function;

public interface G <T extends Node<?>> {
    public double evaluate(T initial, T node);
}
