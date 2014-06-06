package ua.khvorov.datastructures.list.arraylist;

import ua.khvorov.datastructures.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList implements List, Iterable {

    //Fields
    private Object[] array;
    private int size;

    //Constructors
    public ArrayList() {
        array = new Object[10];
    }

    public ArrayList(int amountOfObjects) {
        initialSizeCheck(amountOfObjects);
        array = new Object[amountOfObjects];
    }

    @Override
    public void add(Object element, int index) {
        rangeCheckForAdd(index);
        if (size < array.length) {
            System.arraycopy(array, index, array, index + 1, size - index);
        } else {
            Object[] result = new Object[array.length * 2];
            System.arraycopy(array, 0, result, 0, size);
            System.arraycopy(result, index, result, index + 1, size - index);
            array = result;
        }
        array[index] = element;
        size++;
    }

    @Override
    public void add(Object element) {
        add(element, size);
    }


    @Override
    public void add(Object... args) {
        for (Object element : args) {
            add(element);
        }
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);
        return array[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (array[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(array[index])) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        if (element == null) {
            for (int index = size - 1; index >= 0; index--) {
                if (array[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = size - 1; index >= 0; index--) {
                if (element.equals(array[index])) {
                    return index;
                }
            }
        }

        return -1;
    }

    @Override
    public void set(Object element, int index) {
        rangeCheckForAdd(index);
        array[index] = element;
    }

    @Override
    public void remove(int index) {
        rangeCheck(index);
        if (index == size - 1) {
            array[index] = null;
        } else {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[size - 1] = null;
        }
        size--;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int index = 0; index < size; index++) {
            array[index] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    private void initialSizeCheck(int amountOfObjects) {
        if (amountOfObjects < 0) {
            throw new IllegalArgumentException("Amount of objects cannot be less than 0 , your amount of objects is : " +
                    amountOfObjects);
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {

        private int position = -1;
        private int check = 0;

        private ArrayListIterator() {

        }

        @Override
        public boolean hasNext() {
            return size != 0 && check < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            position++;
            check++;
            return get(position);
        }

        @Override
        public void remove() {
            ArrayList.this.remove(position);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            s.append(array[i]);
            if (i != size - 1) {
                s.append(" , ");
            }
        }
        return s.toString();
    }
}
