package model.function;

import java.util.Set;

public abstract class AbstractEvaluationFunction<T extends Node<?>> implements Assessable<T> {
    protected final T initial;
    protected final T goal;

    protected final Set<G<T>> gComponents;
    protected final Set<H<T>> hComponents;

    public AbstractEvaluationFunction(T initial, T goal, Set<G<T>> gComponents, Set<H<T>> hComponents) {
        this.initial = initial;
        this.goal = goal;
        this.gComponents = gComponents;
        this.hComponents = hComponents;
    }

    public T getInitial() {
        return this.initial;
    }

    public T getGoal() {
        return this.goal;
    }
    
}
