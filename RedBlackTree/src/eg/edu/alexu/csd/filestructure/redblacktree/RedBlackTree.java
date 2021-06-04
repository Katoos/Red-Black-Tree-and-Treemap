package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree <T extends Comparable<T> , V> implements IRedBlackTree<T, V> {
	private INode<T, V> root =null ;
	
	private boolean isRed(INode<T, V> x) {
        if (x == null) return false;
        return x.getColor() == INode.RED;
    }

	@Override
	public INode<T, V> getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.root == null || this.root.isNull()) ;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.root = null ;
		
	}

	@Override
	public V search(T key) {
		if (key == null) throw new IllegalArgumentException("Key is null");
        return searchhelper(root, key);
		
	}
	public V searchhelper(INode<T, V> root , T key) {
		
		while (root != null) {
            int cmp = key.compareTo(root.getKey());
            if(cmp < 0) {
            	root = root.getLeftChild();
            }
            else if(cmp > 0) {
            	root = root.getRightChild();
            }
            else { //key is found
            	return root.getValue();
            }
        }
        return null;
	}

	@Override
	public boolean contains(T key) {
		return search(key) != null ;
		
	}

	@Override
	public void insert(T key, V value) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (value == null) {
            delete(key);
            return;
        }
		
		root = inserthelper(root, key, value) ;
		root.setColor(INode.BLACK);
	}
	//Method for left rotation
	public INode<T, V> RotateLeft(INode<T, V> h) {
		INode<T, V> x= h.getRightChild(); 
		h.setRightChild(x.getLeftChild());
		x.setLeftChild(h);
		x.setColor(h.getColor());
		h.setColor(INode.RED);
		
		
		return x ;
	}
	//Method for Right rotation
	public INode<T, V> RotateRight(INode<T, V> h) {
		INode<T, V> x= h.getLeftChild(); 
		h.setLeftChild(x.getRightChild());
		x.setRightChild(h);
		x.setColor(h.getColor());
		h.setColor(INode.RED);
		return x ;
	}
	//Method for fliping colors
	public void FlipColors(INode<T, V> h) {
		h.setColor(INode.RED);
		h.getLeftChild().setColor(INode.BLACK);
		h.getRightChild().setColor(INode.BLACK);
		
		
	}
	//Helper Method for insertion
	public INode<T, V> inserthelper(INode<T, V> root,T key,V value) {
		//right place found to insert node
		if(root == null) {
			INode<T, V> NewNode = new Node<>();
			NewNode.setColor(INode.RED);
			NewNode.setKey(key);
			NewNode.setValue(value);
			return NewNode ;
			
		}
		int cmp = key.compareTo(root.getKey()) ;
		//if key is less than curr key node, go left
		if(cmp < 0) {
			root.setLeftChild(inserthelper( root.getLeftChild(), key, value));
		}
		//if key is greater than curr key node, go right
		else if(cmp > 0) {
			root.setRightChild(inserthelper( root.getRightChild(), key, value));
		}
		//we reached key
		else {
			root.setValue(value);
		}
		//manage RB properties
		//if left child is not red , but right is red 
		if(!isRed(root.getLeftChild() ) && isRed(root.getRightChild()) ) {
			//rotate left
			root = RotateLeft(root) ;
		}
		//if parent is red and his child is red 
		if(isRed(root.getLeftChild())  && isRed(root.getLeftChild().getLeftChild() ) ) {
			//rotate right
			root = RotateRight(root);
		}
		//if left and right child are red
		if( isRed(root.getLeftChild())  && isRed(root.getRightChild())  ) {
			//Flip colors
			FlipColors(root);
		}
		return root ;
	}
	

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		return false;
	}

}





