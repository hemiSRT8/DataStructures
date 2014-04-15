package ua.khvorov.datastructures.list.linkedlist;

import ua.khvorov.datastructures.list.List;

public class LinkedList implements List {

    private Node[] array;
    private int size = 0;
    private Node first;
    private Node last;

    public LinkedList() {
        array = new Node[10];
    }

    public LinkedList(int amountOfObjects) {
        initialSizeCheck(amountOfObjects);
        array = new Node[amountOfObjects];
    }

    @Override
    public void add(Object obj, int index) {
        rangeCheckForAdd(index);

        if (size < array.length) { //Array is not full
            System.arraycopy(array, index, array, index + 1, size - index);
        } else { //Array is full
            Node[] result = new Node[array.length * 2];
            System.arraycopy(array, 0, result, 0, size);
            System.arraycopy(result, index, result, index + 1, size - index);
            array = result;
        }

        Node node = new Node();
        node.setObject(obj);
        node.setNext(null);
        node.setPrevious(last);
        last = node;

        if (size == 0) {
            first = node;
        }

        array[index] = node;
        size++;
    }

    @Override
    public void add(Object obj) {
        add(obj, size);
    }

    @Override
    public void add(Object... args) {
        for (Object obj : args) {
            add(obj);
        }
    }

    @Override
    public int indexOf(Object obj) {
        if (obj == null) {
            for (int index = 0; index < size; index++) {
                if (array[index].getObject() == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (array[index].getObject().equals(obj)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            for (int index = size - 1; index >= 0; index--) {
                if (array[index].getObject() == null) {
                    return index;
                }
            }
        } else {
            for (int index = size - 1; index >= 0; index--) {
                if (array[index].getObject().equals(obj)) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public Object get(int index) {
        rangeCheck(index);
        return array[index].getObject();
    }

    @Override
    public void set(Object obj, int index) {
        rangeCheck(index);
        array[index].setObject(obj);
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
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
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

    private static void initialSizeCheck(int amountOfObjects) {
        if (amountOfObjects < 0) {
            throw new IllegalArgumentException("Amount of objects cannot be < 0 , your amount of objects is : " +
                    amountOfObjects);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
}
