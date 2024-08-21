package telran.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract public class ListTest extends CollectionTest {
    List<Integer> list;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        list = (List<Integer>) collection;
    }

    @Test
    void testAddByIndex() {
        list.add(0, 10);
        assertEquals(10, list.get(0));
        list.add(1, 20);
        assertEquals(20, list.get(1));
        list.add(1, 15);
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    @Test
    void testRemoveByIndex() {
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 30);
        assertEquals(20, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @Test
    void testGet() {
        list.add(0, 10);
        list.add(1, 20);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void testIndexOf() {
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 10);
        assertEquals(0, list.indexOf(10));
        assertEquals(1, list.indexOf(20));
        assertEquals(-1, list.indexOf(30));
    }

    @Test
    void testLastIndexOf() {
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 10);
        assertEquals(2, list.lastIndexOf(10));
        assertEquals(1, list.lastIndexOf(20));
        assertEquals(-1, list.lastIndexOf(30));
    }
}
