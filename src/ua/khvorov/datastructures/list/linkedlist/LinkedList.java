package ua.khvorov.datastructures.list.linkedlist;

import ua.khvorov.datastructures.list.List;

public class LinkedList implements List {

    Node first = new Node();
    Node last = new Node();
    int size = 0;

    public LinkedList() {

    }

    @Override
    public void add(Object item, int index) {
        AddIndexRangeCheck(index);

        if (index == 0) {
            addFirst(item);
            return;
        } else if (index == size) {
            addLast(item);
            return;
        }

        Node target = first;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }

        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = target.prev;
        newNode.next = target;
        target.prev.next = newNode;
        target.prev = newNode;

        size++;
    }


    @Override
    public void add(Object item) {
        addLast(item);
    }

    public void addLast(Object item) {
        Node newNode = new Node();
        newNode.item = item;

        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void addFirst(Object item) {
        Node newNode = new Node();
        newNode.item = item;

        if (size == 0) {
            first = newNode;
            last = newNode;
        } else {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    @Override
    public void add(Object... args) {
        for (Object item : args) {
            addLast(item);
        }
    }

    @Override
    public Object get(int index) {
        indexRangeCheck(index);

        Node target = first;

        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    @Override
    public int indexOf(Object itemToSearch) {
        Node temp = first;

        if (itemToSearch == null) {
            for (int i = 0; i < size; i++) {
                if (temp.item == null) {
                    return i;
                }
                temp = temp.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (temp.item != null) {
                    if (temp.item.equals(itemToSearch)) {
                        return i;
                    }
                }
                temp = temp.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object itemToSearch) {
        Node temp = last;
        int index = size - 1;

        if (itemToSearch == null) {
            for (int i = index; i > 0; i--) {
                if (temp.item == null) {
                    return i;
                }
                temp = temp.prev;
            }
        } else {
            for (int i = size - 1; i > 0; i--) {
                if (temp.item != null) {
                    if (temp.item.equals(itemToSearch)) {
                        return i;
                    }
                    temp = temp.prev;
                }
            }
        }

        return -1;
    }

    @Override
    public void set(Object itemToSet, int index) {
        indexRangeCheck(index);

        Node target = first;

        for (int i = 0; i < index; i++) {
            target = target.next;
        }

        target.item = itemToSet;
    }

    @Override
    public void remove(int index) {
        indexRangeCheck(index);

        if (size == 1) {
            first = null;
            last = null;
            size--;
            return;
        } else if (index == 0) {
            first = first.next;
            first.prev = null;
            size--;
            return;
        } else if (index == size - 1) {
            last = last.prev;
            last.next = null;
            size--;
            return;
        }

        Node target = first;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        target.prev.next = target.next;
        target.next.prev = target.prev;

        size--;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Node temp = first;
        for (int i = 0; i < size; i++) {
            temp.item = null;
            temp = temp.next;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    private void AddIndexRangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
    }

    private void indexRangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
    }

    private String indexOutOfBoundsMessage(int index) {
        return "index : " + index + ", size : " + size;
    }


    private static class Node {
        private Object item;
        private Node next;
        private Node prev;

        public Node() {
        }
    }
}