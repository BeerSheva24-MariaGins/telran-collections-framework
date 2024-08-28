package telran.util;

import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    List<T>[] hashTable;
    float factor;
    int size;
    private class HashSetIterator implements Iterator<T> {
        
        private int currentIndex = 0;
        private int prevIndex = -1;
        Iterator<T> currentIterator;
        Iterator<T> prevIterator;
        int indexIterator;
        @Override
        public boolean hasNext() {
            return currentIndex < hashTable.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prevIndex = currentIndex;
            List<T> currentList = hashTable[currentIndex];
            if (currentList != null) {
                currentIterator = currentList.iterator();
                return currentIterator.next();
            }
            currentIndex++;
            return next();
        }
        
        @Override
        public void remove() {
            if (prevIndex == -1) {
                throw new IllegalStateException("Call next() before remove()");
            }
            List<T> currentList = hashTable[prevIndex];
            if (currentList != null) {
                currentIterator.remove();
                if (currentList.isEmpty()) {
                    hashTable[prevIndex] = null;
                }
                size--;
            }
        }
    }

    

    public HashSet(int hashTableLength, float factor) {
        hashTable = new List[hashTableLength];
        this.factor = factor;
    }

    public HashSet() {
        this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            if (size >= hashTable.length * factor) {
                hashTableReallocation();
            }

            addObjInHashTable(obj, hashTable);
            
            size++;
        }
        return res;

    }

    private void addObjInHashTable(T obj, List<T>[] table) {
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list == null) {
            list = new ArrayList<>(3);
            table[index] = list; 
        }
        list.add(obj);
    }

    private int getIndex(T obj, int length) {
        int hashCode = obj.hashCode();
        return Math.abs(hashCode % length);
    }

    private void hashTableReallocation() {
       List<T> []tempTable = new List[hashTable.length * 2];
       for(List<T> list: hashTable) {
        if(list != null) {
            list.forEach(obj -> addObjInHashTable(obj, tempTable));
            list.clear(); //??? for testing if it doesn't work remove this statement
        }
       }
       hashTable = tempTable;

    }

    @Override
    public boolean remove(T pattern) {
        boolean result = false;
        int index = getIndex(pattern, hashTable.length);
        List<T> list = hashTable[index];
        if (list != null && list.remove(pattern)) {
            size--;
            if (list.isEmpty()) {
                hashTable[index] = null;
            }
            result = true;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
       return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        int index = getIndex(pattern, hashTable.length);
        List<T> list = hashTable[index];
        return list != null && list.contains(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashSetIterator();
    }

    @Override
    public T get(Object pattern) {
        int index = getIndex((T) pattern, hashTable.length);
        List<T> list = hashTable[index];
    
        if (list != null) {
            int listIndex = list.indexOf((T) pattern);
            if (listIndex != -1) {
                return list.get(listIndex);
            }
        }
    
        return null;
    }


}