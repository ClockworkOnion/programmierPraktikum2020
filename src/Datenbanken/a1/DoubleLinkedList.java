package Datenbanken.a1;

import java.util.Iterator;

public class DoubleLinkedList<E> implements SimpleList<E> {

    class ListElement<E> {
        public E data;
        public ListElement next, prev;

        ListElement (E data) {
            this.data = data;
        }
    }

    ListElement<E> head, tail;
    int size = 0;

    @Override
    public void addFirst(E element) {
        ListElement<E> newFirst = new ListElement<>(element);

        if (head != null) {
            head.prev = newFirst;
            newFirst.next = head;
            head = newFirst;
        } else {
            head = newFirst;
            tail = newFirst;
        }
        size++;

    }

    @Override
    public E getFirst() {
        return head.data;
    }

    @Override
    public E removeFirst() {
        ListElement<E> second = head.next;
        ListElement<E> tempHead = head;
        second.prev = null;
        head = second;
        size--;
        return tempHead.data;
    }

    @Override
    public void addLast(E element) {
        ListElement<E> newTail = new ListElement<>(element);
        ListElement<E> tempTail = tail;
        if (tail != null) {
            tail = newTail;
            tail.prev = tempTail;
            tempTail.next = tail;
            size++;
        } else {
            head = newTail;
            tail = newTail;
        }
    }

    @Override
    public E getLast() {
        return tail.data ;
    }

    @Override
    public E removeLast() {
        ListElement<E> tempLast = tail;
        ListElement<E> secondToLast = tail.prev;
        secondToLast.next = null;
        tail = secondToLast;
        size--;
        return tempLast.data;
    }

    @Override
    public E get(int n) {
        if (head == null) { return null; }
        ListElement<E> elem = head;
        for (int i = 0; i < n; i++) {
            if (elem.next != null) {
                elem = elem.next;
            }
        }
        return elem.data;
    }

    @Override
    public boolean isEmpty() {
        //if (head == null) { return true; } else { return false; }
        return (size==0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            ListElement<E> nextElement = head;

            @Override
            public boolean hasNext() {
                return nextElement.next != null;
            }

            @Override
            public E next() {
                ListElement<E> temp = nextElement;
                nextElement = nextElement.next;
                return temp.data;
            }
        };
    }
}
