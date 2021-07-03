package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        Node cursor = firstNode;

        @Override
        public boolean hasNext() {
            return cursor.next != null;
        }

        @Override
        public Object next() {
            if (cursor.next == null){
                throw new NoSuchElementException();
            }
            cursor = cursor.next;
            return cursor.element;
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
        Node newNode = new Node(null, element, firstNode);
        firstNode.prev = newNode;
        firstNode = newNode;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(lastNode, element, null);
        lastNode.next = newNode;
        lastNode = newNode;
    }

    @Override
    public void removeFirst() {
        Node newFirst = firstNode.next;
        newFirst.prev = null;
        firstNode.next = null;
        firstNode.element = null;
        firstNode = newFirst;
    }

    @Override
    public void removeLast() {
        Node newLast = lastNode.prev;
        newLast.next = null;
        lastNode.prev = null;
        lastNode.element = null;
        lastNode = newLast;
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
