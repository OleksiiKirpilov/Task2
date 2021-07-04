package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

    private final ListImpl queue;

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
    }

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
        System.out.println("QueueImpl() demo:");
        QueueImpl q1 = new QueueImpl();
        QueueImpl q2 = new QueueImpl();
        // enqueue() method
        q1.enqueue(1);
        q1.enqueue('b');
        q1.enqueue(null);
        q1.enqueue(1.5);
        q2.enqueue("s");
        System.out.println("q2 = " + q2);
        // clear() method
        q2.clear();
        System.out.println("q2.clear() = " + q2);
        // top() method
        q1.top();
        System.out.println("q1 = " + q1);
        // size() method
        System.out.println("q1.size() = " + q1.size());
        // dequeue() method
        System.out.println("q1.dequeue() = " + q1.dequeue());
        System.out.println("q1 = " + q1);
        // all iterator() methods
        Iterator i = q1.iterator();
        while (i.hasNext()){
            i.next();
        }
        i.remove();
        System.out.println("q1 iterator().remove() = " + q1);
    }

}
