package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = {3, -10, 20, 1, 10, 8, 100 , 17};

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
        assertTrue(collection.contains(20));
        assertFalse(collection.contains(200));
    }

    @Test
    void removeTest() {
        assertTrue(collection.remove(20));
        assertFalse(collection.contains(20));
        assertEquals(array.length - 1, collection.size());
        assertFalse(collection.remove(200)); 
    }

    @Test
    void streamTest() {
        Stream<Integer> stream = collection.stream();
        assertEquals(array.length, stream.count());
    }

    @Test
    void parallelStreamTest() {
        Stream<Integer> stream = collection.parallelStream();
        assertEquals(array.length, stream.count());
    }

    @Test
    void iteratorTest() {
        var iterator = collection.iterator();
        for (int i = 0; i < array.length; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(array[i], iterator.next());
        }
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}