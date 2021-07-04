package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

    ListImpl list;

    public QueueImpl() {
        list = new ListImpl();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

    }

    @Override
    public void enqueue(Object element) {
        list.addLast(element);
    }

    @Override
    public Object dequeue() {
        Object val = list.getFirst();
        list.removeFirst();
        return val;
    }

    @Override
    public Object top() {
        return list.getLast();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        QueueImpl q1 = new QueueImpl();
        q1.enqueue("a");
        q1.enqueue("b");
        q1.enqueue("c");
        q1.dequeue();
        System.out.println(q1);
    }

}
