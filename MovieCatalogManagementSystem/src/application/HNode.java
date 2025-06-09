package application;

public class HNode<T extends Comparable<T>> {
	private T data;
	private boolean isEmpty = true;
	private AVLTree<T> avlTree;

	public HNode(T data) {
		this.data = data;
		this.isEmpty = false;
		this.avlTree = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
		this.isEmpty = (data == null);
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean empty) {
		this.isEmpty = empty;
	}

	public AVLTree<T> getAvlTree() {
		return avlTree;
	}

	public void setAvlTree(AVLTree<T> avlTree) {
		this.avlTree = avlTree;
	}

	@Override
	public String toString() {
		return "HNode [data=" + data + ", isEmpty=" + isEmpty + ", hasTree=" + (avlTree != null) + "]";
	}

}