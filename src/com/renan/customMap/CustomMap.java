package com.renan.customMap;

import java.util.*;

public class CustomMap<K,V> implements Map<K,V> {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    final private ArrayList<Node<K,V>> table;
    private int capacity;
    private int size;

    static class Node<K,V> implements Map.Entry<K,V> {
        private final int hash;
        private final K key;
        private V value;
        Node<K,V> next;

        public Node(K key, V value) {
            this.hash = key.hashCode();
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    public CustomMap(K key, V value) {
        this.table = new ArrayList<>(DEFAULT_INITIAL_CAPACITY);
        this.size = 0;
        this.capacity = DEFAULT_INITIAL_CAPACITY;
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
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        final int index = key.hashCode() % this.capacity;
        Node head = table.get(index);
        Node prev = null;

        while (head != null) {
            if (head.getKey() == key) {
                return value;
            }
            prev = head;
            head = head.next;
        }

        Node newNode = new Node<>(key, value);

        if (prev == null) {
            table.add(index, newNode);
        } else {
            newNode.next = prev.next;
            prev.next = newNode;
        }

        return value;
    }

    @Override
    public V remove(Object key) {
        final int index = key.hashCode() % this.capacity;
        Node<K,V> head = table.get(index);
        Node<K,V> prev = null;

        while (head != null) {
            if (head.getKey() == key) {
                if (prev == null) {
                    return table.remove(index).getValue();
                }
                prev.next = head.next;
                return head.getValue();
            }
            prev = head;
            head = head.next;
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
