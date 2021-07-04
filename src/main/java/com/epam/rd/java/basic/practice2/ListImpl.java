package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private int size = 0;
    private Node firstNode;
    private Node lastNode;
    
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
        private int cursor;
        private Node node;
        private Node last;

        public IteratorImpl() {
            node = (size == 0) ? null : firstNode;
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            last = node;
            node = node.next;
            cursor++;
            return last.element;
        }

        @Override
        public void remove() {
            if (last == null) {
                throw new IllegalStateException();
            }
            Node newNext = last.next;
            ListImpl.this.unlink(last);
            if (node == last) {
                node = newNext;
            } else {
                cursor--;
            }
            last = null;
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
        if (firstNode == null) {
            lastNode = newNode;
        } else {
            firstNode.prev = newNode;
        }
        firstNode = newNode;
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(lastNode, element, null);
        if (lastNode == null) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
        }
        lastNode = newNode;
        size++;
    }

    @Override
    public void removeFirst() {
        if (firstNode == null) {
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
    }

    @Override
    public void removeLast() {
        if (lastNode == null) {
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
    }

    private void unlink(Node x) {
        Node next = x.next;
        Node prev = x.prev;
        if (prev == null) {
            firstNode = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            lastNode = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.element = null;
        size--;
    }

    @Override
    public Object getFirst() {
        if (firstNode == null) {
            return null;
        }
        return firstNode.element;
    }

    @Override
    public Object getLast() {
        if (lastNode == null) {
            return null;
        }
        return lastNode.element;
    }

    @Override
    public Object search(Object element) {
        Node n = findByValue(element);
        return (n == null) ? null : n.element;
    }

    @Override
    public boolean remove(Object element) {
        Node n = findByValue(element);
        if (n == null) {
            return false;
        }
        unlink(n);
        return true;
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

    private Node findByValue(Object v) {
        for (Node n = firstNode; n != null; n = n.next) {
            if ((v == null && n.element == null) || (v != null && v.equals(n.element))) {
                return n;
            }
        }
        return null;
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
