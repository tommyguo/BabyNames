package project4;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This class represents a binary search tree with methods that allow the user
 * to add and remove data as well as retrieve the lowest and highest data. This
 * class is also iterable.
 * 
 * @author Thomas Guo
 *
 * @param <E> The generic type of data the MyBST class contains. Must implement
 * Comparable.
 * 
 * @throws
 */
public class MyBST<E extends Comparable<E>> implements Iterable<E> {
	protected Node<E> root;

	/**
	 * Default constructor that creates a new empty tree.
	 */
	public MyBST() {
	}

	/**
	 * Adds data to a tree if no duplicate data exists in the tree. Otherwise,
	 * does nothing. Public wrapper class for the recAdd method.
	 * 
	 * @param o
	 *            Data to be added to the tree
	 * @return true if the data was successfully added, false if it was not.
	 * @throws NullPointerException
	 *             if the data is null
	 * @throws ClassCastException
	 *             if the data cannot be compared to elements in the tree
	 */
	public boolean add(E o) throws NullPointerException, ClassCastException {
		if (o == null) {
			throw new NullPointerException();
		}
		if (root != null && !o.getClass().equals(root.getData().getClass())) {
			throw new ClassCastException();
		} else {
			Node<E> newNode = new Node<E>(o);
			if (root == null) {
				root = newNode;
				return true;
			} else {
				return recAdd(root, newNode);
			}

		}
	}

	/**
	 * Adds data to a tree if no duplicate data exists in the tree. Otherwise,
	 * does nothing. Private method used in the public wrapper add method.
	 * 
	 * @param node
	 *            node to which the newNode is compared
	 * @param newNode
	 *            node containing the data to be added
	 * @return true if the data was successfully added, false if it was not.
	 */
	private boolean recAdd(Node<E> node, Node<E> newNode) {
		if (newNode.compareTo(node) == 0) {
			return false;
		}
		if (newNode.compareTo(node) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(newNode);
				return true;
			}
			return recAdd(node.getLeft(), newNode);
		}

		else {
			if (node.getRight() == null) {
				node.setRight(newNode);
				return true;
			}
			return recAdd(node.getRight(), newNode);
		}
	}

	/**
	 * Checks if passed object exists in BST.
	 * 
	 * @param o
	 *            object that is checked
	 * @return true if the tree contains the object, false if it does not
	 * @throws NullPointerException
	 *             if object is null
	 * @throws ClassCastException
	 *             if object's class does not equal the class of the data of the
	 *             root
	 */
	public boolean contains(Object o) throws NullPointerException, ClassCastException {
		if (o == null) {
			throw new NullPointerException();
		}
		if (root != null && !o.getClass().equals(root.getData().getClass())) {
			throw new ClassCastException();
		}

		Node<E> current = root;
		@SuppressWarnings("unchecked")
		Node<E> node = new Node<E>((E) o);
		while (current != null) {
			if (node.compareTo(current) < 0) {
				current = current.getLeft();
			} else if (node.compareTo(current) > 0) {
				current = current.getRight();
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the lowest element in the tree.
	 * 
	 * @return data of the lowest node in the tree
	 * @throws NoSuchElementException
	 *             if the tree is empty
	 */
	public E first() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		Node<E> current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current.getData();
	}

	/**
	 * Returns the highest element in the tree.
	 * 
	 * @return the data of the highest node in the tree.
	 * @throws NoSuchElementException
	 *             if the tree is empty
	 */
	public E last() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		Node<E> current = root;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getData();
	}

	/**
	 * Checks to see if the tree is empty.
	 * 
	 * @return true if the tree is empty, false if it is not.
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns an Iterator<E> over the elements in the tree in ascending order.
	 * 
	 * @return Iterator<E> that allows user to iterate over the nodes in the
	 *         tree.
	 */
	public Iterator<E> iterator() {
		IteratorList<E> list = new IteratorList<E>(root);
		return list;
	}

	/**
	 * Checks to see if data exists in a tree and removes the node containing
	 * the data. If the data does not exist in the tree, then nothing is
	 * removed.
	 * 
	 * @param o
	 *            data of type Object that is removed from the tree.
	 * @return true if data was removed, false if it was not.
	 * @throws NullPointerException
	 *             if passed object is null
	 * @throws ClassCastException
	 *             if passed object is not the same class as the root's data.
	 */
	public boolean remove(Object o) throws NullPointerException, ClassCastException {
		if (o == null) {
			throw new NullPointerException();
		}
		if (root != null && !o.getClass().equals(root.getData().getClass())) {
			throw new ClassCastException();
		}

		if (!this.contains(o)) {
			return false;
		}

		Node<E> current = root;
		Node<E> currentParent = root;
		@SuppressWarnings("unchecked")
		// casts the object to the generic type and creates a node from it
		Node<E> node = new Node<E>((E) o);

		// sets current to the node to be removed and currentParent to the
		// parent node of current.
		while (current != null) {
			if (node.compareTo(current) < 0) {
				currentParent = current;
				current = current.getLeft();
			} else if (node.compareTo(current) > 0) {
				currentParent = current;
				current = current.getRight();
			} else {
				break;
			}
		}
		// sets currentParent's data field containing current to null if current
		// is a leaf
		if (current.getRight() == null && current.getLeft() == null) {
			if (current == root) {
				root = null;
			}
			else if (current == currentParent.getLeft()) {
				currentParent.setLeft(null);
			} else {
				currentParent.setRight(null);
			}
		}
		// sets currentParent's data field containing current to current's child
		// if current only has one child
		else if (current.getRight() == null) {
			if (current == root) {
				root = current.getLeft();
			}
			else if (current == currentParent.getLeft()) {
				currentParent.setLeft(current.getLeft());
			}
			else {
				currentParent.setRight(current.getLeft());
			}
		}

		else if (current.getLeft() == null) {
			if (current == root) {
				root = current.getRight();
			}
			else if (current == currentParent.getLeft()) {
				currentParent.setLeft(current.getRight());
			}
			else {
				currentParent.setRight(current.getRight());
			}
		}

		// replaces current with the rightmost node in current's left tree and
		// handles the removal of that node
		else {
			Node<E> replacementParent = current.getLeft();
			if (replacementParent.getRight() == null) {
				current.setData(replacementParent.getData());
				current.setLeft(replacementParent.getLeft());
				return true;
			}
			else if (replacementParent.getRight().getRight() == null) {
			} 
			else {
				while (replacementParent.getRight().getRight() != null) {
					replacementParent = replacementParent.getRight();
				}
			}
			Node<E> replacement = replacementParent.getRight();
			current.setData(replacement.getData());
			if (replacement.getLeft() != null) {
				replacementParent.setRight(replacement.getLeft());
			}
			else {
				replacementParent.setRight(null);
			}
		}
		return true;

	}

}
