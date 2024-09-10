package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.LinkedList.Node;


public class LinkedHashSet<T> implements Set<T> {
    private LinkedList<T> list = new LinkedList<>();
    HashMap<T, Node<T>> map = new HashMap<>();

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);

        }

        return res;
    }

    
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(T pattern) {
        return map.get(pattern) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedHashSetIterator();     
    }
    class LinkedHashSetIterator implements Iterator<T> {
        private final Iterator<T> listIterator = list.iterator();
        private T currentIterator = null;

        @Override
        public boolean hasNext() {
            return listIterator.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();

            }
            currentIterator = listIterator.next();
            return currentIterator;
        }
        @Override
        public void remove() {
            if (currentIterator == null) {
                throw new IllegalStateException();
            }
            LinkedHashSet.this.remove(currentIterator);
            currentIterator = null;
        }
    }

    @Override
    public T get(Object pattern) {
        LinkedList.Node<T> node = map.get(pattern);
        return node != null ? node.obj : null;
    }

    @Override
    public boolean remove(T pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

}