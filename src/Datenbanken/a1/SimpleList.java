package Datenbanken.a1;

/**
 * The Interface represents a simplified version of the Java List Interface.
 * @param <E> The type of elements in the list
 */
public interface SimpleList<E> extends Iterable<E> {

    /**
     * Inserts the given element at the front of the list.
     * @param element The element which will be inserted
     */
    void addFirst(E element);

    /**
     * Retrieves the first element of the list
     * @return The first element of the list; {@code null} if the list is empty
     */
    E getFirst();

    /**
     * Removes the first element of the list
     * @return The first element of the list; {@code null} if the list is empty
     */
    E removeFirst();

    /**
     * Inserts the given element at the end of the list.
     * @param element The element which will be inserted
     */
    void addLast(E element);

    /**
     * Retrieves the last element of the list
     * @return The last element of the list; {@code null} if the list is empty
     */
    E getLast();

    /**
     * Removes the last element of the list
     * @return The last element of the list; {@code null} if the list is empty
     */
    E removeLast();

    /**
     * Retrieves the n-th element of the list
     * @return The n-th element of the list; {@code null} if there is no n-th element
     */
    E get(int n);

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the size of the list
     * @return The size of the list
     */
    int size();

}
