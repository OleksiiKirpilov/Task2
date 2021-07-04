package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private final ArrayImpl array;

    public StackImpl() {
        array = new ArrayImpl();
    }

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public int size() {
        return array.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int cursor = size();

        @Override
        public boolean hasNext() {
            return cursor != 0;
        }

        @Override
        public Object next() {
            if (cursor == 0) {
                throw new NoSuchElementException();
            }
            return array.elements[--cursor];
        }

    }

    @Override
    public void push(Object element) {
        array.add(element);
    }

    @Override
    public Object pop() {
        if (size() == 0) {
            return null;
        }
        Object value = top();
        array.remove(size() - 1);
        return value;
    }

    @Override
    public Object top() {
        int index = size() - 1;
        return (index < 0) ? null : array.get(size() - 1);
    }

    @Override
    public String toString() {
        return array.toString();
    }

    public static void main(String[] args) {
        System.out.println("StackImpl() demo:");
        StackImpl s1 = new StackImpl();
        StackImpl s2 = new StackImpl();
        // push() method
        s1.push(1);
        s1.push('b');
        s1.push(null);
        s1.push(1.5);
        System.out.println("s1 = " + s1);
        s2.push("s");
        System.out.println("s2 = " + s2);
        // clear() method
        s2.clear();
        System.out.println("s2.clear() = " + s2);
        // top() method
        System.out.println("s1.top() = " + s1.top());
        // size() method
        System.out.println("s1.size() = " + s1.size());
        // pop() method
        System.out.println("s1.pop() = " + s1.pop());
        System.out.println("s1 = " + s1);
        // iterator() hasNext() & next() methods
        Iterator i = s1.iterator();
        Object tempObject = new Object();
        while (i.hasNext()){
            tempObject = i.next();
        }
        System.out.println("s1 iterator().next() = " + tempObject);
    }

}
