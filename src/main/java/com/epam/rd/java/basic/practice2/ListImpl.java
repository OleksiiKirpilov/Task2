package com.epam.rd.java.basic.practice2;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private int size = 0;
    private Node firstNode;
    private Node lastNode;
    private int modCount = 0;

    public ListImpl() {

    }

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
        Node prev;
        Node next;

        public Node(Node prev, Object element, Node next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(null, element, firstNode);
        if (firstNode == null){
            lastNode = newNode;
        } else {
            firstNode.prev = newNode;
        }
        firstNode = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(lastNode, element, null);
        if (lastNode == null){
            lastNode = newNode;
        } else {
            lastNode.next = newNode;
        }
        lastNode = newNode;
        size++;
        modCount++;
    }

    @Override
    public void removeFirst() {
        if (firstNode == null){
            throw new NoSuchElementException();
        }
        Node next = firstNode.next;
        firstNode.element = null;
        firstNode.next = null;
        firstNode = next;
        if (next == null)
            lastNode = null;
        else
            next.prev = null;
        size--;
        modCount++;
    }

    @Override
    public void removeLast() {
        if (lastNode == null){
            throw new NoSuchElementException();
        }
        Node prev = lastNode.prev;
        lastNode.element = null;
        lastNode.next = null;
        lastNode = prev;
        if (prev == null)
            firstNode = null;
        else
            prev.next = null;
        size--;
        modCount++;
    }

    @Override
    public Object getFirst() {
        if (firstNode == null){
            throw new NoSuchElementException();
        }
        return firstNode.element;
    }

    @Override
    public Object getLast() {
        if (lastNode == null){
            throw new NoSuchElementException();
        }
        return lastNode.element;
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
        if (size() == 0) {
            return "[]";
        }
        Iterator<Object> i = iterator();
        StringBuilder sb = new StringBuilder("[");
        while (i.hasNext()) {
            sb.append(i.next()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        ListImpl l1 = new ListImpl();
        l1.addLast(1);
        l1.addLast(2);
        System.out.println(l1);
        l1.addFirst("Q");
        System.out.println(l1);
    }
}
