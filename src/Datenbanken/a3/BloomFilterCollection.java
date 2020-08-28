package Datenbanken.a3;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class BloomFilterCollection<E> implements Collection<E> {

    IBloomFilter<E> bloomFilter;
    Collection<E> collection;


    public BloomFilterCollection(IBloomFilter<E> bitFilter, Collection<E> collection) {
        bloomFilter = bitFilter;
        this.collection = collection;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return (collection.size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        if (!bloomFilter.canContain((E) o)) {
            return false;
        } else {
            return collection.contains(o);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(E e) {
        bloomFilter.insert(e);
        return collection.add(e);
    }

    @Override
    public boolean remove(Object o) {
        if (bloomFilter.canContain((E) o)) {
            boolean removed = collection.remove(o);
            bloomFilter.rebuild(collection);
            return removed;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) { return false; }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            bloomFilter.insert(e);
        }
        return collection.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean didSomething = false;
        for (Object o : c) {
            if (bloomFilter.canContain((E)o)) {
                collection.remove(o);
                didSomething = true;
            }
        }
        if (didSomething) { bloomFilter.rebuild(collection); return true; } else { return false; }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean retained =  collection.retainAll(c);
        bloomFilter.rebuild(collection);
        return retained;
    }

    @Override
    public void clear() {
        collection.clear();
        bloomFilter.rebuild(collection);
    }
}
