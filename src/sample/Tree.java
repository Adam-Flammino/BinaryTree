package sample;


public interface Tree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Insert element into the binary tree Return true if the element is
     * inserted successfully
     * @param e
     * @return
     */
    boolean insert(E e);

}
