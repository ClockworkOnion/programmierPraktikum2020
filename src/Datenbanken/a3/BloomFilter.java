package Datenbanken.a3;

import Datenbanken.hashing.HashFunction;

import java.util.BitSet;
import java.util.Collection;

public class BloomFilter<E> implements IBloomFilter<E> {

    BitSet bitSet;
    int bitSetSize;
    Collection<HashFunction<E>> hashFunctions;

    public BloomFilter(int numBits, Collection<HashFunction<E>> hashFunctions) {
        bitSet = new BitSet(numBits);
        bitSetSize = numBits;
        this.hashFunctions = hashFunctions;
    }


    @Override
    public boolean canContain(E e) {
        for (HashFunction<E> function : hashFunctions) {
            if (!bitSet.get(function.hashMod(e, bitSetSize)))  { return false; }
        }
        return true;
    }

    @Override
    public void rebuild(Iterable<E> es) {
        bitSet = new BitSet(bitSetSize);
        for (E e : es) {
            insert(e);
        }
    }

    @Override
    public void insert(E e) {
        for (HashFunction<E> function : hashFunctions) {
            bitSet.set(function.hashMod(e, bitSetSize));
        }
    }
}
