package ua.khvorov.datastructures.list;

public interface List<E> extends Iterable<E> {
    void add(E element, int index);

    void add(E element);

    void add(E... args);

    Object get(int index);

    int indexOf(E element);

    int lastIndexOf(E element);

    void set(E element, int index);

    void remove(int index);

    boolean contains(E element);

    boolean isEmpty();

    void clear();

    int size();
}
