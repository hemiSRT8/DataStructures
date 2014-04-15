package ua.khvorov.datastructures.list;

import ua.khvorov.datastructures.list.linkedlist.LinkedList;

public class LinkedListTest extends AbstractListTest {
    @Override
    public List createList() {
        return new LinkedList();
    }
}
