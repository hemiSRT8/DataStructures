package ua.khvorov.datastructures.list;

/**
 * Created by umb on 11.04.2014.
 */
public interface List {
    void add(Object element, int index);

    void add(Object element);

    void add(Object... args);

    Object get(int index);

    int indexOf(Object element);

    int lastIndexOf(Object element);

    void set(Object element, int index);

    void remove(int index);

    boolean contains(Object element);

    boolean isEmpty();

    void clear();

    int size();
}
