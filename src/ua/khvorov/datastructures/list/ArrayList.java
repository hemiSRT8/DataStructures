package ua.khvorov.datastructures.list;

public class ArrayList {

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

    public void add(Object element) {
        add(element, size);
    }


    public void add(Object... args) {
        for (Object element : args) {
            add(element);
        }
    }

    public Object get(int index) {
        rangeCheck(index);
        return array[index];
    }

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

    public void set(Object element, int index) {
        rangeCheckForAdd(index);
        array[index] = element;
    }

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

    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int index = 0; index < size; index++) {
            array[index] = null;
        }
        size = 0;
    }

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
