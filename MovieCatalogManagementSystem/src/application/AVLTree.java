package application;

public class AVLTree<T extends Comparable<T>> {
	private TNode<T> root;
	

	public AVLTree() {
		this.root = null;
		
	}

	private int getHeight(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
	}

	private int getHeightDifference(TNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return getHeight(node.getLeft()) - getHeight(node.getRight());
		}

	}

	private TNode<T> rotateLeft(TNode<T> x) {
		TNode<T> y = x.getRight();
		TNode<T> temp = y.getLeft();

		y.setLeft(x);
		x.setRight(temp);

		return y;
	}

	private TNode<T> rotateRight(TNode<T> y) {
		TNode<T> x = y.getLeft();
		TNode<T> temp = x.getRight();

		x.setRight(y);
		y.setLeft(temp);

		return x;
	}

	public TNode<T> rotateLeftRight(TNode<T> node) {

		node.setLeft(rotateLeft(node.getLeft()));

		return rotateRight(node);
	}

	public TNode<T> rotateRightLeft(TNode<T> node) {

		node.setRight(rotateRight(node.getRight()));

		return rotateLeft(node);
	}

	private TNode rebalance(TNode nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) {
			if (getHeightDifference(nodeN.getLeft()) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) {
			if (getHeightDifference(nodeN.getRight()) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			root = addEntry(data, root);
		}
	}

	public TNode<T> addEntry(T data, TNode<T> root) {
		if (root == null) {
			return new TNode<>(data);
		}
		if (data.compareTo(root.getData()) < 0) {
			root.setLeft(addEntry(data, root.getLeft()));
		} else if (data.compareTo(root.getData()) > 0) {
			root.setRight(addEntry(data, root.getRight()));

		} else {
			root.setData(data);
			return root;
		}
		return rebalance(root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public T search(String title) {
		return searchN(root, title);
	}

	private T searchN(TNode<T> node, String title) {
		if (node == null) {
			return null;
		}

		Movie movie = (Movie) node.getData();
		int comparison = title.compareToIgnoreCase(movie.getTitle());

		if (comparison == 0) {
			return node.getData();
		} else if (comparison < 0) {
			return (T) searchN(node.getLeft(), title);
		} else {
			return (T) searchN(node.getRight(), title);
		}
	}

	private TNode getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.getRight();

		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getLeft();
		}
		if (successor != node.getRight()) { // fix successor connections
			parentOfSuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}

	public T delete(String title) {
		if (isEmpty())
			return null;
		T[] deleted = (T[]) new Comparable[1];
		root = deleteN(root, title, deleted);
		return deleted[0];
	}

	private TNode<T> deleteN(TNode<T> node, String title, T[] deleted) {
		if (node == null) {
			return null;
		}

		Movie movie = (Movie) node.getData();
		int comparison = title.compareToIgnoreCase(movie.getTitle());

		if (comparison < 0) {
			node.setLeft(deleteN(node.getLeft(), title, deleted));
		} else if (comparison > 0) {
			node.setRight(deleteN(node.getRight(), title, deleted));
		} else {
			deleted[0] = node.getData();
			if (!node.hasLeft() && !node.hasRight()) {
				return null;
			} else if (node.hasLeft() && !node.hasRight()) {
				return node.getLeft();
			} else if (node.hasRight() && !node.hasLeft()) {
				return node.getRight();
			} else {
				TNode<T> successor = getSuccessor(node);
				successor.setLeft(node.getLeft());
				return successor;
			}
		}
		return rebalance(node);

	}

	public int getTreeHeight() {
		return getHeight(root);
	}

	public Movie[] getAllMoviesSorted(boolean asc) {
		Movie[] movies = new Movie[getSize()];
		int[] index = { 0 };
		if (asc) {
			inOrderTraversal(root, movies, index);
		} else {
			reverseInOrderTraversal(root, movies, index);
		}
		return movies;
	}

	private void inOrderTraversal(TNode<T> node, Movie[] movies, int[] index) {
		if (node != null) {
			inOrderTraversal(node.getLeft(), movies, index);
			movies[index[0]++] = (Movie) node.getData();
			inOrderTraversal(node.getRight(), movies, index);
		}

	}

	private void reverseInOrderTraversal(TNode<T> node, Movie[] movies, int[] index) {
		if (node != null) {
			reverseInOrderTraversal(node.getRight(), movies, index);
			movies[index[0]++] = (Movie) node.getData();
			reverseInOrderTraversal(node.getLeft(), movies, index);
		}
	}

	public int getSize() {
		return getSizeH(root);
	}

	private int getSizeH(TNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + getSizeH(node.getLeft()) + getSizeH(node.getRight());
	}

	public Movie getHighestRatedMovie() {
		Movie[] highest = { null };
		double[] maxRating = { -1.0 };
		findHighestRated(root, highest, maxRating);
		return highest[0];
	}

	private void findHighestRated(TNode<T> node, Movie[] highest, double[] maxRating) {
		if (node != null) {
			Movie movie = (Movie) node.getData();
			if (movie.getRating() > maxRating[0]) {
				maxRating[0] = movie.getRating();
				highest[0] = movie;
			}
			findHighestRated(node.getLeft(), highest, maxRating);
			findHighestRated(node.getRight(), highest, maxRating);
		}
	}

	public Movie getLowestRatedMovie() {
		Movie[] lowest = { null };
		double[] minRating = { 11.0 };
		findLowestRated(root, lowest, minRating);
		return lowest[0];
	}

	private void findLowestRated(TNode<T> node, Movie[] lowest, double[] minRating) {
		if (node != null) {
			Movie movie = (Movie) node.getData();
			if (movie.getRating() < minRating[0]) {
				minRating[0] = movie.getRating();
				lowest[0] = movie;
			}
			findLowestRated(node.getLeft(), lowest, minRating);
			findLowestRated(node.getRight(), lowest, minRating);

		}
	}

	public TNode<T> getRoot() {
		return root;
	}

	public void clear() {
		root = null;
		
		
	}

}
