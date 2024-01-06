package dkSTS.cards.Helpers;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.Queue;


public class ConstructorBuilder<T> {
    private final Queue<Class<?>[]> constructorLists;
    private final Queue<Object[]> conParams;
    private final Class<? extends T> template;

    public ConstructorBuilder(Class<? extends T> template) {
        constructorLists = new LinkedList<>();
        conParams = new LinkedList<>();
        this.template = template;
    }

    public ConstructorBuilder<T> addConstruct(Class<?>...parameterTypes) {
        constructorLists.add(parameterTypes);
        return this;
    }

    public ConstructorBuilder<T> addConstructParams(Object...elements) {
        conParams.add(elements);
        return this;
    }

    public T build() {
        if (conParams.size() == constructorLists.size()) {
            Constructor<? extends T> con = buildHelper();
            if (con != null) {
                try {
                    return con.newInstance(conParams.poll());
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    private Constructor<? extends T> buildHelper() {
        if (constructorLists.isEmpty()) {
            return null;
        }
        Class<?>[] elem = constructorLists.poll();
        try {
            return template.getConstructor(elem);
        } catch (NoSuchMethodException e) {
            conParams.poll();
            return buildHelper();
        } catch (Exception e) {
            return null;
        }
    }
}
