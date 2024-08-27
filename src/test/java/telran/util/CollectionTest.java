package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import java.util.function.Predicate;

public abstract class CollectionTest {
    private static final int N_ELEMENTS = 1_000_000;
    protected Collection<Integer> collection;
    Random random = new Random();
    Integer[] array = {3, -10, 20, 1, 10, 8, 100 , 17};
    void setUp() {
        Arrays.stream(array).forEach(collection::add);
    }   

    @Test
    void removeIfTest() {
        assertTrue(collection.removeIf(n -> n % 2 == 0));
        assertFalse(collection.removeIf(n -> n % 2 == 0));
        assertTrue(collection.stream().allMatch(n -> n % 2 != 0));
    }
    @Test
    void clearTest() {
        collection.clear();
        assertTrue(collection.isEmpty());
    }
    @Test
    void addTest() {
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        runTest(new Integer[]{3, -10, 20, 1, 10, 8, 100 , 17, 200, 17});
    }
    @Test
    void sizeTest() {
        assertEquals(array.length, collection.size());
    }
@Test
    void iteratorTest() {
        Integer[] actual = new Integer[array.length];
        int index = 0;
        Iterator<Integer> it = collection.iterator();
       
        while(it.hasNext()) {
            actual[index++] = it.next();
        }
        
        assertArrayEquals(array, actual);
        assertThrowsExactly(NoSuchElementException.class, it::next );
    }
    @Test
    void removeInIteratorTest(){
        Iterator<Integer> it = collection.iterator();
        assertThrowsExactly(IllegalStateException.class, () -> it.remove());
        Integer n = it.next();
        it.remove();
        it.next();
        it.next();
        it.remove();
        assertFalse(collection.contains(n));
        assertThrowsExactly(IllegalStateException.class, () -> it.remove());

    }

    protected void runTest(Integer[] expected) {
        assertArrayEquals(expected, collection.stream().toArray(Integer[]::new));
        assertEquals(expected.length, collection.size());
    }
    @Test
    void streamTest() {
       runTest(array);
    }
    @Test
    void removeTest() {
        Integer[] expected = {-10, 20, 1,  8, 100 };
        assertTrue(collection.remove(10));
        assertTrue(collection.remove(3));
        assertTrue(collection.remove(17));
        runTest(expected);
        assertFalse(collection.remove(10));
        assertFalse(collection.remove(3));
        assertFalse(collection.remove(17));
        clear();
        runTest(new Integer[0]);

    }
    private void clear() {
        Arrays.stream(array).forEach(n -> collection.remove(n));
    }
    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
        clear();
        assertTrue(collection.isEmpty());
    }
    @Test
    void containsTest(){
       Arrays.stream(array).forEach(n -> assertTrue(collection.contains(n)));
       assertFalse(collection.contains(10000000));
    }
    @Test 
    void performanceTest() {
        collection.clear();
        IntStream.range(0, N_ELEMENTS).forEach(i -> collection.add(random.nextInt()));
        collection.clear();

    }

    @Test
    public void removeIfTest1() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Predicate<Integer> isEven = x -> x % 2 == 0;
        boolean removed = list.removeIf(isEven);

        assertTrue(removed);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void removeIfNoMatchTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);

        Predicate<Integer> isEven = x -> x % 2 == 0;
        boolean removed = list.removeIf(isEven);

        assertFalse(removed);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void removeIfAllMatchTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(6);

        Predicate<Integer> isEven = x -> x % 2 == 0;
        boolean removed = list.removeIf(isEven);

        assertTrue(removed);
        assertEquals(0, list.size());
    }

    @Test
    public void removeIfPerformanceTest() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        Predicate<Integer> isEven = x -> x % 2 == 0;

        long startTime = System.nanoTime();
        boolean removed = list.removeIf(isEven);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Execution time for removeIf: " + duration + " nanoseconds");

        assertTrue(removed);
        assertEquals(500000, list.size());
    }
    
}
