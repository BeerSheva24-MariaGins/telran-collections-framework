package telran.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract public class ListTest extends CollectionTest {
    List<Integer> list;

    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) collection;
    }

    @Test
    void addAtIndexTest() {
        list.add(0, 200);
        assertEquals(200, list.get(0));
        assertEquals(array.length + 1, list.size());
    }

    @Test
    void removeAtIndexTest() {
        int removedElement = list.remove(0);
        assertEquals(array[0], removedElement);
        assertEquals(array.length - 1, list.size());
    }

    @Test
    void getTest() {
        assertEquals(array[0], list.get(0));
        assertEquals(array[array.length - 1], list.get(array.length - 1));
    }

    @Test
    void indexOfTest() {
        assertEquals(0, list.indexOf(array[0]));
        assertEquals(array.length - 1, list.indexOf(array[array.length - 1]));
        assertEquals(-1, list.indexOf(200)); 
    }

    @Test
    void lastIndexOfTest() {
        list.add(array[0]); 
        assertEquals(array.length, list.lastIndexOf(array[0]));
        assertEquals(array.length - 1, list.lastIndexOf(array[array.length - 1]));
        assertEquals(-1, list.lastIndexOf(200)); 
    }
}