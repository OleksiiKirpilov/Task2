package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    Object[] elements;
    private int size = 0;
    private static final float GROW_FACTOR = 1.5f;
    private static final int INITIAL_CAPACITY = 10;

    public ArrayImpl(int capacity) {
        elements = new Object[capacity];
    }

    public ArrayImpl() {
        this(INITIAL_CAPACITY);
    }

    @Override
    public void clear() {
        if (elements.length > INITIAL_CAPACITY * GROW_FACTOR) {
            elements = new Object[INITIAL_CAPACITY];
        } else {
            for (int i = 0; i < elements.length; i++) {
                elements[i] = null;
            }
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int cursor = 0;
        private int last = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            if (cursor >= size()) {
                throw new NoSuchElementException(Integer.toString(cursor));
            }
            last = cursor;
            return elements[cursor++];
        }

        @Override
        public void remove() {
            if (last == -1) {
                throw new IllegalStateException();
            }
            ArrayImpl.this.remove(last);
            cursor = last;
            last = -1;
        }

    }

    @Override
    public void add(Object element) {
        if (size == this.elements.length) {
            grow();
        }
        elements[size++] = element;
    }

    @Override
    public void set(int index, Object element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        elements[index] = element;
    }

    @Override
    public Object get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        return elements[index];
    }

    @Override
    public int indexOf(Object element) {
        for (int cursor = 0; cursor < size; ++cursor) {
            Object e = elements[cursor];
            if ((element == null && e == null)
                    || (element != null && element.equals(e))) {
                return cursor;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[--size] = null;
    }

    @Override
    public String toString() {
        if (size < 1) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        for (; i < size - 1; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(elements[i]).append(']');
        return sb.toString();
    }

    private void grow() {
        int newSize = (size > 2) ? (int) (size * GROW_FACTOR) : 3;
        Object[] newElements = new Object[newSize];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    public static void main(String[] args) {
        System.out.println("ArrayImpl() demo:");
        ArrayImpl a1 = new ArrayImpl(4);
        ArrayImpl a2 = new ArrayImpl();
        // add() method
        a1.add(1);
        a1.add('b');
        a1.add(null);
        a1.add(1.5);
        a2.add("s");
        System.out.println("a2 = " + a2);
        // clear() method
        a2.clear();
        System.out.println("a2.clear() = " + a2);
        // set() method
        a1.set(1, 0);
        System.out.println("a1 = " + a1);
        // size() method
        System.out.println("a1.size() = " + a1.size());
        // get() method
        System.out.println("a1.get(1) = " + a1.get(1));
        // indexOf() method
        System.out.println("a1.indexOf(3) = " + a1.indexOf(3));
        // remove() method
        a1.remove(1);
        System.out.println("a1.remove(1) = " + a1);
        // all iterator() methods
        Iterator i = a1.iterator();
        while (i.hasNext()) {
            i.next();
        }
        i.remove();
        System.out.println("a1 iterator().remove() = " + a1);
    }
}
