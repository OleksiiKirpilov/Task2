package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private ArrayImpl array;

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
        StackImpl s1 = new StackImpl();
        s1.push("a");
        s1.push("b");
        s1.push("c");
        s1.push(null);
        System.out.println(s1);

        StringBuilder sb = new StringBuilder();
        for (Object o : s1) {
            sb.append(o);
        }
        System.out.println(sb);
    }

}
