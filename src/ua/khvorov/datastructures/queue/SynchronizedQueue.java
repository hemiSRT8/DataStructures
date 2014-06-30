package ua.khvorov.datastructures.queue;

import ua.khvorov.datastructures.list.LinkedList;

import java.util.NoSuchElementException;

public class SynchronizedQueue extends LinkedList implements Queue {

    @SuppressWarnings("unchecked")
    @Override
    public synchronized boolean add(Object o) {
        super.add(o);
        notifyAll();

        return true;
    }

    @Override
    public boolean offer(Object o) {
        return add(o);
    }

    @Override
    public Object remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Object temp = get(0);
        remove(0);

        return temp;
    }

    @Override
    public synchronized Object poll() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        Object temp = get(0);
        remove(0);

        return temp;
    }

    @Override
    public Object element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return get(0);
    }

    @Override
    public Object peek() {
        return isEmpty() ? null : get(0);
    }
}
