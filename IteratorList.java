package project4;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * This class represents an iterator over an iterable binary search tree.
 * 
 * @author Thomas Guo
 *
 * @param <E>
 *            The generic type of data the set contains.
 * @throws UnsupportedOperationException
 *             if user calls remove method
 * @throws NullPointerException
 *             if user calls forEachRemaining method
 */
public class IteratorList<E extends Comparable<E>> implements Iterator<E> {
	private ArrayList<E> set = new ArrayList<E>();
	private int size;

	/**
	 * One parameter constructor which places data in a binary search tree into
	 * an ArrayList.
	 * 
	 * @param root
	 *            root of the tree to be processed into an Iterator.
	 */
	public IteratorList(Node<E> root) {
		inOrder(root);
		size = 0;
	}

	/**
	 * Private method used in the constructor to process data in binary search
	 * tree in order.
	 * 
	 * @param n
	 *            node to be processed
	 */
	private void inOrder(Node<E> n) {
		if (n == null) {
			return;
		}
		inOrder(n.getLeft());
		set.add(n.getData());
		inOrder(n.getRight());
	}

	/**
	 * Checks if the iterator has a next element.
	 * 
	 * @return true if iterator has a next element, false if it does not.
	 */
	@Override
	public boolean hasNext() {
		if (size + 1 >= set.size()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns the next element in the iterator.
	 * 
	 * @return next element in the iterator
	 */
	@Override
	public E next() {
		int tempSize = size;
		size++;
		return set.get(tempSize);
	}

	/**
	 * Unsupported method
	 * 
	 * @throws UnsupportOperationException
	 *             if method is called
	 */
	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Unsupported method
	 * 
	 * @throws NullPointerException
	 *             if method is called
	 */
	@Override
	public void forEachRemaining(Consumer<? super E> action) throws NullPointerException {
		throw new NullPointerException();
	}

}
