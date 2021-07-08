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
            node = firstNode;
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException(Integer.toString(cursor));
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
        Node next;

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element, firstNode);
        if (firstNode == null) {
            lastNode = newNode;
        }
        firstNode = newNode;
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(element, null);
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
        if (next == null) {
            lastNode = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        if (lastNode == null) {
            throw new NoSuchElementException();
        }
        Node prev = getPrevNode(lastNode);
        lastNode.element = null;
        lastNode.next = null;
        lastNode = prev;
        if (prev == null)
            firstNode = null;
        else
            prev.next = null;
        size--;
    }

    private Node getPrevNode(Node p) {
        Node n = firstNode;
        while (n != null && n.next != p) {
            n = n.next;
        }
        return n;
    }

    private void unlink(Node x) {
        Node next = x.next;
        Node prev = getPrevNode(x);
        if (prev == null) {
            firstNode = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            lastNode = prev;
        } else {
            x.next = null;
        }
        x.element = null;
        size--;
    }

    @Override
    public Object getFirst() {
        return (firstNode == null) ? null : firstNode.element;
    }

    @Override
    public Object getLast() {
        return (lastNode == null) ? null : lastNode.element;
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
        if (n == firstNode) {
            removeFirst();
        } else {
            unlink(n);
        }
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
            if ((v == null && n.element == null) ||
                    (v != null && v.equals(n.element))) {
                return n;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("ListImpl() demo:");
        ListImpl l1 = new ListImpl();
        ListImpl l2 = new ListImpl();
        // addFirst() & addLast() methods
        l1.addLast('r');
        l1.addLast(null);
        l1.addLast(1.6);
        l1.addFirst(2);
        l2.addFirst("ss");
        System.out.println("l2 = " + l2);
        // clear() method
        l2.clear();
        System.out.println("l2.clear() = " + l2);
        // size() method
        System.out.println("l1 = " + l1);
        System.out.println("l1.size() = " + l1.size());
        // getFirst() method
        System.out.println("l1.getFirst() = " + l1.getFirst());
        // getLast() method
        System.out.println("l1.getLast() = " + l1.getLast());
        // search() method doesn't make any sense
        System.out.println("l1.search(null) = " + l1.search(null));
        // remove() method
        l1.removeFirst();
        System.out.println("l1.removeFirst() = " + l1);
        l1.removeLast();
        System.out.println("l1.removeLast() = " + l1);
        // all iterator() methods
        Iterator i = l1.iterator();
        while (i.hasNext()) {
            i.next();
        }
        i.remove();
        System.out.println("l1 iterator().remove() = " + l1);
    }
}
