package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] elements;
    private int size;
    private static final float growFactor = 1.5f;

    public ArrayImpl(int size) {
        elements = new Object[size];
        this.size = size;
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
    public int size() {
        return size;
        }
	
	@Override
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
    public void add(Object element) {
        if (size < this.elements.length){
            elements[size++] = element;
        } else {
            throw new NoSuchElementException();
        }
    }

	@Override
    public void set(int index, Object element) {
        
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
            if (elements[cursor].equals(element)){
                return cursor;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        
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
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }

    private void grow(){
        int newSize = (int) (size * growFactor);
        Object[] newElements = new Object[newSize];

    }
    public static void main(String[] args) {

    }

}
