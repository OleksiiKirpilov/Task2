package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] elements;
    private int size;
    private static final float growFactor = 1.5f;

    public ArrayImpl(int size) {
        elements = new Object[size];
        this.size = 0;
    }

    public ArrayImpl() {
        clear();
    }


    @Override
    public void clear() {
        elements = new Object[10];
        size = 0;
    }

	@Override
    public int size() { return size; }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            return elements[cursor++];
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
        if (index >= size){
            throw new NoSuchElementException();
        }
        elements[index] = element;
    }

	@Override
    public Object get(int index) {
        if (index < size){
            return elements[index];
        } else {
            throw new NoSuchElementException();
        }
    }

	@Override
    public int indexOf(Object element) {
        for(int cursor = 0; cursor < size; ++cursor){
            if (elements[cursor] == element){
                return cursor;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        if (index < size){
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            --size;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        if (size < 1){
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');
        return sb.toString();
    }

    private void grow(){
        int newSize = (int) (size * growFactor);
        Object[] newElements = new Object[newSize];
//        for (int i = 0; i < size; i++) {
//            newElements[i] = elements[i];
//        }
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
    public static void main(String[] args) {
        ArrayImpl a1 = new ArrayImpl();
        a1.add(1);
        a1.add(2);
        a1.add(3);
        System.out.println(a1);
        System.out.println(a1.size());
        System.out.println(a1.get(1));
        a1.set(1, 0);
        System.out.println(a1.get(1));
        System.out.println(a1.indexOf(3));

    }

}
