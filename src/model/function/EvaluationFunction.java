package model.function;

import java.util.Set;

/**
 *
 * @author Esiel
 */
public final class EvaluationFunction<T extends Node<?>> extends AbstractEvaluationFunction<T> {

    public EvaluationFunction(T initial, T goal, Set<G<T>> gComponents, Set<H<T>> hComponents) {
        super(initial, goal, gComponents, hComponents);
    }

    @Override
    public double evaluate(T node) {
        double total = 0;
        if (gComponents != null)
            for (G<T> g : gComponents)
                if (g != null)
                    total += g.evaluate(initial, node);

        if (hComponents != null)
            for (H<T> h : hComponents)
                if (h != null)
                    total += h.evaluate(goal, node);

        return total;
    }
    
}
