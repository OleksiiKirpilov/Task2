package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

    ListImpl queue;

    public QueueImpl() {
        queue = new ListImpl();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public int size() {
        return queue.size();
    }

    public Iterator<Object> iterator() {
        return queue.iterator();
        //return new IteratorImpl();
    }

//    private class IteratorImpl implements Iterator<Object> {
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public Object next() {
//            return null;
//        }
//
//    }

    @Override
    public void enqueue(Object element) {
        queue.addLast(element);
    }

    @Override
    public Object dequeue() {
        Object val = queue.getFirst();
        if (size() > 0) {
            queue.removeFirst();
        }
        return val;
    }

    @Override
    public Object top() {
        return queue.getFirst();
    }

    @Override
    public String toString() {
        return queue.toString();
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
