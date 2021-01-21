package com.renan.customMap;

import java.util.*;

public class CustomMap<K,V> implements Map<K,V> {
    final int DEFAULT_CAPACITY = 1 << 4; // aka 16

    @SuppressWarnings({"unchecked"}) // Retirado da classe HashMap
    final private Node<K,V>[] table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    private int size;

    static class Node<K,V> implements Map.Entry<K,V> {
        private final K key;
        private V value;
        Node<K,V> next;

        public Node(K key, V value) {
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

    private class Iterator implements java.util.Iterator<Node<K,V>> {
        private final CustomMap map;
        private int index;
        private Node<K,V> node;

        public Iterator(CustomMap map) {
            this.map = map;
        }

        public void first() {
            this.index = -1;
            next();
        }

        @Override
        public boolean hasNext() {
            int cursor = index;
            
            if (node != null) {
                node = node.next;
            }

            while (node == null) {
                cursor++;
                try {
                    node = map.table[index];
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
            
            return true;
        }

        @Override
        public Node<K, V> next() {
            if (node != null) {
                node = node.next;
            }

            while (node == null) {
                index++;
                try {
                    node = map.table[index];
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }

            return node;
        }
    }

    public CustomMap() {
        this.size = 0;
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
        final int index = key.hashCode() % this.DEFAULT_CAPACITY;
        Node<K,V> node = table[index];

        while (node != null) {
            if (node.getKey() == key) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        final int index = key.hashCode() % this.DEFAULT_CAPACITY;
        Node<K,V> node = table[index];

        while (node != null) {
            if (node.getKey() == key) {
                return node.getValue();
            }
            node = node.next;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        final int index = key.hashCode() % this.DEFAULT_CAPACITY;
        Node<K,V> head = table[index];
        Node<K,V> prev = null;

        while (head != null) {
            if (head.getKey() == key) {
                return value;
            }
            prev = head;
            head = head.next;
        }

        Node<K,V> newNode = new Node<>(key, value);

        if (prev == null) {
            table[index] = newNode;
            size++;
        } else {
            newNode.next = prev.next;
            prev.next = newNode;
        }

        return value;
    }

    @Override
    public V remove(Object key) {
        final int index = key.hashCode() % this.DEFAULT_CAPACITY;
        Node<K,V> head = table[index];
        Node<K,V> prev = null;

        while (head != null) {
            if (head.getKey() == key) {
                if (prev == null) {
                    V value = table[index].getValue();
                    table[index] = null;
                    return value;
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
        Arrays.fill(table, null);
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

    private Iterator getIterator() {
        return new Iterator(this);
    }
}
