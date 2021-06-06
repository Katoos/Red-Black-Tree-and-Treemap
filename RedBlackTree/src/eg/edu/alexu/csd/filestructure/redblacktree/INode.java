package eg.edu.alexu.csd.filestructure.redblacktree;

public interface INode<T extends Comparable<T>, V> extends Cloneable {
	static final boolean RED   = true;
    static final boolean BLACK = false;

//    // deep cloning.
//	default public INode<T, V> clone(INode<T, V> root) throws CloneNotSupportedException {
//		INode<T, V> copy = new Node<T, V>();
//		copy.setKey(root.getKey());
//		copy.setValue(root.getValue());
//		copy.setColor(root.getColor());
//		copy.setLeftChild(root.getLeftChild());
//		copy.setRightChild(root.getRightChild());
//		copy.setParent(root.getParent());
//		return copy;
//	}

    /**
     * set the parent of the current node in the tree
     * @param parent
     */
    void setParent(INode<T, V> parent);
	/**
	* Returns the parent of the current node in the tree
	* @return INode wrapper to the parent of the current node
	*/
	INode<T, V> getParent();    
    /**
     * set the left child of the current node in the tree
     * @param leftChild
     */
    void setLeftChild(INode<T, V> leftChild);
	/**
	* Returns the left child of the current node in the tree
	* @return INode wrapper to the left child of the current node
	*/
	INode<T, V> getLeftChild();
	
	/**
     * set the right child of the current node in the tree
     * @param rightChild
     */
	void setRightChild(INode<T, V> rightChild);
	
	/**
	* Returns the right child of the current node in the tree
	* @return INode wrapper to the right child of the current node
	*/
	INode<T, V> getRightChild();
	/**
	* Set/Get the key of the current node
	* @return Key of the current node
	*/
	T getKey();
	void setKey(T key);
	/**
	* Set/Get the value of the current node
	* @return Value of the current node
	*/
	V getValue();
	void setValue(V value);
	/**
	* Set/Get the color of the current node
	* @return Color of the current node
	*/	
	boolean getColor();
	void setColor(boolean color);
	/**
	 * return whether the given node is null or not.
	 * @return
	 */
	boolean isNull();
}