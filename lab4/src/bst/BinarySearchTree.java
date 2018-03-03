package bst;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;
	boolean addReturn;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = add(root, x);
		size = sizeCalc(root);
		return addReturn;
	}

	private BinaryNode<E> add(BinaryNode<E> node, E x) {
		if (node == null) {
			addReturn = true;
			return new BinaryNode<E>(x);
		} else if (x.compareTo(node.element) == 0) {
			addReturn = false;
			return node;
		} else if (x.compareTo(node.element) < 0) {
			node.left = add(node.left, x);
			return node;

		} else {
			node.right = add(node.right, x);
			return node;

		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		if (root == null) {
			return 0;
		} else
			return height(root) + 1;
	}

	private int height(BinaryNode<E> node) {

		int heightLeft = 0;
		int heightRight = 0;
		if (node.left == null && node.right == null) {
			return -1;
		}
		if (node.left != null)
			heightLeft = height(node.left);
		if (node.right != null)
			heightRight = height(node.right);
		return heightLeft > heightRight ? heightLeft + 1 : heightRight + 1;

	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	private int sizeCalc(BinaryNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			return sizeCalc(node.left) + 1 + sizeCalc(node.right);
		}
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		inOrderPrint(root);

	}

	private void inOrderPrint(BinaryNode<E> node) {
		if (node != null) {
			inOrderPrint(node.left);
			System.out.println(node.element);
			inOrderPrint(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a, 0, a.length - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1
	 * (the first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {

		if (n != null) {
			index = toArray(n.left, a, index);
			a[index] = n.element;
			index++;
			toArray(n.right, a, index);
		}
		return index;

	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in
	 * the array a are assumed to be in ascending order. Returns the root of
	 * tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {

		int mid = (last + first) / 2;
		BinaryNode<E> node = new BinaryNode<E>(a[mid]);
		if (mid - first > 0) {
			node.left = buildTree(a, first, mid - 1);
		}
		if (last - mid > 0) {
			node.right = buildTree(a, mid + 1, last);
		}
		return node;

	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BSTVisualizer bs = new BSTVisualizer("Hej", 300, 300);
		tree.add(7);
		System.out.println("the height is" + " " + tree.height());
		System.out.println("the size is" + " " + tree.size());
		tree.add(6);
//		System.out.println("the height is" + " " + tree.height());
//		System.out.println("the size is" + " " + tree.size());
		tree.add(5);
//		tree.add(4);
//		tree.add(3);
//		tree.add(2);
//		tree.add(1);
//		tree.add(8);
//		tree.add(10);
//		tree.add(9);
//		tree.printTree();
//		System.out.println("the height is" + " " + tree.height());
//		System.out.println("the size is" + " " + tree.size());
//		// bs.drawTree(tree);
//		System.out.println(tree.add(9) ? "ja" : "nej");
//		System.out.println(tree.add(9) ? "ja" : "nej");
//		System.out.println(tree.add(11) ? "ja" : "nej");
//		// bs.drawTree(tree);
//		System.out.println("ny ording");
//		tree.printTree();
		tree.rebuild();
		bs.drawTree(tree);

	}

}
