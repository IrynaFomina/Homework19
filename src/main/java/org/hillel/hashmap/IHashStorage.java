package org.hillel.hashmap;

public interface IHashStorage<K,V> {
    boolean add(K key, V value);
    V get(K key);
}
