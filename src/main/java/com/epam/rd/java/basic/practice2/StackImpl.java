package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

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
        return array.iterator();
        //        return new IteratorImpl();
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
        return array.get(size() - 1);
    }

    @Override
    public String toString() {
        return array.toString();
        //        if (size() == 0) {
//            return "[]";
//        }
//        StringBuilder sb = new StringBuilder("[");
//        for (i.hasNext()) {
//            sb.append(i.next()).append(", ");
//        }
//        sb.delete(sb.length() - 2, sb.length());
//        sb.append(']');
//        return sb.toString();
    }

    public static void main(String[] args) {
        StackImpl s1 = new StackImpl();
        s1.push(1);
        s1.push(2);
        System.out.println(s1);

    }

}
