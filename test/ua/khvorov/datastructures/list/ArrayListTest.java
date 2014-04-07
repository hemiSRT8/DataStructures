package ua.khvorov.datastructures.list;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;


public class ArrayListTest {

    private ArrayList array;

    @Before
    public void before() {
        array = new ArrayList();
    }

    @Test
    public void testAddWithIndex() throws Exception {
        assertTrue(array.isEmpty());
        array.add("A", 0);
        array.add("B", 1);
        assertTrue(array.size() == 2);
        assertEquals("A", array.get(0));
        array.add("C", 2);
        assertTrue(array.size() == 3);
        assertEquals("C", array.get(2));
        array.add(null, 3);
        assertEquals(null, array.get(3));
        assertTrue(array.size() == 4);
        array.add('D', 'F', 'T', 'H', 'M', 'X');
        assertTrue(array.size() == 10); // Массив заполнен , 10 из 10
        array.add("hi world!", 10);
        assertTrue(array.size() == 11);
    }

    @Test
    public void testAddWithoutIndex() throws Exception {
        assertTrue(array.isEmpty());
        array.add("A");
        array.add("B");
        assertTrue(array.size() == 2);
        assertEquals("A", array.get(0));
        assertEquals("B", array.get(1));
        array.add("C");
        assertTrue(array.size() == 3);
        assertEquals("C", array.get(2));
    }

    @Test
    public void testAddAll() throws Exception {
        assertTrue(array.isEmpty());
        array.add("A", "B", "C", "D", "E");
        assertTrue(array.size() == 5);
        assertEquals("A", array.get(0));
        assertEquals("B", array.get(1));
        assertEquals("C", array.get(2));
        array.add("F", 5);
        assertTrue(array.size() == 6);
        assertEquals("A", array.get(0));
        assertEquals("B", array.get(1));
        assertEquals("C", array.get(2));
        assertEquals("F", array.get(5));
        array.add(null, null, null);
    }

    @Test
    public void testIndexOf() throws Exception {
        array.add("A", 0);
        array.add("B", 1);
        assertTrue(array.indexOf("A") == 0);
        assertTrue(array.indexOf("B") == 1);
        array.set(null, 1);
        assertTrue(array.indexOf(null) == 1);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        array.add("A", 0);
        array.add("B", 1);
        array.add("A", 2);
        assertEquals("A", array.get(2));
        assertTrue(array.lastIndexOf("A") == 2);
        array.add("as");
        assertTrue(array.lastIndexOf("as") == 3);
    }

    @Test
    public void testGet() throws Exception {
        array.add("A", "B", "C");
        assertEquals("A", array.get(0));
        assertEquals("B", array.get(1));
        assertEquals("C", array.get(2));
    }

    @Test
    public void testSet() throws Exception {
        array.add("A", 0);
        array.add("B", 1);
        array.add("C", 2);
        array.add("D", 3);
        array.add("E", 4);
        assertTrue(array.indexOf("A") == 0);
        array.set("Chelsea FC", 0);
        assertTrue(array.indexOf("Chelsea FC") == 0);
        array.set(null, 0);
        assertTrue(array.indexOf(null) == 0);
    }

    @Test
    public void testRemove() throws Exception {
        array.add("A", "B", "C", "D", "E", "F", "G", "H", "T", "R");
        assertTrue(array.size() == 10);
        assertEquals("C", array.get(2));

        //Array is full , removing C
        array.remove(2);
        assertTrue(array.size() == 9);
        assertEquals("D", array.get(2));

        //Array is not full , removing B
        assertEquals("B", array.get(1));
        array.remove(1);
        assertTrue(array.size() == 8);
        assertEquals("D", array.get(1));
    }

    @Test
    public void testContains() throws Exception {
        array.add("A", "B", "C");
        assertTrue(array.contains("B"));

        array.remove(1);
        assertFalse(array.contains("B"));

        array.add("B");
        assertTrue(array.contains("B"));

        array.add(null, 3);
        assertTrue(array.contains(null));
    }

    @Test
    public void testIsEmpty() throws Exception {
        array.add("A", "B");
        assertFalse(array.isEmpty());

        array.remove(0);
        array.remove(0);
        assertTrue(array.isEmpty());

        array.add("A");
        assertFalse(array.isEmpty());
    }

    @Test
    public void testClear() throws Exception {
        array.add(1, 2, 3);
        assertTrue(array.size() == 3);
        assertEquals(1, array.get(0));
        assertEquals(2, array.get(1));
        assertEquals(3, array.get(2));
        array.clear();
        assertTrue(array.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddWithIndexException() {
        array.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertTrue(array.size() == 10);// коллекция заполнена , последний индекс = 9
        array.add(11, 11);// для добавления доступны индексы 9 и 10 , пробуем добавить в 11 - получаем исключение
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException() {
        array.add('a', 'b');
        assertTrue(array.size() == 2); // для метода get доступны индексы 0 и 1
        array.get(2); // пробуем достать элемент по индексу 2 - получаем исключение
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetExcetpion() {
        assertTrue(array.isEmpty()); //сейчас коллекция пустая , для метода set доступен только индекс 0
        array.set("hi world", 1); // пробуем засэтить в 1 - получаем исключение
        array.add(1, 2, 3, 4, 5); // в коллекции 5 элементов , последний индекс = 4 , для сэта доступны индексы : 4 и 5
        array.set('F', 7); // пробуем засетить в 7 - получаем исключение
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveExcetpion() {
        array.add("hey", "hi", "privet", "hello");
        assertTrue(array.size() == 4); // в коллекции 4 элемента
        // для метода remove доступны индексы : 0 , 1 , 2 , 3
        array.remove(4); // пробуем удалить элемент по индексу 4 - получаем  иключение
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialSizeCheck() {
        ArrayList al = new ArrayList(-1); //пробуем создать коллекцию с количество элементов меньше нуля - получаем исключение
    }
}





