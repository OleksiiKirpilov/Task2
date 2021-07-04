package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class StackImpl implements Stack {

    private ListImpl list;

    public StackImpl() {
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
    public void push(Object element) {
        list.addLast(element);
    }

    @Override
    public Object pop() {
        if (size() == 0) {
            return null;
        }
        Object value = top();
        list.removeLast();
        return value;
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
        StackImpl s1 = new StackImpl();
        s1.push(1);
        s1.push(2);
        System.out.println(s1);

    }

}
