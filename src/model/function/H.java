package model.function;

public interface H<T extends Node<?>> {
    public double evaluate(T goal, T node);
}

