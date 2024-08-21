package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = { 3, -10, 20, 1, 10, 8, 100, 17 };

    @BeforeEach
    void setUp() {
        Arrays.stream(array).forEach(collection::add);
    }

    @Test
    void addTest() {
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        assertEquals(array.length + 2, collection.size());
    }

    @Test
    void sizeTest() {
        assertEquals(array.length, collection.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
        collection = new ArrayList<>(); 
        assertTrue(collection.isEmpty());
    }

    @Test
    void containsTest() {
        assertTrue(collection.contains(3));
        assertFalse(collection.contains(999));
    }

    @Test
    void removeTest() {
        assertTrue(collection.remove(3));
        assertFalse(collection.remove(999));
        assertEquals(array.length - 1, collection.size());
    }

    @Test
    void iteratorTest() {
        Iterator<Integer> iterator = collection.iterator();
        for (int i = 0; i < array.length; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(array[i], iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void streamTest() {
        long count = collection.stream().count();
        assertEquals(array.length, count);
    }

    @Test
    void parallelStreamTest() {
        long count = collection.parallelStream().count();
        assertEquals(array.length, count);
    }
}