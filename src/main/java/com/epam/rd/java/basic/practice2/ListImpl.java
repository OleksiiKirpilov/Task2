package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class ListImpl implements List {

    private int size = 0;
    private Node firstNode;
    private Node lastNode;

    public ListImpl() {}

    @Override
    public void clear() {
        Node n = firstNode;
        while (n != null) {
            Node next = n.next;
            n.element = null;
            n.next = null;
            n.prev = null;
            n = next;
        }
        firstNode = lastNode = null;
        size = 0;
    }

    @Override
    public int size() {
        return 0;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private static class IteratorImpl implements Iterator<Object> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

    }

    private static class Node {
        Object element;
        Node next;
        Node prev;

        public Node(Node prev, Object element, Node next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void addFirst(Object element) {
        
    }

    @Override
    public void addLast(Object element) {
        
    }

    @Override
    public void removeFirst() {
        
    }

    @Override
    public void removeLast() {
        
    }

    @Override
    public Object getFirst() {
        return null;
    }

    @Override
    public Object getLast() {
        return null;
    }

    @Override
    public Object search(Object element) {
        return null;
    }

    @Override
    public boolean remove(Object element) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    public static void main(String[] args) {

    }
}
