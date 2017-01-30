package project4;

/**
 * This class represents a node system used for the implementation of a binary
 * tree. Implements Comparable so nodes can be compared.
 * 
 * @author Thomas Guo
 *
 * @param <E>
 *            The generic type of data the Node class contains. Must implement
 *            Comparable.
 */
public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
	private E data;
	private Node<E> left;
	private Node<E> right;

	/**
	 * Default constructor that keeps all data fields at null.
	 */
	public Node() {
	}

	/**
	 * One parameter constructor that sets data equal to parameter.
	 * 
	 * @param data
	 *            data of type E which the Node object's data data field will be
	 *            set to.
	 */
	public Node(E data) {
		this.data = data;
	}

	/**
	 * Three parameter constructor that sets a Node's data, left, and right data
	 * field.
	 * 
	 * @param data
	 *            data of type E which the Node object's data data field will be
	 *            set to.
	 * @param left
	 *            Node object which the Node object's left data field will be
	 *            set to.
	 * @param right
	 *            Node object which the Node object's right data field will be
	 *            set to.
	 */
	public Node(E data, Node<E> left, Node<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns data of a node.
	 * 
	 * @return data of the node's data data field
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets the data of a node.
	 * 
	 * @param data
	 *            data of type E which the Node object's data data field will be
	 *            set to.
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns left node data field of a node.
	 * 
	 * @return node of the node's left data field that will be returned
	 */
	public Node<E> getLeft() {
		return left;
	}

	/**
	 * Sets the left node data field of a node.
	 * 
	 * @param Node
	 *            object which the Node object's left data field will be set to.
	 */
	public void setLeft(Node<E> left) {
		this.left = left;
	}

	/**
	 * Returns right node data field of a node.
	 * 
	 * @return node of the node's right data field that will be returned
	 */
	public Node<E> getRight() {
		return right;
	}

	/**
	 * Sets the right node data field of a node.
	 * 
	 * @param Node
	 *            object which the Node object's right data field will be set
	 *            to.
	 */
	public void setRight(Node<E> right) {
		this.right = right;
	}

	/**
	 * Compares two nodes by using the compareTo method on the two nodes' data.
	 * 
	 * @param other
	 *            Node object to be compared to Node object on which the method
	 *            is called
	 * @return -1 if this node is less than the other node, 0 if this node is
	 *         equal to the other node, and 1 if this node is greater than the
	 *         other node.
	 */
	public int compareTo(Node<E> other) {
		return data.compareTo(other.getData());
	}

	/**
	 * Converts Node object into a String representation by returning the result
	 * of the toString() method on the data.
	 * 
	 * @return String obtained from toString() method on data data field of Node object.
	 */
	public String toString() {
		if (data == null) {
			return null;
		}
		return data.toString();
	}
}
